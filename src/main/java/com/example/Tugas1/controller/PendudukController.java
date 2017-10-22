package com.example.Tugas1.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Tugas1.model.DomisiliModel;
import com.example.Tugas1.model.KecamatanModel;
import com.example.Tugas1.model.KeluargaModel;
import com.example.Tugas1.model.KelurahanModel;
import com.example.Tugas1.model.KotaModel;
import com.example.Tugas1.model.PendudukModel;
import com.example.Tugas1.service.DomisiliService;
import com.example.Tugas1.service.KeluargaService;
import com.example.Tugas1.service.PendudukService;


@Controller
public class PendudukController {
	@Autowired
	PendudukService pendudukDAO;
	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
	DomisiliService domisiliDAO;
	
	@RequestMapping("/penduduk")	
	public String viewPenduduk(Model model , @RequestParam(value="nik" , required=false) String nik) {
		
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		KeluargaModel keluarga = keluargaDAO.selectKeluargaById(nik);
		
		
		if(nik ==null) {
			return "index";
		}
		else if(!penduduk.equals(null)) {
			DomisiliModel domisili = domisiliDAO.selectDomisili(nik);
			model.addAttribute("domisili",domisili);
			model.addAttribute("penduduk" , penduduk);
			model.addAttribute("keluarga" , keluarga);
			return "view-penduduk";
		}else {
			model.addAttribute("nik" , nik);
			return "error-penduduk";
		}
	}
	@RequestMapping(value="/penduduk/tambah",method=RequestMethod.GET)
	public String addPenduduk() {
		return"add-penduduk-form";
	}
	@RequestMapping(value="/penduduk/tambah",method=RequestMethod.POST)
	public String addPendudukSukses(Model model,
			@RequestParam(value="nama" ,required=false)String nama,
			@RequestParam(value="tempatLahir", required=false)String tempatLahir,
			@RequestParam(value="tanggalLahir" , required=false)String tanggalLahir,
			@RequestParam(value="golonganDarah" , required=false)String golonganDarah,
			@RequestParam(value="agama" , required=false)String agama,
			@RequestParam(value="statusPerkawinan" , required=false)String statusPerkawinan,
			@RequestParam(value="pekerjaan" , required=false)String pekerjaan,
			@RequestParam(value="isWni" , required=false)int isWni,
			@RequestParam(value="isWafat" , required=false)int isWafat,
			@RequestParam(value="idKeluarga" , required=false)int idKeluarga,
			@RequestParam(value="statusDalamKeluarga" , required=false)String statusDalamKeluarga,
			@RequestParam(value="jenisKelamin" ,required=false)int jenisKelamin)
	
		
		{
		String[] tanggal=tanggalLahir.split("-");
		int tanggalWanita = 0;
		if(jenisKelamin == 1) {
			tanggalWanita=Integer.parseInt(tanggal[2])+40;
		}
		else {
			tanggalWanita=Integer.parseInt(tanggal[2]);
		}
		String tahunFinal = tanggal[0].substring(2, 4);
		String tanggalFinal = tanggalWanita + tanggal[1] + tahunFinal;
		String kodeKecamatanFinal = pendudukDAO.selectKecamatan(idKeluarga).substring(0,6);
		int nomorUrutFinal = pendudukDAO.countKeluarga("%"+kodeKecamatanFinal + tanggalFinal + "%")+1;
		String jumlahKeluarga =""+ nomorUrutFinal;
		String nomorUrut="";
		if(jumlahKeluarga.length()==1) 
		{
			nomorUrut="000" + jumlahKeluarga;
		}
		else if(jumlahKeluarga.length()==2) {
			nomorUrut="00" + jumlahKeluarga;
		}
		else if(jumlahKeluarga.length()==3) {
			nomorUrut="0" + jumlahKeluarga;
		}
		else if(jumlahKeluarga.length()==4) {
			nomorUrut=jumlahKeluarga;
		}
		
		String nik = kodeKecamatanFinal+tanggalFinal+nomorUrut;
		nomorUrutFinal += pendudukDAO.countKeluarga("%" + nik + "%");
		jumlahKeluarga=""+nomorUrutFinal;
		if(jumlahKeluarga.length()==1) 
		{
			nomorUrut="000" + jumlahKeluarga;
		}
		else if(jumlahKeluarga.length()==2) {
			nomorUrut="00" + jumlahKeluarga;
		}
		else if(jumlahKeluarga.length()==3) {
			nomorUrut="0" + jumlahKeluarga;
		}
		else if(jumlahKeluarga.length()==4) {
			nomorUrut=jumlahKeluarga;
		}
		nik=kodeKecamatanFinal+tanggalFinal+nomorUrut;
		
		PendudukModel penduduk = new PendudukModel(0,nik, nama,tempatLahir,tanggalLahir,jenisKelamin,isWni,idKeluarga,agama,pekerjaan,statusPerkawinan,statusDalamKeluarga,golonganDarah,isWafat, null);
		pendudukDAO.addPenduduk(penduduk);
			model.addAttribute("nik" , nik);
		
		return"add-penduduk-sukses";
	}
	@RequestMapping("/penduduk/ubah/{nik}")
	public String updatePenduduk(Model model , @PathVariable(value="nik")String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		
		if(penduduk!=null) {
			model.addAttribute("penduduk" , penduduk);
			return "update-penduduk-form";
		}else {
			model.addAttribute("nik" , nik);
			return "not-found-penduduk";
		}
	}
	@RequestMapping(value="/penduduk/ubah/{nik}" , method =RequestMethod.POST)
    public String updatePendudukSubmit(@PathVariable(value="nik")String nikModel,Model model,PendudukModel penduduk,
    		@RequestParam(value="idKeluarga" , required=false)int idKeluargaSesudah,
    		@RequestParam(value="tanggalLahir" , required=false)String tanggalLahirSesudah,
    		@RequestParam(value="jenisKelamin" ,required=false)int jenisKelaminSesudah)
    		
    {	
		model.addAttribute("nikModel",nikModel);
		String[] tanggalLahir = tanggalLahirSesudah.split("-");
		int tanggalWanita = 0;
		if(jenisKelaminSesudah ==1) {
			tanggalWanita = Integer.parseInt(tanggalLahir[2])+40;
		}
		else {
			tanggalWanita = Integer.parseInt(tanggalLahir[2]);
		}
		
		String tahunFinal = tanggalLahir[0].substring(2, 4);
		String tanggalFinal = tanggalWanita + tanggalLahir[1] + tahunFinal;
		String kodeKecamatanFinal = pendudukDAO.selectKecamatan(idKeluargaSesudah).substring(0,6);
		int nomorUrutFinal = pendudukDAO.countKeluarga("%"+kodeKecamatanFinal + tanggalFinal + "%")+1;
		String jumlahKeluarga =""+ nomorUrutFinal;
		String nomorUrut="";
		if(jumlahKeluarga.length()==1) 
		{
			nomorUrut="000" + jumlahKeluarga;
		}
		else if(jumlahKeluarga.length()==2) {
			nomorUrut="00" + jumlahKeluarga;
		}
		else if(jumlahKeluarga.length()==3) {
			nomorUrut="0" + jumlahKeluarga;
		}
		else if(jumlahKeluarga.length()==4) {
			nomorUrut=jumlahKeluarga;
		}
		String nik = kodeKecamatanFinal +tanggalFinal + nomorUrut;
		penduduk.setNik(nik);
		
    		pendudukDAO.updatePenduduk(penduduk);
    			return "update-penduduk-sukses";
    	
    }
	@RequestMapping(value="/penduduk/mati" , method =RequestMethod.POST)
	public String ubahStatus(Model model,PendudukModel penduduk , KeluargaModel keluarga) {
			
		
		penduduk.setIsWafat(1);
		pendudukDAO.updateStatus(penduduk);
		if(pendudukDAO.countJumlahKeluarga(penduduk)== 0) {
			keluarga.setIsTidakBerlaku(1);
			}else {
				keluarga.setIsTidakBerlaku(0);
			}
		pendudukDAO.updateTidakBerlaku(keluarga);
		
		
		
		return"status-diubah";
		
	}
	
}

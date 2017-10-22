package com.example.Tugas1.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Tugas1.model.DomisiliModel;
import com.example.Tugas1.model.KeluargaModel;
import com.example.Tugas1.model.PendudukModel;
import com.example.Tugas1.service.DomisiliService;
import com.example.Tugas1.service.KeluargaService;

@Controller
public class KeluargaController {
	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
	DomisiliService domisiliDAO;
	
	@RequestMapping("/keluarga")
	public String viewKeluargaByNkk(Model model , @RequestParam(value="nkk" , required =false)String nomorKk) {
		List<PendudukModel> satuKeluarga = keluargaDAO.selectKeluargaByNkk(nomorKk);
		DomisiliModel domisili = domisiliDAO.selectDomisiliNkk(nomorKk);
		if(nomorKk == null) {
			return "index";
		}else if(!satuKeluarga.equals(null)) {
			model.addAttribute("satuKeluarga"  , satuKeluarga);
			model.addAttribute("domisili" , domisili);
			model.addAttribute("nomorKk",nomorKk);
			return "view-keluarga";
		}else {
			model.addAttribute("nkk" , nomorKk);
			return "error-nkk";
		}
		
	}
	@RequestMapping(value="/keluarga/tambah" , method=RequestMethod.GET)
	public String addKeluarga() {
		return"add-keluarga-form";
	}
	@RequestMapping(value="/keluarga/tambah",method=RequestMethod.POST)
	public String addKeluargaSukses(Model model,
			@RequestParam(value="alamat" ,required=false)String alamat,
			@RequestParam(value="rt", required=false)String rt,
			@RequestParam(value="rw" , required=false)String rw,
			@RequestParam(value="idKelurahan" , required=false)int idKelurahan,
			@RequestParam(value="kecamatan" , required=false)String kecamatan)
		{
		
			
			Date createTanggal = new Date();
			SimpleDateFormat tanggalPath = new SimpleDateFormat("ddMMyy");
			String tanggal = tanggalPath.format(createTanggal);	
			String kodeKecamatan= keluargaDAO.selectKecamatan(kecamatan).substring(0,6);
			int nomorUrutFinal = keluargaDAO.countKeluarga(kodeKecamatan + tanggal + "%")+1;
			String jumlahNomor= ""+nomorUrutFinal;
			String nomorUrut="";
			if(jumlahNomor.length()==1) 
			{
				nomorUrut="000" + jumlahNomor;
			}
			else if(jumlahNomor.length()==2) {
				nomorUrut="00" + jumlahNomor;
			}
			else if(jumlahNomor.length()==3) {
				nomorUrut="0" + jumlahNomor;
			}
			else if(jumlahNomor.length()==4) {
				nomorUrut=jumlahNomor;
			}
			String nkk=kodeKecamatan+tanggal+nomorUrut;
			KeluargaModel keluarga = new KeluargaModel(0,nkk,alamat,rt,rw,idKelurahan,0,null);
			model.addAttribute("nkk", nkk);
			keluargaDAO.addKeluarga(keluarga);
			
			return "add-keluarga-sukses";
		
		}@RequestMapping("/keluarga/ubah/{nkk}")
		public String updateKeluarga(Model model , @PathVariable(value="nkk")String nkk){
		KeluargaModel keluarga = keluargaDAO.selectKeluargaNkk(nkk);
		if(keluarga!=null) {
			model.addAttribute("keluarga" , keluarga);
			model.addAttribute("nkk ", nkk);
			return "update-keluarga-form";
		}else {
			model.addAttribute("nkk" , nkk);
			return "not-found-keluarga";
		}
	}
		@RequestMapping(value="/keluarga/ubah/{nkk}" , method =RequestMethod.POST)
	    public String updateKeluargaSubmit(@PathVariable(value="nkk")String nkk,KeluargaModel keluarga,Model model,
	    			@RequestParam(value="kecamatan" ,required=false)String namaKecamatan,
				@RequestParam(value="kota" ,required=false)String namaKota)
	    {
			nkk = keluarga.getNomorKk();
			keluargaDAO.updateKeluarga(keluarga);
			model.addAttribute("nkk" , nkk);
			
			return "update-keluarga-sukses";
	    }
}

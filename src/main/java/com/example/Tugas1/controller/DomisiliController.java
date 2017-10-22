package com.example.Tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Tugas1.model.KecamatanModel;
import com.example.Tugas1.model.KelurahanModel;
import com.example.Tugas1.model.KotaModel;
import com.example.Tugas1.model.PendudukModel;
import com.example.Tugas1.service.DomisiliService;

@Controller
public class DomisiliController {
	@Autowired
	DomisiliService domisiliDAO;
	@RequestMapping(value="/penduduk/cari")
	public String cariPenduduk(Model model,
			@RequestParam(value="kt" , required=false) String idKota,
			@RequestParam(value="kc" , required=false) String idKecamatan,
			@RequestParam(value="kl" , required=false) String idKelurahan) {
			
				List<KotaModel>kumpulanKota = domisiliDAO.selectKota();
				model.addAttribute("kumpulanKota",kumpulanKota);
			if(idKota==null && idKecamatan==null && idKelurahan==null) {
			return"penduduk-cari";
			}
			else if(idKota != null && idKecamatan==null && idKelurahan == null) {
				List<KecamatanModel>kumpulanKecamatan = domisiliDAO.selectKecamatan(idKota);
				model.addAttribute("kumpulanKecamatan" , kumpulanKecamatan);
				return "penduduk-cari-kecamatan";
			}
			
			else if(idKota != null && idKecamatan!=null && idKelurahan ==null) {
				List<KelurahanModel>kumpulanKelurahan = domisiliDAO.selectKelurahan(idKecamatan);
				model.addAttribute("kumpulanKelurahan" , kumpulanKelurahan);
				return "penduduk-cari-kelurahan";
			
			}else {
			List<PendudukModel> kumpulanPenduduk = domisiliDAO.selectPenduduk(idKelurahan);
			model.addAttribute("kumpulanPenduduk" , kumpulanPenduduk);
			return"list-penduduk";
			}
	}
}
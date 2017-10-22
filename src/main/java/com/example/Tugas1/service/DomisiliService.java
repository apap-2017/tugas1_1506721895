package com.example.Tugas1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.Tugas1.model.DomisiliModel;
import com.example.Tugas1.model.KecamatanModel;
import com.example.Tugas1.model.KelurahanModel;
import com.example.Tugas1.model.KotaModel;
import com.example.Tugas1.model.PendudukModel;

public interface DomisiliService {

	List<KotaModel> selectKota();
	
	List<KecamatanModel>selectKecamatan(String idKota);
	
	List<KelurahanModel>selectKelurahan(String idKecamatan);
	
	List<PendudukModel>selectPenduduk(String idKelurahan);
	
	DomisiliModel selectDomisili(String nik);
	DomisiliModel selectDomisiliNkk(String nomorKk);
}

package com.example.Tugas1.service;

import java.util.List;

import com.example.Tugas1.model.KeluargaModel;
import com.example.Tugas1.model.PendudukModel;

public interface KeluargaService {
	List<PendudukModel> selectKeluargaByNkk(String nomorKk);
	
	KeluargaModel selectKeluargaById(String nik);
	List<PendudukModel> selectKeluarga(String nomorKk);
	void addKeluarga(KeluargaModel keluarga);
	String selectKecamatan(String kecamatan);
	int countKeluarga(String nkk);
	void updateKeluarga(KeluargaModel keluarga);
	KeluargaModel selectKeluargaNkk(String nkk);
}
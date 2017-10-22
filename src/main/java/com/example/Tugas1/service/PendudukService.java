package com.example.Tugas1.service;

import com.example.Tugas1.model.KeluargaModel;
import com.example.Tugas1.model.PendudukModel;

public interface PendudukService {

	PendudukModel selectPenduduk(String nik);
	
	void addPenduduk(PendudukModel penduduk);

	String selectKecamatan(int idKeluarga);

	int countKeluarga(String nik);
	
	void updatePenduduk(PendudukModel penduduk);
	
	void updateStatus(PendudukModel penduduk);
	
	void updateTidakBerlaku(KeluargaModel keluarga);

	int countJumlahKeluarga(PendudukModel penduduk);
}

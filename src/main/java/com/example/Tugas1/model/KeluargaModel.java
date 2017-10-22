package com.example.Tugas1.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
	private int id;
	private String nomorKk;
	private String alamat;
	private String rt;
	private String rw;
	private int idKelurahan;
	private int isTidakBerlaku;
	private List<PendudukModel> satuKeluarga;
	
}

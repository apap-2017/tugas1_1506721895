package com.example.Tugas1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomisiliModel {
	private List<KotaModel> kumpulanKota;
	private List<KecamatanModel> kumpulanKecamatan;
	private List<KelurahanModel> kumpulanKelurahan;
	private List<PendudukModel> kumpulanPenduduk;
	private String namaKecamatan;
	private String namaKota;
	private String namaKelurahan;
	private String alamat;
	private String rt;
	private String rw;
}

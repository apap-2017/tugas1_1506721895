package com.example.Tugas1.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanModel {
	private Integer id;
	private String kodeKecamatan;
	private String idKota;
	private String namaKecamatan;
	private List<KecamatanModel>kecamatan;
}

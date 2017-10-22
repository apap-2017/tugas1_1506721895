package com.example.Tugas1.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel {
	private Integer id;
	private String kodeKelurahan;
	private String idKecamatan;
	private String namaKelurahan;
	private String kodePos;
	private List<KelurahanModel> kelurahan;
}

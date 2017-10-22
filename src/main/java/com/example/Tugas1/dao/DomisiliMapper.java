package com.example.Tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.Tugas1.model.DomisiliModel;
import com.example.Tugas1.model.KecamatanModel;
import com.example.Tugas1.model.KelurahanModel;
import com.example.Tugas1.model.KotaModel;
import com.example.Tugas1.model.PendudukModel;



@Mapper
public interface DomisiliMapper {
	
	@Select("SELECT id,nama_kota FROM kota ")
	@Results(value= {
	@Result(property = "namaKota" , column="nama_kota"),
	@Result(property="id",column="id")
})
	List<KotaModel>selectKota();

	@Select("SELECT id,nama_kecamatan FROM kecamatan WHERE id_kota = ${idKota}")
	@Results(value= {
	@Result(property = "namaKecamatan" , column="nama_kecamatan"),
	@Result(property="id",column="id")
	})
	List<KecamatanModel>selectKecamatan(@Param("idKota") String idKota);
	
	@Select("SELECT id,nama_kelurahan FROM kelurahan where id_kecamatan = ${idKecamatan}")
	@Results(value= {
			@Result(property = "namaKelurahan" , column="nama_kelurahan"),
			@Result(property="id",column="id")
			})
	List<KelurahanModel>selectKelurahan(@Param("idKecamatan") String idKecamatan);
	
	@Select("SELECT nik,nama,jenis_kelamin FROM kelurahan,penduduk,keluarga "
			+ "where kelurahan.id=${idKelurahan} AND keluarga.id_kelurahan = kelurahan.id AND keluarga.id = penduduk.id_keluarga")
	@Results(value= {
			@Result(property = "nik" , column="nik"),
			@Result(property="nama",column="nama"),
			@Result(property="jenisKelamin" , column="jenis_kelamin")
			})
	List<PendudukModel>selectPenduduk(@Param("idKelurahan") String idKelurahan);
	
	@Select("SELECT nama_kelurahan , nama_kecamatan , nama_kota FROM keluarga,kecamatan, kelurahan,kota,penduduk WHERE nik=${nik} AND penduduk.id_keluarga = keluarga.id AND keluarga.id_kelurahan = kelurahan.id AND kelurahan.id_kecamatan = kecamatan.id AND kecamatan.id_kota = kota.id")
	@Results(value= {
			@Result(property = "namaKelurahan" , column="nama_kelurahan"),
			@Result(property="namaKecamatan",column="nama_kecamatan"),
			@Result(property="namaKota" , column="nama_kota")
			})
	DomisiliModel selectDomisili(@Param("nik")String nik);
	
	@Select("SELECT alamat, RT ,RW , nama_kelurahan , nama_kecamatan , nama_kota FROM keluarga,kecamatan,kelurahan,kota WHERE "
			+ "nomor_kk=${nomorKk} AND keluarga.id_kelurahan = kelurahan.id AND kelurahan.id_kecamatan = kecamatan.id AND kecamatan.id_kota = kota.id")
	@Results(value= {
			@Result(property = "namaKelurahan" , column="nama_kelurahan"),
			@Result(property="namaKecamatan",column="nama_kecamatan"),
			@Result(property="namaKota" , column="nama_kota")
			})
	DomisiliModel selectDomisiliNkk(@Param("nomorKk")String nomorKk);
}

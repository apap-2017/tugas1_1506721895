package com.example.Tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.Tugas1.model.KeluargaModel;
import com.example.Tugas1.model.PendudukModel;


@Mapper
public interface PendudukMapper {
	@Select("SELECT id , nik , nama , tempat_lahir , tanggal_lahir , jenis_kelamin , is_wni , id_keluarga , agama , pekerjaan , status_perkawinan ,"
			+ " status_dalam_keluarga , golongan_darah , is_wafat FROM penduduk WHERE nik =#{nik}")
	@Results(value= {
		@Result(property = "id" , column="id"),
		@Result(property = "nik" , column ="nik"),
		@Result(property = "nama" , column="nama"),
		@Result(property = "tempatLahir" , column="tempat_lahir"),
		@Result(property = "tanggalLahir" , column="tanggal_lahir"),
		@Result(property = "jenisKelamin" , column="jenis_kelamin"),
		@Result(property = "isWni" , column ="is_wni"),
		@Result(property = "idKeluarga" , column="id_keluarga"),
		@Result(property = "agama" , column="agama"),
		@Result(property = "pekerjaan" , column="pekerjaan"),
		@Result(property = "statusPerkawinan" , column="status_perkawinan"),
		@Result(property = "statusDalamKeluarga" , column="status_dalam_keluarga"),
		@Result(property = "golonganDarah" , column="golongan_darah"),
		@Result(property = "isWafat" , column="is_wafat"),
		@Result(property = "satuKeluarga" , column ="id_keluarga",
				javaType = List.class,
				many=@Many(select = "selectKeluargaById")),
		
		
	})
	PendudukModel selectPenduduk(@Param("nik") String nik);

	@Select("SELECT DISTINCT keluarga.alamat , keluarga.RT , keluarga.RW FROM keluarga , penduduk  "
			+ "WHERE keluarga.id= penduduk.id_keluarga AND penduduk.id_keluarga = #{id_keluarga}")

	List<KeluargaModel> selectKeluargaById(@Param("id_keluarga") int id_keluarga);
	
	@Insert("INSERT INTO penduduk (nik,nama, tempat_lahir,tanggal_lahir,jenis_kelamin,is_wni,id_keluarga,agama,pekerjaan,status_perkawinan,status_dalam_keluarga,golongan_darah,is_wafat) "
			+ "VALUES (#{nik},#{nama}, #{tempatLahir},#{tanggalLahir},#{jenisKelamin}, #{isWni},#{idKeluarga},#{agama},#{pekerjaan},#{statusPerkawinan},#{statusDalamKeluarga}, #{golonganDarah},  #{isWafat}) ")
	
		void addPenduduk(PendudukModel penduduk);
	
	@Select("SELECT kode_kecamatan FROM keluarga, kelurahan, kecamatan WHERE keluarga.id = #{id_keluarga} AND kelurahan.id = keluarga.id_kelurahan AND kecamatan.id = kelurahan.id_kecamatan")
		
	String selectKecamatan(@Param("id_keluarga")int id_keluarga);
	
	@Select("SELECT count(*) FROM  penduduk  where nik like #{nik}")
	int countKeluarga(@Param("nik") String nik );
	
	
	@Update("UPDATE penduduk SET nik =#{nik}, nama = #{nama} , tempat_lahir= #{tempatLahir} , tanggal_lahir=#{tanggalLahir}, jenis_kelamin=#{jenisKelamin}, is_wni=#{isWni},id_keluarga=#{idKeluarga},"
			+ "agama=#{agama},pekerjaan=#{pekerjaan},status_perkawinan=#{statusPerkawinan},status_dalam_keluarga=#{statusDalamKeluarga},is_wafat=#{isWafat} WHERE id = #{id}")
    void updatePenduduk(PendudukModel penduduk);
	
	@Update("UPDATE penduduk SET is_wafat =#{isWafat} where nik=#{nik}")
	void updateStatus(PendudukModel penduduk);
	
	@Update("UPDATE keluarga SET is_tidak_berlaku=#{isTidakBerlaku} where id=#{id}")
	void updateTidakBerlaku(KeluargaModel keluarga);
	
	@Select("SELECT count(*) from penduduk where id_keluarga = #{id} AND is_wafat=0")
	int countJumlahKeluarga(PendudukModel penduduk);
}

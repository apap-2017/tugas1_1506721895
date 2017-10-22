package com.example.Tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import com.example.Tugas1.model.KeluargaModel;
import com.example.Tugas1.model.PendudukModel;

@Mapper
public interface KeluargaMapper {
	@Select("SELECT DISTINCT keluarga.nomor_kk, keluarga.alamat , keluarga.RT , keluarga.RW FROM keluarga , penduduk  "
			+ "WHERE keluarga.id= penduduk.id_keluarga AND penduduk.nik = #{nik}")
	
	KeluargaModel selectKeluargaById(@Param("nik") String nik);
	
	@Select("SELECT keluarga.nomor_kk,keluarga.alamat,keluarga.RT,keluarga.RW, penduduk.nama , penduduk.nik , penduduk.jenis_kelamin , penduduk.tempat_lahir ,penduduk.tanggal_lahir , penduduk.agama , penduduk.status_perkawinan , penduduk.status_dalam_keluarga "
			+ "FROM penduduk , keluarga"
			+ " WHERE penduduk.id_keluarga = keluarga.id AND keluarga.nomor_kk = #{nomorKk}")
			@Results(value= {
			@Result(property = "satuKeluarga" , column ="nomor_kk",
					javaType = List.class,
				many=@Many(select = "selectKeluargaByNkk"))
		})		
	
	List<PendudukModel> selectKeluarga(@Param("nomorKk")String nomorKk);
	
	@Select("SELECT penduduk.nama , penduduk.nik , penduduk.jenis_kelamin , penduduk.tempat_lahir ,penduduk.tanggal_lahir , penduduk.agama ,"
			+ "penduduk.status_perkawinan , penduduk.status_dalam_keluarga,penduduk.pekerjaan,penduduk.is_wni "
			+ "FROM penduduk , keluarga"
			+ " WHERE penduduk.id_keluarga = keluarga.id AND keluarga.nomor_kk = #{nomorKk}")
	@Results(value= {
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
			@Result(property = "statusDalamKeluarga" , column="status_dalam_keluarga")	
		})
	
	List<PendudukModel>selectKeluargaByNkk(@Param("nomorKk") String nomorKk);
	

	@Insert("INSERT INTO keluarga (nomor_kk,alamat,RT,RW,id_kelurahan) "
			+ "VALUES (#{nomorKk},#{alamat},#{rt},#{rw}, #{idKelurahan})")
	
	void addKeluarga(KeluargaModel keluarga);
	
	@Select("SELECT kode_kecamatan FROM kecamatan WHERE nama_kecamatan= #{kecamatan}")
	
	String selectKecamatan(@Param("kecamatan")String kecamatan);
	
	@Select("SELECT count(*) from keluarga where nomor_kk like #{nkk}")
	int countKeluarga(@Param("nkk") String nkk );
	
	@Select("SELECT * FROM keluarga WHERE nomor_kk =#{nkk}")
	@Results(value= {
			@Result(property = "nomorKk" , column ="nomor_kk"),
			@Result(property = "alamat" , column="alamat"),
			@Result(property = "rt" , column="RT"),
			@Result(property = "rw" , column="RW"),
			@Result(property = "idKelurahan" , column="id_kelurahan"),	
		})
	KeluargaModel selectKeluargaNkk(@Param("nkk") String nkk);
	
	@Update("UPDATE keluarga SET alamat = #{alamat} , RT= #{rt} , RW=#{rw}, id_kelurahan=#{idKelurahan} WHERE id = #{id}")
	@Results(value= {
			@Result(property = "nomorKk" , column ="nomor_kk")
		})
    void updateKeluarga(KeluargaModel keluarga);
}

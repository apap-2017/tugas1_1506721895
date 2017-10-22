package com.example.Tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tugas1.dao.KeluargaMapper;
import com.example.Tugas1.model.KeluargaModel;
import com.example.Tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaSeviceDatabase implements KeluargaService {
	@Autowired
	KeluargaMapper keluargaMapper;
	
	@Override
	public List<PendudukModel> selectKeluargaByNkk(String nomorKk) {
		log.info("select penduduk with nkk {}" , nomorKk);
		return keluargaMapper.selectKeluargaByNkk(nomorKk);
	}
	@Override
	public KeluargaModel selectKeluargaById(String nik) {
		log.info(("select penduduk with id{}"),nik);
		return keluargaMapper.selectKeluargaById(nik);
	}
	public List<PendudukModel> selectKeluarga(String nomorKk) {
		log.info("select penduduk with nkk {}" , nomorKk);
		return keluargaMapper.selectKeluarga(nomorKk);
	}
	@Override
	public void addKeluarga(KeluargaModel keluarga) {
		log.info("add keluarga berhasil");
		keluargaMapper.addKeluarga(keluarga);
	}
	@Override
	public String selectKecamatan(String kecamatan) {
		log.info(("select kecamatan with nama{}"),kecamatan);
		return keluargaMapper.selectKecamatan(kecamatan);
	}
	@Override
	public int countKeluarga(String nkk) {
		log.info("count keluarga berhasil{} {}",keluargaMapper.countKeluarga(nkk),nkk);
		return keluargaMapper.countKeluarga(nkk);
	}
	@Override
	public KeluargaModel selectKeluargaNkk(String nkk) {
		log.info("select keluarga where nkk{} {}",nkk , keluargaMapper.selectKeluargaNkk(nkk));
		return keluargaMapper.selectKeluargaNkk(nkk);
	}
	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		log.info("update keluarga berhasil "+keluarga.getNomorKk()+keluarga.getId());
		keluargaMapper.updateKeluarga(keluarga);
		
	}
	
}

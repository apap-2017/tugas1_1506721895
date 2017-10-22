package com.example.Tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tugas1.dao.DomisiliMapper;
import com.example.Tugas1.model.DomisiliModel;
import com.example.Tugas1.model.KecamatanModel;
import com.example.Tugas1.model.KelurahanModel;
import com.example.Tugas1.model.KotaModel;
import com.example.Tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DomisiliServiceDatabase implements DomisiliService{
	@Autowired
	DomisiliMapper domisiliMapper;
	@Override
	public List<KotaModel> selectKota() {
		log.info("select kota dengan id{}");
		return domisiliMapper.selectKota();
	}
	@Override
	public List<KecamatanModel> selectKecamatan(String idKota) {
		log.info("select kecamatan with idKota {}",idKota);
		return domisiliMapper.selectKecamatan(idKota);
	}
	@Override
	public List<KelurahanModel> selectKelurahan(String idKecamatan) {
		log.info("select kelurahan with idKecamatan{}",idKecamatan);
		return domisiliMapper.selectKelurahan(idKecamatan);
	}
	@Override
	public List<PendudukModel> selectPenduduk(String idKelurahan) {
		log.info("select penduduk with idKelurahan{}",idKelurahan);
		return domisiliMapper.selectPenduduk(idKelurahan);
	}
	@Override
	public DomisiliModel selectDomisili(String nik) {
		log.info("select nama domisili with nik{}",nik);
		return domisiliMapper.selectDomisili(nik);
	}
	@Override
	public DomisiliModel selectDomisiliNkk(String nomorKk) {
		log.info("select nama domisili wih Nkk{}",nomorKk);
		return domisiliMapper.selectDomisiliNkk(nomorKk);
	}
	
}

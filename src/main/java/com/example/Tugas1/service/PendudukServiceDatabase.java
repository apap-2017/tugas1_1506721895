package com.example.Tugas1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Tugas1.dao.PendudukMapper;
import com.example.Tugas1.model.KeluargaModel;
import com.example.Tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	@Autowired
	PendudukMapper pendudukMapper;
	
	@Override
	public PendudukModel selectPenduduk(String nik)
	{
		log.info("SELECT penduduk with nik {}",nik);
		return pendudukMapper.selectPenduduk(nik);
	}
	@Override
	public void addPenduduk(PendudukModel penduduk)
	{
		log.info("add penduduk berhasil");
		pendudukMapper.addPenduduk(penduduk);
	}
	@Override
	public String selectKecamatan(int idKeluarga) {
		log.info("select kecamatan with id{}",idKeluarga);
		return pendudukMapper.selectKecamatan(idKeluarga);
	}
	@Override
	public int countKeluarga(String nik) {
		log.info("count keluarga berhasil{} {}",pendudukMapper.countKeluarga(nik),nik);
		
		return pendudukMapper.countKeluarga(nik);
	}
	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		log.info("update pendududuk berhasil "+penduduk.getNik() + penduduk.getAgama()+penduduk.getNama()+penduduk.getGolonganDarah()+penduduk.getIsWni());
		pendudukMapper.updatePenduduk(penduduk);
	}
	@Override
	public void updateStatus(PendudukModel penduduk) {
		log.info("update status berhasil untuk nik"+penduduk.getNik() +penduduk.getIsWafat());
		pendudukMapper.updateStatus(penduduk);
	}
	@Override
	public void updateTidakBerlaku(KeluargaModel keluarga) {
		log.info("update Tidak Berlaku berhasil untuk id keluarga" +keluarga.getId());
		pendudukMapper.updateTidakBerlaku(keluarga);
		
	}
	@Override
	public int countJumlahKeluarga(PendudukModel penduduk) {
		log.info("count jumlah keluarga berhasil"+penduduk.getId());
		return pendudukMapper.countJumlahKeluarga(penduduk);
	}
	
	
	
	
}

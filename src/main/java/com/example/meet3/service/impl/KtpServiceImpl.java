package com.example.meet3.service.impl;


import com.example.meet3.mapper.KtpMapper;
import com.example.meet3.model.dto.KtpAddRequest;
import com.example.meet3.model.dto.KtpDto;
import com.example.meet3.model.entity.Ktp;
import com.example.meet3.repository.KtpRepository;
import com.example.meet3.service.KtpService;
import com.example.meet3.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {
    @Autowired
    private KtpRepository ktpRepository;
    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public KtpDto addKtp(KtpAddRequest request) {
        validationUtil.validate(request);
        if (ktpRepository.findByNomorKtp(request.getNomorKtp()).isPresent()) {
            throw new RuntimeException("Nomor KTP already exists");
        }
        Ktp ktp = Ktp.builder()
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();
        ktpRepository.save(ktp);
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public List<KtpDto> getAllKtp() {
        return ktpRepository.findAll().stream()
                .map(KtpMapper.MAPPER::toKtpDtoData)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP not found"));
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public KtpDto updateKtp(Integer id, KtpAddRequest request) {
        validationUtil.validate(request);
        Ktp existingKtp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP not found"));
        existingKtp.setNomorKtp(request.getNomorKtp());
        existingKtp.setNamaLengkap(request.getNamaLengkap());
        existingKtp.setAlamat(request.getAlamat());
        existingKtp.setTanggalLahir(request.getTanggalLahir());
        existingKtp.setJenisKelamin(request.getJenisKelamin());
        ktpRepository.save(existingKtp);
        return KtpMapper.MAPPER.toKtpDtoData(existingKtp);
    }

    @Override
    public void deleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP not found"));
        ktpRepository.delete(ktp);
    }
}
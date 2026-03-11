package com.example.meet3.service;

import com.example.meet3.model.dto.KtpAddRequest;
import com.example.meet3.model.dto.KtpDto;
import java.util.List;

public interface KtpService {
    KtpDto addKtp(KtpAddRequest request);
    List<KtpDto> getAllKtp();
    KtpDto getKtpById(Integer id);
    KtpDto updateKtp(Integer id, KtpAddRequest request);
    void deleteKtp(Integer id);
}
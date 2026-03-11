package com.example.meet3.mapper;
import com.example.meet3.model.dto.KtpDto;
import com.example.meet3.model.entity.Ktp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KtpMapper {
    KtpMapper MAPPER = Mappers.getMapper(KtpMapper.class);
    KtpDto toKtpDtoData(Ktp ktp);
}
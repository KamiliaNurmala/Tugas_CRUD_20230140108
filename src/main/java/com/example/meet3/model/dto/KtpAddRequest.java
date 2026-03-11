// KtpAddRequest.java
package com.example.meet3.model.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class KtpAddRequest {
    @NotBlank(message = "Nomor KTP is required")
    private String nomorKtp;
    @NotBlank(message = "Nama is required")
    private String namaLengkap;
    private String alamat;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
}
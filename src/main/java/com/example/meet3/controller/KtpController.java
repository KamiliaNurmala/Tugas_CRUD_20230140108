package com.example.meet3.controller;
import com.example.meet3.model.dto.KtpAddRequest;
import com.example.meet3.service.KtpService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ktp")
public class KtpController {
    @Autowired
    private KtpService ktpService;

    @PostMapping
    public ResponseEntity<?> addKtp(@RequestBody KtpAddRequest request) {
        return ResponseEntity.ok(ktpService.addKtp(request));
    }

    @GetMapping
    public ResponseEntity<?> getAllKtp() {
        return ResponseEntity.ok(ktpService.getAllKtp());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getKtpById(@PathVariable Integer id) {
        return ResponseEntity.ok(ktpService.getKtpById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateKtp(@PathVariable Integer id, @RequestBody KtpAddRequest request) {
        return ResponseEntity.ok(ktpService.updateKtp(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKtp(@PathVariable Integer id) {
        ktpService.deleteKtp(id);
        return ResponseEntity.ok().build();
    }

    // --- ERROR HANDLERS ---

    // Menangani error dari logika bisnis (misal: "KTP not found")
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // Menangani error validasi (misal: Nomor KTP kosong)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(ConstraintViolationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Validation Failed: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
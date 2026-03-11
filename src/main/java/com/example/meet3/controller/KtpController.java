package com.example.meet3.controller;
import com.example.meet3.model.dto.KtpAddRequest;
import com.example.meet3.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
package com.project.back_end.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<Object> getPrescriptionByAppointment(@PathVariable("appointmentId") int appointmentId) {
        // Lấy thông tin đơn thuốc theo mã cuộc hẹn
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> createPrescription(@RequestBody Map<String, Object> prescriptionRequest) {
        // Bác sĩ kê đơn thuốc mới sau khi khám
        return ResponseEntity.status(HttpStatus.CREATED).body(prescriptionRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePrescription(@PathVariable("id") int id, @RequestBody Map<String, Object> prescriptionRequest) {
        // Chỉnh sửa đơn thuốc
        return ResponseEntity.ok().body(prescriptionRequest);
    }
}

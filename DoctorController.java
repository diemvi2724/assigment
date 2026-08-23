package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        // Trả về danh sách tất cả bác sĩ
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") int id) {
        // Trả về thông tin chi tiết bác sĩ theo ID
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        // Thêm bác sĩ mới (quyền Admin)
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") int id, @RequestBody Doctor doctor) {
        // Cập nhật thông tin bác sĩ
        return ResponseEntity.ok().body(doctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") int id) {
        // Xóa bác sĩ
        return ResponseEntity.noContent().build();
    }
}

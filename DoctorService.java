package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    public List<Doctor> getAllDoctors() {
        // Lấy danh sách tất cả bác sĩ
        return List.of();
    }

    public Optional<Doctor> getDoctorById(int doctorId) {
        // Tìm thông tin chi tiết bác sĩ theo ID
        return Optional.empty();
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        // Lọc danh sách bác sĩ theo chuyên khoa
        return List.of();
    }

    public Doctor saveDoctor(Doctor doctor) {
        // Lưu hoặc thêm mới thông tin bác sĩ
        return doctor;
    }

    public Doctor updateDoctor(int doctorId, Doctor doctorDetails) {
        // Cập nhật chuyên khoa, giá khám, thông tin liên hệ của bác sĩ
        return doctorDetails;
    }

    public void deleteDoctor(int doctorId) {
        // Xóa bác sĩ khỏi hệ thống
    }
}

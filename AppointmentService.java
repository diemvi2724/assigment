package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    public Appointment scheduleAppointment(Appointment appointment) {
        // Logic nghiệp vụ kiểm tra xung đột lịch và lưu lịch hẹn
        appointment.setStatus("PENDING");
        return appointment;
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        // Logic lấy danh sách lịch hẹn của bệnh nhân
        return List.of();
    }

    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        // Logic lấy danh sách lịch hẹn của bác sĩ
        return List.of();
    }

    public Optional<Appointment> getAppointmentById(int appointmentId) {
        // Logic lấy thông tin lịch hẹn theo ID
        return Optional.empty();
    }

    public Appointment updateStatus(int appointmentId, String newStatus) {
        // Logic cập nhật trạng thái lịch hẹn (CONFIRMED, COMPLETED, CANCELLED)
        return null;
    }

    public void cancelAppointment(int appointmentId) {
        // Logic hủy lịch hẹn
    }
}

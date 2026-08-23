package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.Doctor;
import com.project.back_end.repo.AppointmentRepository;
import com.project.back_end.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * Validates doctor login credentials.
     */
    public Optional<Doctor> validateDoctorLogin(String email, String password) {
        return doctorRepository.findByEmail(email)
                .filter(doctor -> doctor.getPassword().equals(password) && doctor.isActive());
    }

    /**
     * Retrieves available time slots for a specific doctor on a given date.
     */
    public List<String> getDoctorAvailability(Long doctorId, LocalDate date, String userRole) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with ID: " + doctorId));

        if (!doctor.isActive()) {
            return new ArrayList<>();
        }

        // Define standard clinic working hours (09:00 to 17:00, 1-hour slots)
        List<LocalTime> standardSlots = List.of(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0)
        );

        // Fetch existing appointments for the doctor on this date
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<Appointment> bookedAppointments = appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(
                doctorId, startOfDay, endOfDay
        );

        List<LocalTime> bookedTimes = bookedAppointments.stream()
                .filter(app -> !"CANCELLED".equalsIgnoreCase(app.getStatus()))
                .map(app -> app.getAppointmentTime().toLocalTime())
                .collect(Collectors.toList());

        // Return only available slots as formatted strings
        return standardSlots.stream()
                .filter(slot -> !bookedTimes.contains(slot))
                .map(LocalTime::toString)
                .collect(Collectors.toList());
    }

    public List<Doctor> getAllActiveDoctors() {
        return doctorRepository.findByActiveTrue();
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctorRepository.findBySpecialtyIgnoreCaseAndActiveTrue(specialty);
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> updateDoctor(Long id, Doctor doctorDetails) {
        return doctorRepository.findById(id).map(existingDoctor -> {
            existingDoctor.setName(doctorDetails.getName());
            existingDoctor.setSpecialty(doctorDetails.getSpecialty());
            existingDoctor.setConsultationFee(doctorDetails.getConsultationFee());
            existingDoctor.setBiography(doctorDetails.getBiography());
            return doctorRepository.save(existingDoctor);
        });
    }

    public boolean deactivateDoctor(Long id) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setActive(false);
            doctorRepository.save(doctor);
            return true;
        }).orElse(false);
    }
}

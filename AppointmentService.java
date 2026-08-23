package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.Doctor;
import com.project.back_end.models.Patient;
import com.project.back_end.repo.AppointmentRepository;
import com.project.back_end.repo.DoctorRepository;
import com.project.back_end.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public Appointment bookAppointment(Long patientId, Long doctorId, LocalDateTime appointmentDate, String reasonForVisit) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + patientId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with ID: " + doctorId));

        if (!doctor.isActive()) {
            throw new IllegalStateException("Cannot book an appointment with an inactive doctor.");
        }

        boolean isSlotTaken = appointmentRepository.existsByDoctorIdAndAppointmentDateAndStatusNot(
                doctorId, appointmentDate, Appointment.Status.CANCELLED
        );

        if (isSlotTaken) {
            throw new IllegalStateException("Doctor is already booked for this time slot.");
        }

        Appointment appointment = new Appointment(patient, doctor, appointmentDate, reasonForVisit);
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> updateAppointmentStatus(Long id, Appointment.Status newStatus, String clinicalNotes) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setStatus(newStatus);
            if (clinicalNotes != null) {
                appointment.setClinicalNotes(clinicalNotes);
            }
            return appointmentRepository.save(appointment);
        });
    }

    public boolean cancelAppointment(Long id) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setStatus(Appointment.Status.CANCELLED);
            appointmentRepository.save(appointment);
            return true;
        }).orElse(false);
    }
}

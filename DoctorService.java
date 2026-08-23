package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
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
            existingDoctor.setFirstName(doctorDetails.getFirstName());
            existingDoctor.setLastName(doctorDetails.getLastName());
            existingDoctor.setPhone(doctorDetails.getPhone());
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

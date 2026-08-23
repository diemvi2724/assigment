package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;
    private final TokenService tokenService;

    @Autowired
    public DoctorController(DoctorService doctorService, TokenService tokenService) {
        this.doctorService = doctorService;
        this.tokenService = tokenService;
    }

    /**
     * Retrieves doctor availability based on user role, doctor ID, date, and token.
     */
    @GetMapping("/{id}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable("id") Long doctorId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(value = "userRole", required = false) String userRole,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // Validate Token
        if (token == null || token.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denied: Missing authorization token");
        }

        String bearerToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String username = tokenService.extractUsername(bearerToken);

        if (!tokenService.validateToken(bearerToken, username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }

        // Validate Role Access
        String roleFromToken = tokenService.extractClaim(bearerToken, claims -> claims.get("role", String.class));
        String effectiveRole = (userRole != null && !userRole.isBlank()) ? userRole : roleFromToken;

        if (effectiveRole == null || (!effectiveRole.equalsIgnoreCase("PATIENT") && 
                                      !effectiveRole.equalsIgnoreCase("DOCTOR") && 
                                      !effectiveRole.equalsIgnoreCase("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized user role");
        }

        List<String> availableSlots = doctorService.getDoctorAvailability(doctorId, date, effectiveRole);
        return ResponseEntity.ok(availableSlots);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam(required = false) String specialty) {
        if (specialty != null && !specialty.isBlank()) {
            return ResponseEntity.ok(doctorService.getDoctorsBySpecialty(specialty));
        }
        return ResponseEntity.ok(doctorService.getAllActiveDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        return doctorService.updateDoctor(id, doctorDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateDoctor(@PathVariable Long id) {
        boolean deactivated = doctorService.deactivateDoctor(id);
        if (deactivated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

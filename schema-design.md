# Database Schema Design - Smart Clinic Management System

## 1. Database Tables Overview
* **Users:** Lưu thông tin tài khoản chung (Patient, Doctor, Admin).
* **Doctors:** Lưu thông tin bổ sung của Bác sĩ (chuyên khoa, giá khám).
* **Appointments:** Lưu lịch đặt hẹn khám.
* **Medical_Records:** Lưu hồ sơ bệnh án và đơn thuốc sau khi khám xong.

## 2. MySQL DDL Script

```sql
-- Bảng Users (Bệnh nhân, Bác sĩ, Admin)
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    role ENUM('PATIENT', 'DOCTOR', 'ADMIN') NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Doctors (Chi tiết bác sĩ)
CREATE TABLE Doctors (
    doctor_id INT PRIMARY KEY,
    specialty VARCHAR(100) NOT NULL,
    consultation_fee DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

-- Bảng Appointments (Lịch hẹn)
CREATE TABLE Appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATETIME NOT NULL,
    status ENUM('PENDING', 'CONFIRMED', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES Users(user_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id)
);

-- Bảng Medical Records (Bệnh án)
CREATE TABLE Medical_Records (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT NOT NULL UNIQUE,
    diagnosis TEXT NOT NULL,
    prescription TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id) ON DELETE CASCADE
);

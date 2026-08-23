# User Stories - Smart Clinic Management System

## 1. Patient Role
* **US-P01: User Registration & Profile Management**
  * *As a* Patient,
  * *I want to* create an account and update my personal/medical profile,
  * *So that* the clinic has accurate information for my records and appointments.
  * **Acceptance Criteria:**
    * Validates required fields (full name, email, phone, DOB, gender).
    * Prevents duplicate registrations with the same email.

* **US-P02: Search Doctors by Specialty & Availability**
  * *As a* Patient,
  * *I want to* search for doctors by medical specialty, consultation fees, and available dates,
  * *So that* I can find the right healthcare specialist for my condition.
  * **Acceptance Criteria:**
    * Filtering results by specialty and date returns real-time open slots.

* **US-P03: Book & Reschedule Appointments**
  * *As a* Patient,
  * *I want to* book, reschedule, or cancel a consultation appointment,
  * *So that* I can manage my medical visits conveniently.
  * **Acceptance Criteria:**
    * Prevents double-booking for the same time slot.
    * Sends confirmation email/notification upon successful booking.

---

## 2. Doctor Role
* **US-D01: Manage Consultation Schedule**
  * *As a* Doctor,
  * *I want to* define and adjust my working hours, slot durations, and days off,
  * *So that* patients can only book when I am available.
  * **Acceptance Criteria:**
    * Slot generator automatically creates available slots based on working hours.

* **US-D02: View Appointment Queue & Patient History**
  * *As a* Doctor,
  * *I want to* access today's appointment list and view past consultation notes,
  * *So that* I can deliver informed diagnoses and treatments.
  * **Acceptance Criteria:**
    * Displays chronologically ordered patient appointments with current status.

* **US-D03: Update Appointment Status & Add Clinical Notes**
  * *As a* Doctor,
  * *I want to* mark appointments as completed or no-show and record diagnosis/prescriptions,
  * *So that* the patient's medical history stays up to date.
  * **Acceptance Criteria:**
    * Saves diagnosis text and updates status to `COMPLETED` upon submission.

---

## 3. Admin Role
* **US-A01: Manage Medical Staff & Clinic Departments**
  * *As an* Admin,
  * *I want to* add, update, deactivate, and assign doctors to departments,
  * *So that* clinic operations and directory information remain accurate.
  * **Acceptance Criteria:**
    * Deactivated doctors can no longer receive new bookings.

* **US-A02: System-Wide Audit & Appointment Monitoring**
  * *As an* Admin,
  * *I want to* view all clinic appointments and override bookings in case of emergencies,
  * *So that* the clinic can handle unforeseen scheduling conflicts.
  * **Acceptance Criteria:**
    * Admin can reassign or cancel appointments with mandatory reason logging.

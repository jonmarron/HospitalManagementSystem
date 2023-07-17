package com.jontxu.HospitalManagementSystem.data;

import java.time.LocalDateTime;

public record Appointment(int id, LocalDateTime dateTime, Patient patient, Doctor doctor) {
}

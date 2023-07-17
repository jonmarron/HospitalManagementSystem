package com.jontxu.HospitalManagementSystem.data;

import java.time.LocalDate;

public record Patient(int id, String name, LocalDate birthDate) {
}

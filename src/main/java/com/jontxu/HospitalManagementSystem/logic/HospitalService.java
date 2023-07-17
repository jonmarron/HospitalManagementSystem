package com.jontxu.HospitalManagementSystem.logic;

import com.jontxu.HospitalManagementSystem.data.Appointment;
import com.jontxu.HospitalManagementSystem.data.Doctor;
import com.jontxu.HospitalManagementSystem.data.Patient;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HospitalService {
    public List<Patient> getPatientsByDoctor(Doctor doctor, List<Appointment> appointments) {
        return appointments.stream()
                .filter(appointment -> appointment.doctor().equals(doctor) && appointment.dateTime().isAfter(LocalDateTime.now()))
                .map(Appointment::patient)
                .collect(Collectors.toList());
    }

    public Patient getPatientWithMostAppointments(List<Appointment> appointments) {
        Map<Patient, Long> appointmentCounts = appointments.stream()
                .filter(appointment -> appointment.dateTime().isAfter(LocalDateTime.now().minusDays(30)))
                .collect(Collectors.groupingBy(Appointment::patient, Collectors.counting()));

        return appointmentCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Doctor getMostExperiencedDoctor(List<Doctor> doctors) {
        return doctors.stream()
                .max(Comparator.comparingInt(Doctor::expYears))
                .orElse(null);
    }
}

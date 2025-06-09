package com.microservice1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice1.dto.DoctorDTO;
import com.microservice1.feign.DoctorClient;
import com.microservice1.model.Appointment;
import com.microservice1.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private DoctorClient doctorClient;

    public Appointment bookAppointment(Appointment appointment) {
        // Validate doctor existence by calling Doctor Service
        try {
            DoctorDTO doctor = doctorClient.getDoctorById(appointment.getDoctorId());
            if (doctor == null || doctor.getId() == null) {
                throw new RuntimeException("Doctor not found with ID: " + appointment.getDoctorId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching doctor info: " + e.getMessage());
        }

        // Save appointment
        return repository.save(appointment);
    }

    public DoctorDTO getDoctorDetails(Long doctorId) {
        return doctorClient.getDoctorById(doctorId);
    }

    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }
}

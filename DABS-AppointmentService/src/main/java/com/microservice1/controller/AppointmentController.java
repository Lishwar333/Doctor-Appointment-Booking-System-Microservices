package com.microservice1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice1.dto.DoctorDTO;
import com.microservice1.model.Appointment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
 

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;
    
    
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return service.bookAppointment(appointment);
    }
    
    
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return service.getAllAppointments();
    }
    
    @HystrixCommand(fallbackMethod = "defaultDoctor")
    @GetMapping("/doctor/{doctorId}")
    public DoctorDTO getDoctor(@PathVariable Long doctorId) {
        return service.getDoctorDetails(doctorId);
    }

    public DoctorDTO defaultDoctor(Long doctorId) {
        DoctorDTO fallbackDoctor = new DoctorDTO();
        fallbackDoctor.setId(0L);
        fallbackDoctor.setName("No doctor available now.");
        fallbackDoctor.setSpecialization("N/A");
        return fallbackDoctor;
    }
    
}
package com.microservice1.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice1.dto.DoctorDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@FeignClient(name = "DOCTOR-SERVICE")
//@FeignClient(name = "DOCTOR-SERVICE", url = "http://localhost:9001")

public interface DoctorClient {

    @GetMapping("/api/doctors/{id}")
    //@HystrixCommand(fallbackMethod="getDefaultDoctor")
    DoctorDTO getDoctorById(@PathVariable("id") Long id);

//    default DoctorDTO getDefaultDoctor(Long id, Throwable ex) {
//        DoctorDTO doctor = new DoctorDTO();
//        doctor.setId(id);
//        doctor.setName("Doctor Service Unavailable");
//        doctor.setSpecialization("N/A");
//        return doctor;
//    }
    
    
}

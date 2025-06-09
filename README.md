# Doctor Appointment Booking System (Microservices)
This project is a Doctor Appointment Booking System built using Spring Boot and follows the Microservices Architecture. It demonstrates how multiple services can be connected to form a complete system, including managing doctor appointments and handling patient data.

Project Overview
The system consists of multiple microservices:

Doctor Service - Handles the management of doctors and their details.

Appointment Service - Manages patient appointments with doctors.

Config Server - Centralized configuration server for microservices.

Eureka Server - Service discovery server to allow microservices to discover and communicate with each other.

API Gateway - A single entry point to access all services.

Circuit Breaker - Handles faults in communication between services and provides fallback solutions.

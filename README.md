# Doctor Appointment Booking System (Microservices)

This project is a **Doctor Appointment Booking System** built using **Spring Boot** and follows the **Microservices Architecture**. It demonstrates how multiple services can be connected to form a complete system, including managing doctor appointments and handling patient data.

## Project Overview

The system consists of multiple microservices:

1. **Doctor Service** - Handles the management of doctors and their details.  
2. **Appointment Service** - Manages patient appointments with doctors.  
3. **Config Server** - Centralized configuration server for microservices.  
4. **Eureka Server** - Service discovery server to allow microservices to discover and communicate with each other.  
5. **API Gateway** - A single entry point to access all services.  
6. **Circuit Breaker** - Handles faults in communication between services and provides fallback solutions.

### Technologies Used:
- **Spring Boot**
- **Spring Cloud** (Eureka, Config Server, API Gateway, Circuit Breaker)
- **JPA** (Java Persistence API) for database interactions
- **MySQL** for the relational database

## How It Works

1. **Doctor Service** provides endpoints for managing doctor data.  
   - It allows adding, updating, and viewing doctor information.  
2. **Appointment Service** handles appointment creation, updating, and viewing for patients.  
   - It interacts with a MySQL database to store appointment details, including patient name, doctor ID, and appointment time.  
3. **API Gateway** acts as a front-facing gateway that routes requests to the appropriate microservice.  
   - It simplifies access to the system by abstracting multiple microservices behind a single entry point.  
4. **Eureka Server** is used for service discovery.  
   - It allows each microservice to register itself and discover other microservices dynamically, ensuring proper communication between services.  
5. **Circuit Breaker** ensures that if one microservice fails, the system can still continue to operate by using fallback methods.

## Database Schema

### Appointment Table:

The `appointment` table stores details of appointments made by patients.

- **id**: Primary key (auto-generated).
- **doctor_id**: Foreign key referencing the doctor.
- **patient_name**: Name of the patient.
- **appointment_time**: Date and time of the appointment.

```sql
CREATE TABLE appointment (
    id INT PRIMARY KEY,
    doctor_id INT,
    patient_name VARCHAR(100),
    appointment_time DATETIME
);

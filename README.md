# Doctor Appointment Booking System (Microservices)

This project is a **Doctor Appointment Booking System** built using **Spring Boot** and follows the **Microservices Architecture**. It demonstrates how multiple services can be connected to form a complete system for managing doctor appointments and handling patient data.

## Project Overview

The microservices archetecture of this system is composed of the following:

1.  **Appointment Service**: Manages patient appointments with doctors.
2.  **Doctor Service**: Handles the management of doctors and their details.
3.  **Config Server**: Provides centralized configuration for all microservices.
4.  **Eureka Server**: Acts as a service discovery server, allowing microservices to discover and communicate with each other dynamically.
5.  **API Gateway**: Serves as a single entry point for all client requests, routing them to the appropriate backend microservice.
6.  **Circuit Breaker**: Integrated within services using Hystrix to handle faults in communication between services, preventing cascading failures and providing fallback solutions.

### Technologies Used:

* **Spring Boot**: For building standalone, production-ready Spring applications.
* **Spring Cloud**: Provides tools for building common patterns in distributed systems (Eureka, Config Server, Spring Cloud Gateway, Circuit Breaker).
* **Spring Data JPA**: For simplified data access and persistence with relational databases.
* **H2**: The relational database used for storing application data.

## How It Works

* **Doctor Service**: Exposes REST endpoints for managing doctor information (e.g., adding new doctors, updating details, retrieving doctor profiles).
* **Appointment Service**: Manages the core appointment booking logic. It interacts with its own database to store appointment details (patient name, doctor ID, and appointment time). It also communicates with the Doctor Service **(via Feign Client)** to fetch doctor-related information and check availability.
* **API Gateway**: All client requests first hit the API Gateway. Based on predefined routing rules, the gateway forwards the requests to the correct microservice (e.g., requests to `/api/doctors` go to Doctor Service, `/api/appointments` go to Appointment Service).
* **Eureka Server**: Each microservice registers itself with the Eureka Server upon startup. When one microservice needs to call another (e.g., Appointment Service calling Doctor Service), it queries Eureka to find the available instances of the target service.
* **Circuit Breaker**: When a service dependency (like calling the Doctor Service from Appointment Service) experiences failures, the Circuit Breaker can temporarily block calls to the failing service, preventing resource exhaustion and offering a graceful degradation. For instance, if the Doctor Service is unresponsive, a fallback message like "No doctors available now" might be returned.

## API Endpoints

All endpoints are accessed via the API Gateway (`http://localhost:9100`). The base paths mentioned below are relative to the gateway's routing.

### Doctor Service Endpoints (routed via API Gateway to `/api/doctors`)

* **`POST /api/doctors`**: Used for adding a new doctor to the system.
    * **Purpose**: Register a new doctor who can then be booked for appointments.
    * **Request Body Example**: (Details depend on Doctor DTO)
        ```json
        {
          "name": "Dr. Alice Wonderland",
          "specialization": "Pediatrician",
          "phoneNumber": "9988776655"
        }
        ```
* **`GET /api/doctors/available`**: Retrieves a list of doctors currently marked as available.
    * **Purpose**: Allows patients to see which doctors are ready to take appointments.
    * **Fallback**: If the Doctor Service is down or unresponsive, a default message like **"No doctors available now."** will be returned due to the Circuit Breaker.
* **`GET /api/doctors/{id}`**: Retrieves detailed information for a specific doctor by their ID.
    * **Purpose**: Get all details for a particular doctor.

### Appointment Service Endpoints (routed via API Gateway to `/appointments`)

* **`GET /appointments`**: Retrieves a list of all existing appointments.
    * **Purpose**: View all scheduled appointments in the system.
* **`POST /appointments`**: Creates a new appointment.
    * **Purpose**: Book an appointment for a patient with a specific doctor at a specific time.
    * **Request Body Example**:
        ```json
        {
          "doctorId": 5,
          "patientName": "John Doe",
          "appointmentTime": "2025-06-10T10:30:00"
        }
       c
* **`GET /appointments/doctor/{doctorId}`**: Checks if a doctor with the given ID is available to take new appointments (e.g., if their schedule allows it, or if the doctor service confirms availability).
    * **Purpose**: Verify a doctor's immediate availability status for booking.

---

## Database Schema

### Appointment Table

The `appointment` table stores details of appointments made by patients.

* **id**: Primary key (auto-generated by the database).
* **doctor\_id**: Foreign key referencing the doctor.
* **patient\_name**: Name of the patient.
* **appointment\_time**: Date and time of the appointment.

### Doctor Table

The `doctor` table stores the details of doctors available in the system.

* **id**: Primary key (auto-generated by the database).
* **name**: Name of the doctor.
* **specialization**: Doctor's specialization (e.g., Cardiologist, Neurologist).
* **phone_number**: Doctor's contact phone number.

---

## Sample API Request

### To Create a New Appointment

**Endpoint:** `POST /appointments`

**Sample Request Body:**

```json
{
  "doctorId": 5,
  "patientName": "John Doe",
  "appointmentTime": "2025-06-10T10:30:00"
}
```

---

## How to Run the Project

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-repository.git
```

### Step 2: Open in Your IDE

Navigate to the project directory and open it in your preferred IDE (e.g., IntelliJ, Eclipse).

### Step 3: Setup Dependencies

- **Maven**: Used to build the project.

### Step 4: Run the Services

Start each service in the following order:

1. **Eureka Server**
2. **Config Server**
3. **API Gateway**
4. **Doctor Service**
5. **Appointment Service**

### Step 5: Test the Endpoints

Use **Postman** or any HTTP client to test the following:

- `GET /appointments` – Fetch all appointments.
- `POST /appointments` – Create a new appointment (using the sample request body above).

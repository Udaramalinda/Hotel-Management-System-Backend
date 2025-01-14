# Hotel Reservation and Management System Backend

This repository contains the backend for the Hotel Reservation and Management System, developed using **Spring Boot** and **PostgreSQL**. The system is designed to streamline hotel reservations, manage hotel registrations with room details, and provide customers with the best reservation options based on their budget and group size. 

[Frontend Repository for Hotel Reservation & Management System](https://github.com/Udaramalinda/Hotel-Management-System-Frontend)

## Features

### Core Features
- **Hotel Registration**: Allows hotels to register along with room details.
- **Reservation Management**: Customers can book rooms based on availability and preferences.
- **Recommendation Algorithm**: Suggests the best reservation options considering customer budget and group size.
- **Customer Management**: Handles customer details and reservation history.

### Security Features
- **JWT Authentication**: Secures API endpoints using JSON Web Tokens (JWT).
- **Role-Based Access Control (RBAC)**: Restricts access to specific resources and actions based on user roles:
  - **Admin**: Manages hotels, rooms, and user accounts.
  - **Hotel Manager**: Manages hotel-specific details and reservations.
  - **Customer**: Searches for hotels, makes reservations, and manages personal bookings.

---

## Tech Stack

- **Backend Framework**: Spring Boot
- **Database**: PostgreSQL
- **Security**: JWT Authentication, Role-Based Access Control
- **Build Tool**: Maven
- **Programming Language**: Java

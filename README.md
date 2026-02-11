# 🏭 Insee Cement Bin Monitoring System

A real-time web-based cement bin monitoring system built to help workers monitor cement bin weights remotely and manage valve operations efficiently.

---

## 📌 Project Overview

In cement manufacturing environments, bin levels are often monitored manually. This can lead to:

- Material overflow  
- Unexpected shortages  
- Delayed valve operations  
- Human errors  
- Lack of real-time visibility  

This system provides a centralized web platform that enables real-time monitoring of bin weights and secure worker access.

---

## 🎯 Purpose

To provide real-time visibility of cement bin weights, enabling efficient valve management, reducing manual monitoring errors, and improving operational decision-making.

---

## 🚀 Features

### 👷 Worker Module
- Secure login using email and password
- View real-time bin weight by entering bin number
- Monitor bin status (Full / Empty)

### 🧑‍💼 Management Module
- Register new workers
- Auto-generate secure passwords
- Email credentials to workers
- Role-based access control

### ⚙️ System Features
- Real-time bin weight monitoring
- REST API architecture
- Secure authentication with Spring Security
- Database integration with MySQL
- Layered backend architecture

---

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL

### Frontend
- React.js
- Axios

### Tools
- VS Code
- MySQL Workbench
- Maven
- Git

---

## 🏗️ Architecture
Controller Layer
Service Layer
Repository Layer
Entity Layer
Database (MySQL)

This ensures:
- Separation of concerns
- Scalability
- Maintainability
- Clean and structured code

---

## 🔐 Security

- Spring Security authentication
- Role-based authorization (Admin / Worker)
- Encrypted passwords
- Protected REST endpoints

---

## 🗄️ Database Design

Main tables include:

- workers
- bins
- weight_records

The system stores worker credentials securely and tracks bin weight data in real time.

---

## ⚙️ Installation & Setup

### 1️. Clone the repository

```bash
git clone https://github.com/your-username/insee-monitor.git
cd insee-monitor
```
The project follows a layered architecture:

### 2. Create MySQL Database

Open MySQL and run:
```bash
CREATE DATABASE insee;

CREATE USER 'insee'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON insee.* TO 'insee'@'localhost';
FLUSH PRIVILEGES;
```
### 3️. Configure application.properties

Open:
```bash
src/main/resources/application.properties
```

Add the following configuration:
```
spring.datasource.url=jdbc:mysql://localhost:3306/insee
spring.datasource.username=insee
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 4️. Run the Application
```bash
mvn spring-boot:run
```

The application will start on:
```
http://localhost:8080
```
#### Viewing Database Content

To view tables:
```
USE insee;
SHOW TABLES;
```

To view table data:
```
SELECT * FROM table_name;
```
### 📈 Future Improvements

Real-time WebSocket integration

Email/SMS alerts for bin thresholds

Historical analytics dashboard

IoT sensor integration

Mobile-friendly UI

## 👩‍💻 Author

Supuni Dhananji
Computer Engineering Undergraduate

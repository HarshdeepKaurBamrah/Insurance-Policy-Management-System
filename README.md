# Insurance Policy Management System

This project is a Spring Boot backend for a small insurance platform where users can browse policies, purchase a policy, and submit claims. It was designed to stay simple, readable, and easy to test during college evaluation.

## Project Highlights

- User management with full CRUD support
- Insurance policy catalog with active policy filtering
- Policy purchase flow with automatic start and end dates
- Claim submission with document link support
- Admin review flow for approving or rejecting claims
- Validation and structured exception handling
- H2 for quick testing and MySQL for database integration

## Tech Stack

- Java 17
- Spring Boot 3.5.13
- Spring Web
- Spring Data JPA
- Spring Validation
- Lombok
- H2 Database
- MySQL Connector
- Maven

## Package Structure

```text
com.insurance.insurance_policy_management_system
├── config
├── controller
├── dto
├── entity
│   └── enums
├── exception
├── repository
└── service
    └── impl
```

## What the Project Does

### User Module
- Create user
- View all users
- View user by id
- Update user
- Delete user

### Policy Module
- Create policy
- View all policies
- View active policies only
- Update policy
- Delete policy

### Purchase Module
- Purchase an active policy for a valid user
- Set purchase date automatically
- Set start date automatically
- Calculate end date from policy duration
- Keep purchase status as ACTIVE initially

### Claim Module
- Submit claim against a purchased policy
- Store claim reason, amount, incident date, and document URL
- Keep claim status as PENDING initially
- Allow admin to approve or reject the claim
- Save admin remarks during review

## Database Profiles

The project supports both H2 and MySQL through Spring profiles.

### Current Default Profile
- H2 is enabled by default in [src/main/resources/application.properties](src/main/resources/application.properties)

### H2 Config
- [src/main/resources/application-h2.properties](src/main/resources/application-h2.properties)
- In-memory database for fast testing
- H2 console is enabled

### MySQL Config
- [src/main/resources/application-mysql.properties](src/main/resources/application-mysql.properties)
- Use this if faculty wants to check MySQL integration

### Switch to MySQL
1. Open [src/main/resources/application.properties](src/main/resources/application.properties)
2. Change `spring.profiles.active=h2` to `spring.profiles.active=mysql`
3. Update MySQL username and password in [src/main/resources/application-mysql.properties](src/main/resources/application-mysql.properties)

## How to Run

### On Windows PowerShell
```powershell
.\mvnw.cmd spring-boot:run
```

### If Port 8080 Is Busy
```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments=--server.port=9090
```

### App URLs
- Application: `http://localhost:8080`
- H2 Console: `http://localhost:8080/h2-console`

If you use another port, replace `8080` with that port number.

## API Endpoints

### User APIs
- `POST /api/users`
- `GET /api/users`
- `GET /api/users/{id}`
- `PUT /api/users/{id}`
- `DELETE /api/users/{id}`

### Policy APIs
- `POST /api/policies`
- `GET /api/policies`
- `GET /api/policies/active`
- `GET /api/policies/{id}`
- `PUT /api/policies/{id}`
- `DELETE /api/policies/{id}`

### Purchase APIs
- `POST /api/purchases`
- `GET /api/purchases`
- `GET /api/purchases/{id}`
- `GET /api/purchases/user/{userId}`
- `PUT /api/purchases/{id}/status`

### Claim APIs
- `POST /api/claims`
- `GET /api/claims`
- `GET /api/claims/{id}`
- `GET /api/claims/user/{userId}`
- `PUT /api/claims/{id}/review`

## Postman Testing Order

Use this order for smooth testing:

1. Create a user
2. Create a policy
3. Verify active policies
4. Purchase the policy
5. Fetch purchased policies for the user
6. Submit a claim
7. Fetch claims for the user
8. Review the claim as admin
9. Fetch the reviewed claim by id
10. Test invalid requests for validation errors

## Sample Request Bodies

### Create User
```json
{
  "fullName": "Rahul Sharma",
  "email": "rahul.sharma@example.com",
  "phone": "9876543210",
  "address": "Pune, Maharashtra"
}
```

### Create Policy
```json
{
  "policyName": "Health Shield Gold",
  "policyType": "Health",
  "description": "Comprehensive family health insurance plan with cashless hospital coverage.",
  "premiumAmount": 15000,
  "coverageAmount": 500000,
  "durationInMonths": 12,
  "active": true
}
```

### Purchase Policy
```json
{
  "userId": 1,
  "policyId": 1
}
```

### Submit Claim
```json
{
  "purchasePolicyId": 1,
  "claimAmount": 25000,
  "reason": "Hospitalization due to accidental injury",
  "incidentDate": "2026-03-18",
  "documentUrl": "https://example.com/documents/claim-1.pdf"
}
```

### Review Claim
```json
{
  "status": "APPROVED",
  "adminRemarks": "Documents verified and claim approved"
}
```

### Update Purchased Policy Status
```json
{
  "status": "EXPIRED"
}
```

## Expected Response Style

### Success Response
The APIs return clean JSON objects or lists, for example:

```json
{
  "id": 1,
  "fullName": "Rahul Sharma",
  "email": "rahul.sharma@example.com",
  "phone": "9876543210",
  "address": "Pune, Maharashtra"
}
```

### Validation Error Response
When input is invalid, the response includes field-wise messages:

```json
{
  "timestamp": "2026-04-05T19:30:45.120",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/users",
  "validationErrors": {
    "email": "Email format is invalid",
    "fullName": "Full name is required"
  }
}
```

## Faculty Testing Checklist

Before evaluation, check these points:

- Start the application successfully
- Create at least one user
- Create at least one active policy
- Purchase the policy using valid user and policy ids
- Submit a claim only for an active purchased policy
- Review the claim as APPROVED or REJECTED
- Verify that invalid data returns readable validation errors
- Verify that missing records return resource-not-found errors

## Submission Notes

- The project uses DTOs instead of exposing entities directly.
- Controller, Service, and Repository layers are separated.
- Constructor injection is used across service and controller classes.
- H2 is included for easy local testing.
- MySQL is also supported for database evaluation.
- The code is intentionally kept simple so it is easy to explain in viva.

## GitHub Submission

If you need to push it to GitHub, create a repository and then run:

```powershell
git init
git add .
git commit -m "Initial commit - insurance policy management system"
git branch -M main
git remote add origin <your-github-repo-url>
git push -u origin main
```

## Short Viva Summary

This project follows a standard Spring Boot architecture. The controller layer handles HTTP requests, the service layer contains the business rules, and the repository layer communicates with the database. DTOs are used for requests and responses so the API stays clean and safe. Validation and global exception handling make the API suitable for testing in Postman and for final college submission.

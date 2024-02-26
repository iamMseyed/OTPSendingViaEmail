# User Registration and OTP Verification System

## Overview

This project implements a user registration system where users provide their details including name, email, password, and mobile number. Upon successful registration, an OTP (One-Time Password) is sent to the user's email address for verification. Additionally, during login, another OTP is sent for authentication before permitting access to the service.

## Endpoints

### 1. User Registration

- **URL**: `http://localhost:8080/api/register`
- **Method**: POST
- **Request Body**:
  ```json
  {
      "name": "string",
      "email": "string",
      "password": "string",
      "mobile": "string"
  }
  ```
- **Response**:  
  ```json
  {
      "message": "If email provided is valid, you will shortly receive an OTP to verify your account.",
      "status": "success"
  }
  ```
### 2. Email Verification
- **URL**: `http://localhost:8080/api/verify-OTP`
- **Method**: Get
- **Query Parameters**:
-  `email`: Email address provided during registeration
-  `otp`: otp received on registered email
- **Response**:
  - if Email not registered
      ```json
      {
        "message": "Email not registered!",
        "status": "error"
      }
  - If email registered but OTP not valid:
       ```json
      {
        "message": "Invalid OTP!",
        "status": "error"
      }
  - If everything correct:
      ```json
      {
        "message": "Email verified successfully!",
        "status": "success"
      }
  
### 3. OTP Sending for Login
- **URL**: `http://localhost:8080/api/send-otp-to-login`
- **Method**: Get
- **Query Parameters**:
-  `email`: Email address provided during registeration
- **Response**:
  - if Email not registered
     ```json
      {
        "message": "Email not registered!",
        "status": "error"
      }
       ```
  - If email registered but not verified
       ```json
      {
        "message": "Email not verified!",
        "status": "error"
      }
      ```
  - If everything correct:
      ``` json
      {
        "message": "OTP sent successfully!",
        "status": "success"
      }

### 4. OTP Verification for Login
- **URL**: `http://localhost:8080/api/verify-otp-to-login`
- **Method**: Get
- **Query Parameters**:
-  `email`: Email address provided during registeration
-  `otp`: OTP received by the user for login
- **Response**:
  - If email not registered
       ```json
      {
        "message": "Email not registered!",
        "status": "error"
      }
      ```
  - If email registered but not verified
       ```json
      {
        "message": "Email not verified!",
        "status": "error"
      }
      ```
  - If email registered but OTP not valid:
       ```json
      {
        "message": "Invalid OTP!",
        "status": "error"
      }
       ```
  - If everything correct:
      ```json
      {
        "message": "OTP sent successfully!",
        "status": "success"
      }
    
# Usage

## User Registration:

Send a POST request to `/api/register` with user details in the request body.
Upon successful registration, an OTP will be sent to the provided email address for verification.

## Email Verification:

Send a GET request to `/api/verify-OTP` with email and OTP parameters.
Verify the email address using the received OTP.

## OTP Sending for Login:

Send a GET request to `/api/send-otp-to-login` with the registered email address.
An OTP will be sent to the registered email address for login.

## OTP Verification for Login:

Send a GET request to `/api/verify-otp-to-login` with email and OTP parameters.
Verify the OTP for login authentication.

## Note

- Replace `http://localhost:8080` with the actual base URL of your server.
- Replace placeholder values such as `"string"`, `"name"`, `"email"`, `"password"`, `"mobile"`, etc., with actual data when making requests.

  

SOFTWARE REQUIREMENTS SPECIFICATION

(SRS)

Project: Resource Sharing Platform
Document: SOFTWARE REQUIREMENTS SPECIFICATION
Author: JAISRE R (7376232AD161)
Published on: 2026-02-03

1. INTRODUCTION
1.1 Purpose
This document describes the Software Requirements Specification (SRS) for the Resource
Sharing Platform.
The purpose of this document is to provide a clear and complete description of the system’s
functionality, constraints, and requirements. It serves as a reference for project
development, evaluation, and maintenance.
1.2 Document Conventions
The word “shall” indicates mandatory requirements
The word “should” indicates optional features
The document follows IEEE SRS guidelines
1.3 Intended Audience
This document is intended for:
Project Guide and Faculty
Evaluators
Developers
Students learning software engineering concepts
1.4 Product Scope
The Resource Sharing Platform is a web-based system that allows users to upload, share,
and download digital resources such as documents and study materials.
The system provides authentication, access control, and administrative moderation to
ensure secure and organized sharing of resources.

1.5 Definitions, Acronyms, and Abbreviations

Term Description

SRS Software Requirements Specification

UI User Interface

DB Database

API Application Programming Interface

MVC Model View Controller

CRUD Create, Read, Update, Delete

2. OVERALL DESCRIPTION
2.1 Product Perspective
The Resource Sharing Platform is a standalone web application developed using a
client–server architecture.
It does not depend on external systems and operates independently.
2.2 Product Functions
The main functions of the system include:
1. User registration and login
2. Uploading digital resources

3. Viewing and downloading resources
4. Admin moderation of uploaded content
5. Secure storage of files and metadata
2.3 User Classes and Characteristics

User
Class

Description

Admin Manages users and shared resources

User Uploads and accesses shared resources

2.4 Operating Environment
Client: Web browser (Chrome, Firefox, Edge)
Backend Server: Java Spring Boot
Database: MySQL
Operating System: Windows / Linux

2.5 Design and Implementation Constraints
Only authenticated users shall access resources
Files shall be stored on the server file system
Database shall store only file metadata
Internet connection is required

2.6 User Documentation
Basic user guidance will be provided through:
Simple UI labels
Error messages
User-friendly navigation
2.7 Assumptions and Dependencies
Users have valid login credentials
Uploaded content is legal and appropriate
Sufficient server storage is available
3. EXTERNAL INTERFACE REQUIREMENTS
3.1 User Interfaces
● A web-based authentication interface for user registration and login
● An interface for uploading and submitting digital resources
● An interface for browsing, viewing, and downloading shared resources
● An administrative interface for monitoring and managing users and shared
resources
● Search and filtering options for faster resource discovery
3.2 Hardware Interfaces
● A general-purpose or virtual server environment capable of running the application
and storing uploaded files
● Client devices such as desktops, laptops, or mobile systems with internet
connectivity and a web browser
3.3 Software Interfaces
Web browser
Database management system (MySQL)

3.4 Communication Interfaces
HTTP/HTTPS protocol for client-server communication
4. SYSTEM FEATURES
4.1 User Authentication
Description:
Allows users to register and log in securely.
Functional Requirements:
The system shall allow users to register with valid credentials
The system shall authenticate users during login
Unauthorized users shall be denied access
4.2 Resource Upload
Description:
Allows authenticated users to upload digital resources.
Functional Requirements:
The system shall accept file uploads using multipart requests
The system shall store uploaded files on the server
The system shall store file metadata in the database
4.3 Resource Viewing and Download
Description:
Allows users to view and download shared resources.
Functional Requirements:
The system shall display a list of available resources

The system shall allow users to download files securely
4.4 Admin Resource Management
Description:
Allows admin users to monitor and manage resources.
Functional Requirements:
Admins shall view all uploaded resources
Admins shall delete inappropriate resources
Admins shall manage user access
5. NON-FUNCTIONAL REQUIREMENTS
5.1 Performance Requirements
The system shall provide acceptable response time
Upload and download operations shall be efficient
5.2 Security Requirements
Passwords shall be stored securely
Role-based access control shall be enforced
Unauthorized access shall be prevented
5.3 Usability Requirements
The system shall have a simple and user-friendly interface
Users shall be able to navigate easily
5.4 Reliability Requirements
The system shall ensure data consistency
Uploaded files shall not be lost during normal operation

5.5 Maintainability Requirements
The system shall follow modular design
The application shall be easy to update and maintain
6. OTHER REQUIREMENTS
6.1 Database Requirements
Users table for authentication
Resources table for file metadata
Roles table for access control
6.2 Logging and Monitoring
Upload and download activities may be logged
Admin actions may be tracked for monitoring
7. IMPLEMENTATION DETAILS
7.1 Technology Stack
Backend: Java Spring Boot
Frontend: HTML, CSS, JavaScript
Database: MySQL
Architecture: MVC with RESTful APIs
7.2 Tools Used
IDE: Visual Studio Code / IntelliJ IDEA
Database Tool: MySQL Workbench
API Testing: Postman
Version Control: GitHub

8. Novelty
The novelty of the proposed Resource Sharing Platform lies in its structured and
requirement-driven design rather than the use of complex technologies. The system
emphasizes controlled access through role-based user management and maintains a clear
separation between file storage and metadata management. Unlike basic file-sharing
applications, the proposed platform is designed for academic and organizational
environments where moderation, accountability, and extensibility are important. This
approach makes the system reliable, scalable, and suitable for future enhancement.
9. USE CASES

Use Case 1: User Authentication and Role Assignment
(Role-Based Access Control – REQUIRED)

Field Description

Use Case Name User Authentication and Role Assignment

Actors User, Admin

Description Allows users to log in and be granted access based on assigned roles

Precondition User is registered in the system

Main Flow User enters credentials → System authenticates user → System
assigns role (Admin/User) → Access is granted based on role

Postcondition User is logged in with role-based permissions

Use Case 2: Upload Resource (Authorized User Only)
Field Description

Use Case
Name

Upload Resource

Actor User

Description Allows an authenticated user to upload a digital resource to the system

Precondition User is logged in and authorized

Main Flow User selects file → Submits upload request → System validates file →

System stores file and metadata

Postcondition Resource is uploaded successfully and available for access

Use Case 3: Administrative Resource Management
(Admin-Only Access)

Field Description

Use Case
Name

Administrative Resource Management

Actor Admin

Description Allows administrators to monitor, manage, and remove shared

resources

Precondition Admin is logged in

Main Flow Admin views resource list → Selects resource → Performs

management action (delete/disable)

Postcondition Resource state is updated in the system

10. FUTURE ENHANCEMENTS
● The Resource Sharing Platform is designed with extensibility in mind. The following
features may be considered for future development phases:
● Integration with cloud-based storage services for scalable file management
● Support for file preview functionality within the web interface
● Role-based access extensions with fine-grained permissions
● Activity logging and analytics for monitoring system usage

● These enhancements are not part of the current scope and may be implemented
based on future requirements.
11. CONCLUSION
The Resource Sharing Platform is proposed as a web-based solution to address the need for
secure and organized sharing of digital resources. This Software Requirements
Specification document defines the functional and non-functional requirements that will
guide the design and development of the system. The platform is intended to support
controlled access, efficient resource management, and administrative moderation.
The proposed system will follow standard software engineering principles and a modular
architectural approach to ensure clarity, maintainability, and scalability. This document will
serve as a baseline reference during the implementation, testing, and future enhancement
phases of the project.

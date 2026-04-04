/*create database EduLink;
use EduLink;

-- User table
CREATE TABLE User (
    UserID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Role VARCHAR(50),
    Email VARCHAR(150) UNIQUE,
    Phone VARCHAR(20),
    Status VARCHAR(20)
);

-- AuditLog table
CREATE TABLE AuditLog (
    AuditID INT PRIMARY KEY,
    UserID INT,
    Action VARCHAR(100),
    Resource VARCHAR(100),
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Student table
CREATE TABLE Student (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    DOB DATE,
    Gender VARCHAR(10),
    Address VARCHAR(255),
    ContactInfo VARCHAR(100),
    EnrollmentDate DATE,
    Status VARCHAR(20)
);

-- StudentDocument table
CREATE TABLE StudentDocument (
    DocumentID INT PRIMARY KEY,
    StudentID INT,
    DocType VARCHAR(50),
    FileURI VARCHAR(255),
    UploadedDate DATE,
    VerificationStatus VARCHAR(20),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID)
);

-- Course table
CREATE TABLE Course (
    CourseID INT PRIMARY KEY,
    Title VARCHAR(100),
    Subject VARCHAR(50),
    GradeLevel VARCHAR(20),
    Credits INT,
    Status VARCHAR(20)
);

-- Class table
CREATE TABLE Class (
    ClassID INT PRIMARY KEY,
    CourseID INT,
    TeacherID INT,
    Schedule VARCHAR(100),
    Status VARCHAR(20),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID),
    FOREIGN KEY (TeacherID) REFERENCES User(UserID)
);

-- LearningMaterial table
CREATE TABLE LearningMaterial (
    MaterialID INT PRIMARY KEY,
    CourseID INT,
    Title VARCHAR(100),
    FileURI VARCHAR(255),
    UploadedDate DATE,
    Status VARCHAR(20),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
);

-- Assignment table
CREATE TABLE Assignment (
    AssignmentID INT PRIMARY KEY,
    CourseID INT,
    StudentID INT,
    Title VARCHAR(100),
    SubmissionDate DATE,
    Status VARCHAR(20),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID)
);

-- Exam table
CREATE TABLE Exam (
    ExamID INT PRIMARY KEY,
    CourseID INT,
    Type VARCHAR(50),
    Date DATE,
    Status VARCHAR(20),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
);

-- Grade table
CREATE TABLE Grade (
    GradeID INT PRIMARY KEY,
    ExamID INT,
    StudentID INT,
    Score DECIMAL(5,2),
    Grade VARCHAR(5),
    Status VARCHAR(20),
    FOREIGN KEY (ExamID) REFERENCES Exam(ExamID),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID)
);

-- Attendance table
CREATE TABLE Attendance (
    AttendanceID INT PRIMARY KEY,
    StudentID INT,
    ClassID INT,
    Date DATE,
    Status VARCHAR(20),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (ClassID) REFERENCES Class(ClassID)
);

-- PerformanceMetric table
CREATE TABLE PerformanceMetric (
    MetricID INT PRIMARY KEY,
    StudentID INT,
    CourseID INT,
    Score DECIMAL(5,2),
    Date DATE,
    Status VARCHAR(20),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
);

-- ComplianceRecord table
CREATE TABLE ComplianceRecord (
    ComplianceID INT PRIMARY KEY,
    EntityID INT,
    Type VARCHAR(50),
    Result VARCHAR(50),
    Date DATE,
    Notes VARCHAR(255)
);

-- Audit table
CREATE TABLE Audit (
    AuditID INT PRIMARY KEY,
    OfficerID INT,
    Scope VARCHAR(100),
    Findings VARCHAR(255),
    Date DATE,
    Status VARCHAR(20),
    FOREIGN KEY (OfficerID) REFERENCES User(UserID)
);

-- Report table
CREATE TABLE Report (
    ReportID INT PRIMARY KEY,
    Scope VARCHAR(100),
    Metrics VARCHAR(255),
    GeneratedDate DATE
);

-- Notification table
CREATE TABLE Notification (
    NotificationID INT PRIMARY KEY,
    UserID INT,
    EntityID INT,
    Message VARCHAR(255),
    Category VARCHAR(50),
    Status VARCHAR(20),
    CreatedDate DATE,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);
use EduLink;*/
/*set FOREIGN_KEY_CHECKS=0;

DROP TABLE user;
CREATE TABLE User (
    UserID INT  auto_increment PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Role VARCHAR(50),
    Email VARCHAR(150) UNIQUE,
    Phone VARCHAR(20),
    Status VARCHAR(20),
    Password varchar(100)
);
set FOREIGN_KEY_CHECKS=1;
select * from user;
create database EduLink;
show databases;

use EduLink;
show tables;
select * from users;
show tables;*/
select * from audit_logs;
select * from users;

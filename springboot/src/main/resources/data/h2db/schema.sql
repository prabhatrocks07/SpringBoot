DROP TABLE IF EXISTS EMPLOYEE;

CREATE TABLE EMPLOYEE (
  emp_Id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_Name VARCHAR(18),
  last_Name VARCHAR(32) NOT NULL,
  email VARCHAR(50) NOT NULL,
  age INT,
  deptID INT,
  salary BIGINT
);
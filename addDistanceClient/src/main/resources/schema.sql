DROP TABLE IF EXISTS TBL_EMPLOYEES;

CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  point_a VARCHAR(250) NOT NULL,
  point_b VARCHAR(250) NOT NULL,
  distance VARCHAR(250) DEFAULT NULL
);
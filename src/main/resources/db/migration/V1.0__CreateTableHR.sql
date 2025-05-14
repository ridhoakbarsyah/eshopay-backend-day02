DROP TABLE IF EXISTS hr.dependents;
DROP TABLE IF EXISTS hr.employees;
DROP TABLE IF EXISTS hr.departments;
DROP TABLE IF EXISTS hr.locations;
DROP TABLE IF EXISTS hr.countries;
DROP TABLE IF EXISTS hr.regions;
DROP TABLE IF EXISTS hr.jobs;

CREATE TABLE hr.regions (
	region_id SERIAL PRIMARY KEY,
	region_name CHARACTER VARYING (25)
);

CREATE TABLE hr.countries (
	country_id CHARACTER (2) PRIMARY KEY,
	country_name CHARACTER VARYING (40),
	region_id INTEGER NOT NULL,
	FOREIGN KEY (region_id) REFERENCES hr.regions (region_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE hr.locations (
	location_id SERIAL PRIMARY KEY,
	street_address CHARACTER VARYING (40),
	postal_code CHARACTER VARYING (12),
	city CHARACTER VARYING (30) NOT NULL,
	state_province CHARACTER VARYING (25),
	country_id CHARACTER (2) NOT NULL,
	FOREIGN KEY (country_id) REFERENCES hr.countries (country_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE hr.departments (
	department_id SERIAL PRIMARY KEY,
	department_name CHARACTER VARYING (30) NOT NULL,
	location_id INTEGER,
	FOREIGN KEY (location_id) REFERENCES hr.locations (location_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE hr.jobs (
	job_id SERIAL PRIMARY KEY,
	job_title CHARACTER VARYING (35) NOT NULL,
	min_salary NUMERIC (8, 2),
	max_salary NUMERIC (8, 2)
);

CREATE TABLE hr.employees (
	employee_id SERIAL PRIMARY KEY,
	first_name CHARACTER VARYING (20),
	last_name CHARACTER VARYING (25) NOT NULL,
	email CHARACTER VARYING (100) NOT NULL,
	phone_number CHARACTER VARYING (20),
	hire_date DATE NOT NULL,
	job_id INTEGER NOT NULL,
	salary NUMERIC (8, 2) NOT NULL,
	manager_id INTEGER,
	department_id INTEGER,
	FOREIGN KEY (job_id) REFERENCES hr.jobs (job_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (department_id) REFERENCES hr.departments (department_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (manager_id) REFERENCES hr.employees (employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE hr.dependents (
	dependent_id SERIAL PRIMARY KEY,
	first_name CHARACTER VARYING (50) NOT NULL,
	last_name CHARACTER VARYING (50) NOT NULL,
	relationship CHARACTER VARYING (25) NOT NULL,
	employee_id INTEGER NOT NULL,
	FOREIGN KEY (employee_id) REFERENCES hr.employees (employee_id) ON DELETE CASCADE ON UPDATE CASCADE
);
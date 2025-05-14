
alter table hr.regions
add column created_date timestamp default current_timestamp,
add column modified_date timestamp;

alter table hr.countries
add column created_date timestamp default current_timestamp,
add column modified_date timestamp;

alter table hr.locations
add column created_date timestamp default current_timestamp,
add column modified_date timestamp;

alter table hr.departments
add column created_date timestamp default current_timestamp,
add column modified_date timestamp;

alter table hr.jobs
add column created_date timestamp default current_timestamp,
add column modified_date timestamp;

alter table hr.employees
add column created_date timestamp default current_timestamp,
add column modified_date timestamp;

alter table hr.dependents
add column created_date timestamp default current_timestamp,
add column modified_date timestamp;
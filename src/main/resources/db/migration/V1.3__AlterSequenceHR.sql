--alter sequence
alter sequence hr.regions_region_id_seq
restart with 5 increment by 1;

alter sequence hr.employees_employee_id_seq
restart with 100 increment by 1;

alter sequence hr.departments_department_id_seq
restart with 11 increment by 1;
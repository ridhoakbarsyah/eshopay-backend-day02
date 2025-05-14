package com.codeid.eshopay.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees",schema = "hr")
public class Employee extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private Integer employeeId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="hire_date")
    private LocalDate hireDate;

    @Column(name="job_id")
    private int jobId;

    @Column(name="salary")
    private double salary;

    @Column(name="manager_id",nullable = true)
    private Long managerId;

    @Column(name="photo",nullable = true)
    private String photo;

    @ManyToOne
    @JoinColumn(name="department_id") //foreign key
    private Department department;
}

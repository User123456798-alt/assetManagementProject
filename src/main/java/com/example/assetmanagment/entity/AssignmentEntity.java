package com.example.assetmanagment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "asset_assignment")
public class AssignmentEntity {

    @Id
    @Column(name = "entity_id")
    private int id;

    @Column(name = "employee_id")
    private BigInteger employeeId;

    @Column(name = "asset_id")
    private BigInteger assetId;

    @Column(name = "assigned_date")
    private Date assignedDate;

    @Column(name = "returned_date")
    private Date returnedDate;
}

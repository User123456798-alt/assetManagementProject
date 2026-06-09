package com.example.assetmanagment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "asset")
public class AssetEntity {

    @Id
    @Column(name = "entity_id")
    private int id;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "asset_type")
    private String assetType;

    @Column(name = "serial_number")
    private long serialNumber;

    @Column(name = "status")
    private String status;

    public AssetEntity(){
    }
}

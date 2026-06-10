package com.example.assetmanagment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@AllArgsConstructor
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

    public void returned(){
        this.status = "AVAILABLE";
    }

    public void checkOut(){
        this.status = "ASSIGNED";
    }
}

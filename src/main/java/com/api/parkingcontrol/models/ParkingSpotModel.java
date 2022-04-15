package com.api.parkingcontrol.models;

import com.api.parkingcontrol.dtos.ParkingSpotDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "ParkingSpots")
public class ParkingSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    protected ParkingSpotModel() { }

    public ParkingSpotModel(ParkingSpotDto parkingSpotDto) {
        parkingSpotNumber = parkingSpotDto.getParkingSpotNumber();
        carLicensePlate = parkingSpotDto.getCarLicensePlate();
        carBrand = parkingSpotDto.getCarBrand();
        carModel = parkingSpotDto.getCarModel();
        carColor = parkingSpotDto.getCarColor();
        registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
        responsibleName = parkingSpotDto.getResponsibleName();
        apartment = parkingSpotDto.getApartment();
        block = parkingSpotDto.getBlock();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;
    @Column(nullable = false, unique = true, length = 7)
    private String carLicensePlate;
    @Column(nullable = false, length = 70)
    private String carBrand;
    @Column(nullable = false, length = 70)
    private String carModel;
    @Column(nullable = false, length = 70)
    private String carColor;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @Column(nullable = false, length = 130)
    private String responsibleName;
    @Column(nullable = false, length = 30)
    private String apartment;
    @Column(nullable = false, length = 30)
    private String block;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void update(ParkingSpotDto parkingSpotDto) {
        parkingSpotNumber = parkingSpotDto.getParkingSpotNumber();
        carLicensePlate = parkingSpotDto.getCarLicensePlate();
        carBrand = parkingSpotDto.getCarBrand();
        carModel = parkingSpotDto.getCarModel();
        carColor = parkingSpotDto.getCarColor();
        responsibleName = parkingSpotDto.getResponsibleName();
        apartment = parkingSpotDto.getApartment();
        block = parkingSpotDto.getBlock();
    }
}

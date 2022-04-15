package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.InvalidObjectException;
import java.util.UUID;

public interface IParkingSpotService {

    ParkingSpotModel save(ParkingSpotDto parkingSpotDto) throws InvalidObjectException;

    Page<ParkingSpotModel> findAll(Pageable pageable);

    ParkingSpotModel findById(UUID id) throws InvalidObjectException;

    void delete(UUID id) throws InvalidObjectException;

    ParkingSpotModel update(UUID id, ParkingSpotDto parkingSpotDto) throws InvalidObjectException;
}

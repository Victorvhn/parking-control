package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.IParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InvalidObjectException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService implements IParkingSpotService {

    final IParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(
          IParkingSpotRepository parkingSpotRepository
    ) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    @Transactional
    public ParkingSpotModel save(ParkingSpotDto parkingSpotDto) throws InvalidObjectException {
        validate(parkingSpotDto);

        var parkingSpotModel = new ParkingSpotModel(parkingSpotDto);

        return parkingSpotRepository.save(parkingSpotModel);
    }

    @Override
    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    @Override
    public ParkingSpotModel findById(UUID id) throws InvalidObjectException {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotRepository.findById(id);

        if (!parkingSpotModelOptional.isPresent()) {
            throw new InvalidObjectException("Parking Spot not found.");
        }

        return parkingSpotModelOptional.get();
    }

    @Override
    @Transactional
    public void delete(UUID id) throws InvalidObjectException {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotRepository.findById(id);

        if (!parkingSpotModelOptional.isPresent()) {
            throw new InvalidObjectException("Parking Spot not found.");
        }

        parkingSpotRepository.delete(parkingSpotModelOptional.get());
    }

    @Override
    @Transactional
    public ParkingSpotModel update(UUID id, ParkingSpotDto parkingSpotDto) throws InvalidObjectException {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotRepository.findById(id);

        if (!parkingSpotModelOptional.isPresent()) {
            throw new InvalidObjectException("Parking Spot not found.");
        }

        var parkingSpotModel = parkingSpotModelOptional.get();

        validate(parkingSpotDto);

        parkingSpotModel.update(parkingSpotDto);

        return parkingSpotRepository.save(parkingSpotModel);
    }

    private void validate(ParkingSpotDto parkingSpotDto) throws InvalidObjectException {
        if (parkingSpotRepository.existsByCarLicensePlate(parkingSpotDto.getCarLicensePlate())) {
            throw new InvalidObjectException("Conflict: Car License Plate is already in use!");
        }

        if (parkingSpotRepository.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            throw new InvalidObjectException("Conflict: Parking Spot is already in use!");
        }

        if (parkingSpotRepository.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            throw new InvalidObjectException("Conflict: Parking Spot already registered for this apartment/block!");
        }
    }
}

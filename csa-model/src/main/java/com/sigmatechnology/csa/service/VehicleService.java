package com.sigmatechnology.csa.service;

import com.sigmatechnology.csa.entity.vehicle.Vehicle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
@Transactional
public interface VehicleService {

    Vehicle createVehicle(Vehicle vehicle);
    Vehicle updateVehicle(Vehicle vehicle);
    void deleteVehicle(Vehicle vehicle);
    List<Vehicle> listAll();
    Vehicle fetchVehicle(long id);
}

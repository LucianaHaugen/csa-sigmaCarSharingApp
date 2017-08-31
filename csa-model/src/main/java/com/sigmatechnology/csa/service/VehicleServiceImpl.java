package com.sigmatechnology.csa.service;

import com.sigmatechnology.csa.entity.vehicle.Vehicle;
import com.sigmatechnology.csa.repository.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleDao vehicleDao;

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleDao.create(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleDao.update(vehicle);
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        vehicleDao.delete(vehicleDao.find(vehicle.getId()));
    }

    @Override
    public List<Vehicle> listAll() {
        return vehicleDao.listAll();
    }

    @Override
    public Vehicle fetchVehicle(long id) {
        return vehicleDao.find(id);
    }
}

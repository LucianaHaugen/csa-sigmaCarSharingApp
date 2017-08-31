package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.vehicle.Vehicle;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
public interface VehicleDao extends CrudDao<Vehicle>{
    public Vehicle getVehicle(long vehicleId);
    public List<Vehicle> findByIds(List<Long> ids);
    public List<Vehicle> fetchAll(List<Long> ids);
    public Vehicle fetchByReg(String reg);
}

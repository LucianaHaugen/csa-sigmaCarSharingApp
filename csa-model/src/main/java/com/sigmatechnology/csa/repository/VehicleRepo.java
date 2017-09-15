package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.vehicle.Vehicle;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
@Repository
public class VehicleRepo extends CrudRepo<Vehicle> implements VehicleDao{
    @Override
    public Vehicle getVehicle(long vehicleId) {
        return entityManager
                .createQuery("SELECT v FROM Vehicle i WHERE v.id=:vehicleId", Vehicle.class)
                .setParameter("vehicleId", vehicleId)
                .getSingleResult();
    }

    @Override
    public List<Vehicle> findByIds(List<Long> ids) {
        return entityManager
                .createNamedQuery(Vehicle.FIND_BY_IDS, Vehicle.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<Vehicle> fetchAll(List<Long> ids) {
        return entityManager
                .createNamedQuery(Vehicle.FETCH_ALL, Vehicle.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public Vehicle fetchByReg(String reg) {
        return entityManager
                .createNamedQuery(Vehicle.FETCH_BY_REG, Vehicle.class)
                .setParameter("reg", reg)
                .getSingleResult();
    }
}

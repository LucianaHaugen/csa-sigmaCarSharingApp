package com.sigmatechnology.csa.controller;



import com.sigmatechnology.csa.json.Vehicle;
import com.sigmatechnology.csa.service.VehicleService;
import com.sigmatechnology.csa.util.MappingsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lucianahaugen on 07/09/17.
 */
@RestController
public class VehicleController extends AbstractController{

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET, value = "/vehicles")
    @ResponseBody
    public List<Vehicle> listAll(){

        return MappingsUtil.mapVehiclesToVehicles(vehicleService.listAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vehicle/{id}")
    @ResponseBody
    public Vehicle getVehicle(@PathVariable("id") long id){

        return MappingsUtil.mapVehicleToVehicle(vehicleService.fetchVehicle(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/vehicles")
    @ResponseBody
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public Vehicle create(@RequestBody Vehicle vehicle){

        return MappingsUtil.mapVehicleToVehicle(
            vehicleService.createVehicle(MappingsUtil.mapVehicleToVehicleEntity(vehicle, null)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/vehicle")
    @ResponseBody
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public Vehicle update(@RequestBody Vehicle updatedVehicle){
        com.sigmatechnology.csa.entity.vehicle.Vehicle vehicle =
            MappingsUtil.mapVehicleToVehicleEntity(
            updatedVehicle, vehicleService.fetchVehicle(updatedVehicle.getId()));

        return MappingsUtil.mapVehicleToVehicle(vehicleService.updateVehicle(vehicle));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/vehicle/{id}")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public void delte(@PathVariable("id") long id){
        com.sigmatechnology.csa.entity.vehicle.Vehicle vehicle = vehicleService.fetchVehicle(id);
        vehicleService.deleteVehicle(vehicle);

    }
}

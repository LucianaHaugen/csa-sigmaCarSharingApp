package com.sigmatechnology.csa.entity.vehicle;

import com.sigmatechnology.csa.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by lucianahaugen on 31/08/17.
 */

@NamedQueries({
        @NamedQuery(
                name = Vehicle.FIND_BY_IDS,
                query = "SELECT v from Vehicle v WHERE v.Id IN :Ids"
        )
})

@Entity
public class Vehicle extends AbstractBaseEntity implements Serializable {

    public static final String FIND_BY_IDS = "FindByIds";
    public static final String FETCH_ALL = "FetchAll";
    public static final String FETCH_BY_REG = "FetchByReg";

    private Long vehicleId;
    private String reg;
    private int year;
    private double mileage;
    private String body;
    private String equipment;
    private String model;
    private String fuel;
    private String site;
    private String responsible;
    private Image vehicleImage;

    public Vehicle() {
    }

    public Vehicle(Long vehicleId, String reg, int year, double mileage, String body, String equipment, String model, String fuel, String site, String responsible, Image vehicleImage) {
        this.vehicleId = vehicleId;
        this.reg = reg;
        this.year = year;
        this.mileage = this.mileage;
        this.body = body;
        this.equipment = equipment;
        this.model = model;
        this.fuel = fuel;
        this.site = site;
        this.responsible = responsible;
        this.vehicleImage = vehicleImage;
    }

    public Long getVehicleId() {
        return vehicleId;
    }


    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public Image getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(Image vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", reg='" + reg + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", body='" + body + '\'' +
                ", equipment='" + equipment + '\'' +
                ", model='" + model + '\'' +
                ", fuel='" + fuel + '\'' +
                ", site='" + site + '\'' +
                ", responsible='" + responsible + '\'' +
                ", vehicleImage=" + vehicleImage +
                '}';
    }
}

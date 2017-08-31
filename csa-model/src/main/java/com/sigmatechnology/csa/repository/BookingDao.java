package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.booking.Booking;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
public interface BookingDao extends CrudDao<Booking>{
    public List<Booking> findByIds(List<Long> ids);
    public List<Booking> fetchAll(List<Long> ids);
    public List<Booking> fetchByNames(List<String> names);
}

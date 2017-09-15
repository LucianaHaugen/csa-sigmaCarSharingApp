package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.booking.Booking;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
public class BookingRepo extends CrudRepo<Booking> implements BookingDao{

    @Override
    public List<Booking> findByIds(List<Long> ids) {
        return entityManager
                .createNamedQuery(Booking.FIND_BY_IDS, Booking.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<Booking> fetchAll(List<Long> ids) {
        return entityManager
                .createNamedQuery(Booking.FETCH_ALL, Booking.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<Booking> fetchByNames(List<String> names) {
        return entityManager
                .createNamedQuery(Booking.FETCH_BY_ERRAND, Booking.class)
                .setParameter("errand", names)
                .getResultList();
    }
}

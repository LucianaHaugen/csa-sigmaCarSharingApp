package com.sigmatechnology.csa.service;

import com.sigmatechnology.csa.entity.booking.Booking;
import com.sigmatechnology.csa.repository.BookingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingDao bookingDao;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingDao.create(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return bookingDao.update(booking);
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingDao.delete(bookingDao.find(booking.getId()));
    }

    @Override
    public List<Booking> listAll() {
        return bookingDao.listAll();
    }

    @Override
    public Booking fetchBooking(long id) {
        return bookingDao.find(id);
    }
}

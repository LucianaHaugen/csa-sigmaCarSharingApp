package com.sigmatechnology.csa.service;

import com.sigmatechnology.csa.entity.booking.Booking;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
@Transactional
public interface BookingService {

    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    void deleteBooking(Booking booking);
    List<Booking> listAll();
    Booking fetchBooking(long id);
}

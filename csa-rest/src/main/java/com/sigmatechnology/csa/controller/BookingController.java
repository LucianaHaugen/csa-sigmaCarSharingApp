package com.sigmatechnology.csa.controller;

import com.sigmatechnology.csa.entity.booking.Booking;
import com.sigmatechnology.csa.service.BookingService;
import com.sigmatechnology.csa.util.MappingsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lucianahaugen on 06/09/17.
 */

@RestController
public class BookingController extends AbstractController{

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET, value = "/booking({id}")
    @ResponseBody
    public Booking getBooking(@PathVariable("id") long id){

        return MappingsUtil.mapBookingToBooking(bookingService.fetchBooking(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bookings")
    @ResponseBody
    public List<Booking> listAll(){

        return  MappingsUtil.mapBookingsToBookings(bookingService.listAll());
    }

    @RequestMapping(method = RequestMethod.POST, value="booking")
    @ResponseBody
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public Booking create(@RequestBody Booking booking){

        return MappingsUtil.mapBookingToBooking(bookingService.createBooking(MappingsUtil.mapBookingToBookingEntity(booking, null)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/booking")
    @ResponseBody
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public Booking update(@RequestBody Booking booking){

        return MappingsUtil.mapBookingToBooking(bookingService.updateBooking(MappingsUtil.mapBookingToBookingEntity(booking, bookingService.fetchBooking(booking.getId()))));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/booking/{id}")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public @ResponseBody void delete(@PathVariable("id") long id){

        com.sigmatechnology.csa.entity.booking.Booking booking = bookingService.fetchBooking(id);
        bookingService.deleteBooking(booking);
    }

}

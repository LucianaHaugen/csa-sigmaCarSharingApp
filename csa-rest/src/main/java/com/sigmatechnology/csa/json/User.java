package com.sigmatechnology.csa.json;

import com.sigmatechnology.csa.entity.account.UserRole;
import com.sigmatechnology.csa.entity.booking.Booking;

import java.util.List;

/**
 * Created by lucianahaugen on 05/09/17.
 */
public class User extends AbstractJsonObject{

    private Long userId;
    private String userName;
    private String password;
    private int totalBookingsYear;
    private double totalDistanceYear;
    private List<Booking> bookings;
    private List<UserRole> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public int getTotalBookingsYear() {
        return totalBookingsYear;
    }

    public void setTotalBookingsYear(int totalBookingsYear) {
        this.totalBookingsYear = totalBookingsYear;
    }

    public double getTotalDistanceYear() {
        return totalDistanceYear;
    }

    public void setTotalDistanceYear(double totalDistanceYear) {
        this.totalDistanceYear = totalDistanceYear;
    }
}

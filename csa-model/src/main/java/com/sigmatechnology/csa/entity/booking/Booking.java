package com.sigmatechnology.csa.entity.booking;

import com.sigmatechnology.csa.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by lucianahaugen on 31/08/17.
 */

@NamedQueries({
        @NamedQuery(
                name = Booking.FIND_BY_IDS,
                query = "SELECT c FROM Booking c WHERE c.id IN :ids"
        )
})

@Entity
public class Booking extends AbstractBaseEntity implements Serializable {

    public static final String FIND_BY_IDS = "FindByIds";
    public static final String FETCH_ALL = "FetchAll";
    public static final String FETCH_BY_ERRAND = "FetchByErrands";


    private Long bookingId;
    private Long userId;
    private String reg;
    private LocalDateTime timeOfBooking;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private String errand;
    private String destination;
    private String purpose;

    public Booking() {
    }

    public Booking(Long bookingId, Long userId, String reg, LocalDateTime timeOfBooking, LocalDate startingDate, LocalTime startingTime, String errand, String destination, String purpose) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.reg = reg;
        this.timeOfBooking = LocalDateTime.now();;
        this.startingDate = startingDate;
        this.startingTime = startingTime;
        this.errand = errand;
        this.destination = destination;
        this.purpose = purpose;
    }

    public Long getBookingId() {
        return bookingId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public LocalDateTime getTimeOfBooking() {
        return timeOfBooking;
    }

    public void setTimeOfBooking(LocalDateTime timeOfBooking) {
        this.timeOfBooking = timeOfBooking;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }

    public String getErrand() {
        return errand;
    }

    public void setErrand(String errand) {
        this.errand = errand;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", reg='" + reg + '\'' +
                ", timeOfBooking=" + timeOfBooking +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", startingTime=" + startingTime +
                ", endingTime=" + endingTime +
                ", errand='" + errand + '\'' +
                ", destination='" + destination + '\'' +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}

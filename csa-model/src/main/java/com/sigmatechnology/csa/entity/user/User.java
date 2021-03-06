package com.sigmatechnology.csa.entity.user;

import com.sigmatechnology.csa.entity.AbstractBaseEntity;
import com.sigmatechnology.csa.entity.account.UserRole;
import com.sigmatechnology.csa.entity.booking.Booking;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
@NamedQueries({
        @NamedQuery(
                name = User.FIND_BY_IDS,
                query = "SELECT u from User u WHERE u.Id IN :Ids"
        )
})
@Entity
public class User extends AbstractBaseEntity implements Serializable {

    public static final String FIND_BY_IDS = "FindByIds";
    public static final String FETCH_ALL = "FetchAll";
    public static final String FETCH_BY_NAMES = "FetchByNames";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String userName;
    private boolean isApproved;
    private int totalBookingsYear;
    private double totalDistanceYear;
    private UserRole role;
    private List<UserRole> roles;
    private String password;

    public User() {
    }

    public User(String userName, boolean isApproved, int totalBookingsYear, double totalDistanceYear) {
        this.userName = userName;
        this.isApproved = isApproved;
        this.totalBookingsYear = totalBookingsYear;
        this.totalDistanceYear = totalDistanceYear;
    }

    public User(Long userId, String userName, boolean isApproved, int totalBookingsYear, int totalDistanceYear) {
        this.userId = userId;
        this.userName = userName;
        this.isApproved = isApproved;
        this.totalBookingsYear = totalBookingsYear;
        this.totalDistanceYear = totalDistanceYear;
    }

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.REMOVE,
                    CascadeType.PERSIST,
                    CascadeType.MERGE })
    private List<Booking> userBookings;

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
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

    public List<Booking> getUserBookings() {
        return userBookings;
    }

    public void setUserBooking(List<Booking> userBookings) {
        this.userBookings = userBookings;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setUserBookings(List<Booking> userBookings) {
        this.userBookings = userBookings;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public java.lang.String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", isApproved=" + isApproved +
                ", totalBookingsYear=" + totalBookingsYear +
                ", totalDistanceYear=" + totalDistanceYear +
                ", userBookings=" + userBookings +
                '}';
    }
}

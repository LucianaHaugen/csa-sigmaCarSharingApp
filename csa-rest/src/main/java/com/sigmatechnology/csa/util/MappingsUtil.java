package com.sigmatechnology.csa.util;

import com.google.common.collect.Lists;
import com.sigmatechnology.csa.entity.account.UserAccount;
import com.sigmatechnology.csa.entity.account.UserRole;
import com.sigmatechnology.csa.entity.booking.Booking;
import com.sigmatechnology.csa.entity.user.User;
import com.sigmatechnology.csa.entity.vehicle.Vehicle;
import com.sigmatechnology.csa.json.AbstractJsonObject;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by lucianahaugen on 05/09/17.
 */
public class MappingsUtil {

    public static List<com.sigmatechnology.csa.json.User> mapUserAccountsToUsers(List<UserAccount> userAccounts) {
        List<com.sigmatechnology.csa.json.User> returnList = new ArrayList<>();
        for (UserAccount userAccount : userAccounts) {
            returnList.add(mapUserAccountToUser(userAccount));
        }
        return returnList;
    }

    public static com.sigmatechnology.csa.json.User mapUserAccountToUser(UserAccount userAccount) {
        com.sigmatechnology.csa.json.User user = new com.sigmatechnology.csa.json.User();
        user.setId(userAccount.getId());
        user.setUserName(userAccount.getUsername());
        user.setPassword(userAccount.getPassword());
        user.setRoles(mapUserRolesToJson(userAccount.getRoles()));

        return user;
    }

    public static UserAccount mapUserToUserAccount(com.sigmatechnology.csa.json.User user, UserAccount userAccount, List<User> users) {
        if (userAccount == null) {
            userAccount = new UserAccount();
        }
        userAccount.setId(user.getUserId());
        userAccount.setUsername(user.getUserName());
        userAccount.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userAccount.setRoles(mapUserRolesToDao(user.getRoles()));
        userAccount.setUsers(users);

        return userAccount;
    }


   public static List<com.sigmatechnology.csa.json.User> mapUsersToUsers(List<User> users) {
       List<com.sigmatechnology.csa.json.User> returnList = new ArrayList<>();
       for (User user : users) {
           returnList.add(mapUserToUser(user));
       }
       return returnList;
   }

    /**
     * Map Entity User to JSON User
     */
    public static com.sigmatechnology.csa.json.User mapUserToUser(User user) {
        com.sigmatechnology.csa.json.User jsonUser = new com.sigmatechnology.csa.json.User();
        jsonUser.setId(user.getId());
        jsonUser.setUserName(user.getUserName());

        return jsonUser;
    }

    public static User mapUserToUserEntity(com.sigmatechnology.csa.json.User user, User userEntity){
        if(user == null){
            return null;
        }
        if(userEntity == null){
            userEntity = new User();
        }
        userEntity.setId(user.getId());
        userEntity.setUserName(user.getUserName());
        userEntity.setRoles(user.getRoles());
        userEntity.setTotalBookingsYear(user.getTotalBookingsYear());
        userEntity.setTotalDistanceYear(user.getTotalDistanceYear());
        userEntity.setUserBooking(user.getBookings());

        return userEntity;
    }

    private static List<UserRole> mapUserRolesToJson(List<UserRole> userRolesDao){
        List<UserRole> userRolesJson = Lists.newArrayList();

        if(Objects.nonNull(userRolesDao)){
            userRolesDao.forEach((role -> {
                UserRole userRole = new UserRole();
                userRole.setRole(role.getRole());
                userRolesJson.add(userRole);

            }));
        }
        return userRolesJson;
    }

    private static List<UserRole> mapUserRolesToDao(List<UserRole> userRolesJson){
        List<UserRole> userRolesDao = Lists.newArrayList();

        if(Objects.nonNull(userRolesJson)){
            userRolesJson.forEach((role) -> {
                UserRole userRole = new UserRole();
                userRole.setRole(role.getRole());
                userRolesDao.add(userRole);
            });
        }

        return userRolesDao;
    }

    public static List<Long> mapIdList(List<? extends AbstractJsonObject> jsonList){
        List<Long> idList = new ArrayList<>();
        for(AbstractJsonObject jsonObject : jsonList){
            idList.add(jsonObject.getId());
        }

        return idList;
    }


    /** ____________________________________BOOKING RELATED METHODS__________
     *
     * Map Entity Booking to JSON Booking
     * @param booking
     * @return jsonBooking
     */
    public static Booking mapBookingToBooking(Booking booking) {
        Booking jsonBooking = new Booking();
        jsonBooking.setId(booking.getId());
        jsonBooking.setUserId(booking.getUserId());
        jsonBooking.setReg(booking.getReg());
        jsonBooking.setTimeOfBooking(booking.getTimeOfBooking());
        jsonBooking.setStartingDate(booking.getStartingDate());
        jsonBooking.setStartingTime(booking.getStartingTime());
        jsonBooking.setEndingDate(booking.getEndingDate());
        jsonBooking.setEndingTime(booking.getEndingTime());
        jsonBooking.setDestination(booking.getDestination());
        jsonBooking.setErrand(booking.getErrand());
        jsonBooking.setPurpose(booking.getPurpose());

        return jsonBooking;
    }

    public static List<Booking> mapBookingsToBookings(List<Booking> bookings) {
        List<Booking> returnList = new ArrayList<>();
        for(com.sigmatechnology.csa.entity.booking.Booking booking : bookings){
            returnList.add(mapBookingToBooking(booking));
        }

        return returnList;
    }

    public static Booking mapBookingToBookingEntity(Booking booking, com.sigmatechnology.csa.entity.booking.Booking bookingEntity) {
        if(booking == null){
            return null;
        }

        if(bookingEntity == null){
            bookingEntity = new com.sigmatechnology.csa.entity.booking.Booking();
        }
        bookingEntity.setId(booking.getId());
        bookingEntity.setUserId(booking.getUserId());
        bookingEntity.setReg(booking.getReg());
        bookingEntity.setTimeOfBooking(booking.getTimeOfBooking());
        bookingEntity.setStartingDate(booking.getStartingDate());
        bookingEntity.setStartingTime(booking.getStartingTime());
        bookingEntity.setEndingDate(booking.getEndingDate());
        bookingEntity.setEndingTime(booking.getEndingTime());
        bookingEntity.setDestination(booking.getDestination());
        bookingEntity.setErrand(booking.getErrand());
        bookingEntity.setPurpose(booking.getPurpose());

        return bookingEntity;
    }


    /**____________________________________VEHICLE RELATED METHODS__________
     *
     * Map Entity Vehicle to JSON Booking
     * @param vehicles
     * @return
     */
    public static List<com.sigmatechnology.csa.json.Vehicle> mapVehiclesToVehicles(List<Vehicle> vehicles) {
        List<com.sigmatechnology.csa.json.Vehicle> returnList = new ArrayList<>();
        for(com.sigmatechnology.csa.entity.vehicle.Vehicle vehicle : vehicles){
            returnList.add(mapVehicleToVehicle(vehicle));
        }

        return returnList;
    }

    public static com.sigmatechnology.csa.json.Vehicle mapVehicleToVehicle(Vehicle vehicle) {
        com.sigmatechnology.csa.json.Vehicle jsonVehicle = new com.sigmatechnology.csa.json.Vehicle();

        jsonVehicle.setId(vehicle.getId());
        jsonVehicle.setVehicleId(vehicle.getVehicleId());
        jsonVehicle.setReg(vehicle.getReg());
        jsonVehicle.setBody(vehicle.getBody());
        jsonVehicle.setModel(vehicle.getModel());
        jsonVehicle.setFuel(vehicle.getFuel());
        jsonVehicle.setEquipment(vehicle.getEquipment());
        jsonVehicle.setMileage(vehicle.getMileage());
        jsonVehicle.setYear(vehicle.getYear());
        jsonVehicle.setSite(vehicle.getSite());
        jsonVehicle.setResponsible(vehicle.getResponsible());

        return jsonVehicle;
    }

    public static Vehicle mapVehicleToVehicleEntity(com.sigmatechnology.csa.json.Vehicle vehicle, Vehicle vehicleEntity) {
        /**
         * A vehicle may not be selected in eg Vehicle view during Save.
         * In that case the Vehicle will be NULL
         */
        if(vehicle == null){
            return null;
        }
        if(vehicleEntity == null){
            vehicleEntity = new Vehicle();
        }
        vehicleEntity.setId(vehicle.getId());
        vehicleEntity.setReg(vehicle.getReg());
        vehicleEntity.setBody(vehicle.getBody());
        vehicleEntity.setModel(vehicle.getModel());
        vehicleEntity.setFuel(vehicle.getFuel());
        vehicleEntity.setEquipment(vehicle.getEquipment());
        vehicleEntity.setMileage(vehicle.getMileage());
        vehicleEntity.setYear(vehicle.getYear());
        vehicleEntity.setSite(vehicle.getSite());
        vehicleEntity.setResponsible(vehicle.getResponsible());

        return vehicleEntity;
    }



}

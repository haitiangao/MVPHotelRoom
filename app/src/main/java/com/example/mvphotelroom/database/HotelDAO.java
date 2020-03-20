package com.example.mvphotelroom.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HotelDAO {

    //BookingEntity tasks
    @Insert
    void addNewBooking(BookingEntity... newBooking);

    @Delete
    void deleteBooking (BookingEntity deleteBooking);

    @Query("SELECT * FROM Booking WHERE hotelRoomID = :hotelRoomID")
    BookingEntity findBookingByForeignKey(int hotelRoomID);

    @Query("SELECT * FROM Booking")
    List<BookingEntity> findAllBooking();

    @Update
    void updateBooking(BookingEntity bookingEntity);


    //HotelRoomEntity tasks
    @Insert
    void addNewHotelRoom(HotelRoomEntity... newHotelRoom);

    @Delete
    void deleteHotelRoom(HotelRoomEntity deleteHotelRoom);

    @Query("SELECT * FROM HotelRooms WHERE hotelRoomID = :hotelRoomID")
    HotelRoomEntity findHotelRoom(int hotelRoomID);

    @Query("SELECT * FROM HotelRooms")
    List<HotelRoomEntity> findAllHotelRoom();

    @Update
    void updateHotelRoom(HotelRoomEntity hotelRoomEntity);


    //ClerkEntity tasks
    @Insert
    void addNewClerk(ClerkEntity... newClerk);

    @Delete
    void deleteClerk (ClerkEntity deleteClerk);

    @Query("SELECT * FROM Clerks WHERE clerkUsername = :clerkUsername AND clerkPassword = :clerkPassword")
    ClerkEntity loginClerk(String clerkUsername, String clerkPassword);

}

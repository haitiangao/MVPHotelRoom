package com.example.mvphotelroom.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Booking")
public class BookingEntity {

    @PrimaryKey(autoGenerate = true)
    private int bookingID;

    @ForeignKey(entity = HotelRoomEntity.class, parentColumns = "hotelRoomID", childColumns = "hotelRoomID")
    private int hotelRoomID;

    @ColumnInfo(name = "guestname")
    private String guestName;

    public BookingEntity(int bookingID, int hotelRoomID, String guestName) {
        this.bookingID = bookingID;
        this.hotelRoomID = hotelRoomID;
        this.guestName = guestName;
    }

    @Ignore
    public BookingEntity(int hotelRoomID, String guestName) {
        this.hotelRoomID = hotelRoomID;
        this.guestName = guestName;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getHotelRoomID() {
        return hotelRoomID;
    }

    public void setHotelRoomID(int hotelRoomID) {
        this.hotelRoomID = hotelRoomID;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
}
package com.example.mvphotelroom.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {BookingEntity.class,ClerkEntity.class,HotelRoomEntity.class})
public abstract class HotelDB extends RoomDatabase {
    public abstract HotelDAO getHotelDAO();


}

package com.example.mvphotelroom.presenter;

import com.example.mvphotelroom.database.BookingEntity;
import com.example.mvphotelroom.database.HotelRoomEntity;

import java.util.List;

public interface Contract {

    interface Presenter{
        void loginClerk(String clerkUsername, String clerkPassword);
        void signOutClerk();
        void registerClerk(String clerkUsername, String clerkPassword);
        HotelRoomEntity getHotelEntity(int roomID);
        List<HotelRoomEntity> getAllHotelEntity();
        BookingEntity getBookingEntityByForeign(int foreignHotelID);
        void makeNewRoom();
        void addBooking(int hotelRoomID, String guestName);
        void setCurrentHotelRoomInstance(HotelRoomEntity hotelRoomInstance);
        HotelRoomEntity getHotelRoomInstance();
        void updateHotelItem(HotelRoomEntity hotelRoomEntity);
    }

    interface View{
        void clerkLoginSuccess();
        void clerkLoginFailed();
        void clerkLoggedOut();
        void backFromRegister();
        void makeNewRoom();
        void openCheckFrag();
        void backFromBooking();

    }

    interface GuestInterface{
        void checkingIn();
    }

}

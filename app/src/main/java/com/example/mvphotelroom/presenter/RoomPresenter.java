package com.example.mvphotelroom.presenter;

import android.util.Log;

import androidx.room.Room;

import com.example.mvphotelroom.database.BookingEntity;
import com.example.mvphotelroom.database.ClerkEntity;
import com.example.mvphotelroom.database.HotelDB;
import com.example.mvphotelroom.database.HotelRoomEntity;
import com.example.mvphotelroom.view.MainActivity;

import java.util.List;
import java.util.Random;


public class RoomPresenter implements  Contract.Presenter {

    private Contract.View mainView;
    private HotelDB hotelDB;
    private HotelRoomEntity currentHotelRoom;
    private BookingEntity currentBooking;
    private ClerkEntity currentClerk;

    public RoomPresenter(Contract.View mainView){
        this.mainView = mainView;

        hotelDB = Room.databaseBuilder(
                ((MainActivity)mainView),
                HotelDB.class,
                "users.db")
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public void loginClerk(String clerkUsername, String clerkPassword) {
        currentClerk = hotelDB.getHotelDAO().loginClerk(clerkUsername, clerkPassword);

        Log.d("TAG_X", "userName: " + clerkUsername + " password: " + clerkPassword);
        if (currentClerk == null)
            mainView.clerkLoginFailed();
        else
            mainView.clerkLoginSuccess();

    }

    @Override
    public void signOutClerk() {
        currentClerk = null;
        mainView.clerkLoggedOut();
    }

    @Override
    public void registerClerk(String clerkUsername, String clerkPassword) {
        hotelDB.getHotelDAO().addNewClerk(new ClerkEntity(clerkUsername, clerkPassword));
        mainView.backFromRegister();
    }

    @Override
    public HotelRoomEntity getHotelEntity(int roomID) {
        return hotelDB.getHotelDAO().findHotelRoom(roomID);
    }

    @Override
    public List<HotelRoomEntity> getAllHotelEntity() {
        List<HotelRoomEntity> hotelRoomList = hotelDB.getHotelDAO().findAllHotelRoom();
        return hotelRoomList;
    }

    @Override
    public BookingEntity getBookingEntityByForeign(int hotelRoomID) {
        return hotelDB.getHotelDAO().findBookingByForeignKey(hotelRoomID);
    }

    @Override
    public void makeNewRoom(){
        String url = "https://images.pexels.com/photos/164595/pexels-photo-164595.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
        Random random = new Random();
        int randomNumber = random.nextInt(901)+100;
        HotelRoomEntity hotelRoomEntity = new HotelRoomEntity(true,url,randomNumber);
        hotelDB.getHotelDAO().addNewHotelRoom(hotelRoomEntity);
    }

    @Override
    public void addBooking(int hotelRoomID, String guestName) {

        BookingEntity bookingEntity = new BookingEntity(hotelRoomID,guestName);
        hotelDB.getHotelDAO().addNewBooking(bookingEntity);

    }

    @Override
    public void setCurrentHotelRoomInstance(HotelRoomEntity hotelRoomInstance) {
        this.currentHotelRoom=hotelRoomInstance;
    }

    @Override
    public HotelRoomEntity getHotelRoomInstance() {
        return currentHotelRoom;
    }
    @Override
    public void updateHotelItem(HotelRoomEntity hotelRoomEntity){
        hotelDB.getHotelDAO().updateHotelRoom( hotelRoomEntity);
    }

}

package com.example.mvphotelroom.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;

import com.example.mvphotelroom.R;
import com.example.mvphotelroom.database.BookingEntity;
import com.example.mvphotelroom.database.ClerkEntity;
import com.example.mvphotelroom.database.HotelDB;
import com.example.mvphotelroom.database.HotelRoomEntity;
import com.example.mvphotelroom.presenter.Contract;
import com.example.mvphotelroom.presenter.RoomPresenter;
import com.example.mvphotelroom.presenter.GuestPresenter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Contract.View{

    private HotelDB hotelDB;

    @BindView(R.id.username_edittext)
    EditText usernameEditText;

    @BindView(R.id.password_edittext)
    EditText passwordEditText;
    private Contract.Presenter mainPresenter;
    private HotelRoomsFragment hotelRooms;
    private RegisterFragment registerFragment;
    private BookingHotelRoomFragment bookingHotelRoomFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new RoomPresenter(this);
        hotelRooms = new HotelRoomsFragment();
        registerFragment = new RegisterFragment();
        bookingHotelRoomFragment = new BookingHotelRoomFragment();

    }

    @OnClick(R.id.login_button)
    public void loginClick(View view) {
        String clerkName = usernameEditText.getText().toString().trim();
        String clerkPassword = passwordEditText.getText().toString().trim();

        mainPresenter.loginClerk(clerkName, clerkPassword);
    }

    @Override
    public void clerkLoginSuccess() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrame, hotelRooms)
                .commit();
    }

    @OnClick(R.id.new_clerk_button)
    public void registerClick(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrame,registerFragment)
                .commit();
    }

    public void registerClerk(String userName, String passWord){
        mainPresenter.registerClerk(userName,passWord);
    }


    @Override
    public void clerkLoginFailed() {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme))
                .setTitle("Login failed")
                .setMessage("Username or password incorrect. Please re-enter.")
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                    }
                })
                .create()
                .show();
    }

    @Override
    public void clerkLoggedOut() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(hotelRooms)
                .commit();
    }

    @Override
    public void backFromRegister() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(registerFragment)
                .commit();
    }

    @Override
    public void backFromBooking() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(bookingHotelRoomFragment)
                .commit();
    }
    @Override
    public void openCheckFrag() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bookingFrame, bookingHotelRoomFragment)
                .commit();
    }


    public void makeNewRoom(){
        mainPresenter.makeNewRoom();
    }

    public void logout() {
        mainPresenter.signOutClerk();

    }

    public List<HotelRoomEntity> getAllHotelRoomEntity(){
        return mainPresenter.getAllHotelEntity();
    }
    public HotelRoomEntity getOneHotelRoomEntity(int ID){
        return mainPresenter.getHotelEntity(ID);
    }

    public HotelRoomEntity getCurrentHotelEntity(){
        return mainPresenter.getHotelRoomInstance();
    }

    public void setCurrentHotelRoomInstance(HotelRoomEntity hotelRoomInstance)
    {
        mainPresenter.setCurrentHotelRoomInstance(hotelRoomInstance);
    }

}

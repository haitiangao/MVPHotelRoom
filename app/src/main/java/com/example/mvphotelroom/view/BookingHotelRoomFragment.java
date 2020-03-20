package com.example.mvphotelroom.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvphotelroom.R;
import com.example.mvphotelroom.database.BookingEntity;
import com.example.mvphotelroom.database.HotelRoomEntity;
import com.example.mvphotelroom.presenter.Contract;
import com.example.mvphotelroom.presenter.GuestPresenter;
import com.example.mvphotelroom.presenter.RoomPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingHotelRoomFragment extends Fragment {
    private HotelRoomEntity hotelRoomEntity;
    private Contract.GuestInterface guestPresenter;
    private Contract.Presenter roomPresenter;
    private BookingEntity bookingEntity;

    @BindView(R.id.bookingImage)
    ImageView bookingImage;
    @BindView(R.id.roomNumberView)
    TextView roomNumberView;
    @BindView(R.id.roomAvailabilityView)
    TextView roomAvailabilityView;
    @BindView(R.id.priceView)
    TextView priceView;
    @BindView(R.id.guestNameView)
    TextView guestNameView;
    @BindView(R.id.namedEdit)
    EditText namedEdit;

    @BindView(R.id.backFButton)
    Button backButton;
    @BindView(R.id.addBookingButton)
    Button addBookingButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking_hotel_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guestPresenter = new GuestPresenter((MainActivity)getContext());
        roomPresenter = new RoomPresenter((MainActivity)getContext());

        ButterKnife.bind(this,view);
        hotelRoomEntity = ((MainActivity)getActivity()).getCurrentHotelEntity();

        //Glide.with(this).load(hotelRoomEntity.getImageUrl()).into(bookingImage);
        roomNumberView.setText("Room Number: "+hotelRoomEntity.getHotelRoomID());
        roomAvailabilityView.setText("Availability: "+hotelRoomEntity.isAvailable());
        priceView.setText("Price: "+hotelRoomEntity.getPrice());


        if (!hotelRoomEntity.isAvailable()){
            addBookingButton.setEnabled(false);
            bookingEntity=roomPresenter.getBookingEntityByForeign(hotelRoomEntity.getHotelRoomID());
            namedEdit.setVisibility(View.INVISIBLE);
            namedEdit.setEnabled(false);
            guestNameView.setVisibility(View.VISIBLE);

            guestNameView.setEnabled(true);
            guestNameView.setText("Guest: "+bookingEntity.getGuestName());

            Log.d("TAG_H","false: "+namedEdit.getText().toString().trim());

        }



    }


    @OnClick(R.id.addBookingButton)
    public void addBooking(){
        Log.d("TAG_H", "Booking entered:" +namedEdit.getText().toString().trim());
        hotelRoomEntity.setAvailable(false);
        roomPresenter.updateHotelItem(hotelRoomEntity);
        roomPresenter.addBooking(hotelRoomEntity.getHotelRoomID(),namedEdit.getText().toString().trim());
        ((MainActivity)getContext()).backFromBooking();
    }

    @OnClick(R.id.backFButton)
    public void backToPrevious(View view){
        ((MainActivity)getContext()).backFromBooking();
        }

}

package com.example.mvphotelroom.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvphotelroom.R;
import com.example.mvphotelroom.adapter.RecyclerAdapter;
import com.example.mvphotelroom.database.HotelRoomEntity;
import com.example.mvphotelroom.presenter.Contract;
import com.example.mvphotelroom.presenter.GuestPresenter;
import com.example.mvphotelroom.presenter.RoomPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HotelRoomsFragment extends Fragment implements RecyclerAdapter.UserClickListener {

    private List<HotelRoomEntity> hotelRoomEntityList = new ArrayList<>();
    private Contract.GuestInterface guestPresenter;
    //private Contract.Presenter roomPresenter;


    @BindView(R.id.hotelRecyclerView)
    RecyclerView hotelRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotel_room, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        guestPresenter = new GuestPresenter((MainActivity)getContext());
        //roomPresenter = new RoomPresenter((MainActivity)getContext());

        hotelRoomEntityList = ((MainActivity)getContext()).getAllHotelRoomEntity();

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), RecyclerView.VERTICAL);
        hotelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        hotelRecyclerView.setAdapter(new RecyclerAdapter(hotelRoomEntityList,this,getActivity()));
        hotelRecyclerView.addItemDecoration(itemDecoration);
    }

    @OnClick(R.id.logoutHotel)
    public void backFromHotel(){
        ((MainActivity)getContext()).clerkLoggedOut();
    }

    @OnClick(R.id.addHotelRoomButton)
    public void createNewRoom(){
        ((MainActivity)getContext()).makeNewRoom();
        hotelRoomEntityList = ((MainActivity)getContext()).getAllHotelRoomEntity();
        Log.d("TAG_H",Integer.toString  (hotelRoomEntityList.get(0).getHotelRoomID()));
        refreshView();
    }

    @Override
    public void displayHotelRoom(HotelRoomEntity hotelRoomEntity){
        //Log.d("TAG_H", String.valueOf(hotelRoomEntity.getPrice()));

        ((MainActivity)getContext()).setCurrentHotelRoomInstance(hotelRoomEntity);
        //Log.d("TAG_H", String.valueOf("1"+roomPresenter.getHotelRoomInstance()));
        //Log.d("TAG_H", String.valueOf("2"+((MainActivity)getActivity()).getCurrentHotelEntity()));

        guestPresenter.checkingIn();
    }

    public void refreshView(){
        RecyclerAdapter recycleAdaptor = new RecyclerAdapter(hotelRoomEntityList, this,getActivity());
        hotelRecyclerView.setAdapter(null);
        hotelRecyclerView.setAdapter(recycleAdaptor);
        recycleAdaptor.notifyDataSetChanged();
    }



}

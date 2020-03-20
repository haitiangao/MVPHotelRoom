package com.example.mvphotelroom.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvphotelroom.R;
import com.example.mvphotelroom.database.HotelRoomEntity;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HotelViewHolder> {

    private List<HotelRoomEntity> hotelRooms;
    private UserClickListener userClickListener;
    private Context context;

    public RecyclerAdapter(List<HotelRoomEntity> hotelRooms, UserClickListener userClickListener, Context context) {
        this.hotelRooms = hotelRooms;
        this.userClickListener = userClickListener;
        this.context = context;
    }

    public interface UserClickListener {
        void displayHotelRoom(HotelRoomEntity hotelRoomEntity);
    }

    @NonNull
    @Override
    public RecyclerAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_room_layout, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.HotelViewHolder holder, int position) {
        holder.hotelNumber.setText("Room Number: "+hotelRooms.get(position).getHotelRoomID());
        holder.hotelRoomAvailable.setText("Available: "+ hotelRooms.get(position).isAvailable());
        holder.hotelRoomPrice.setText("Price: "+ hotelRooms.get(position).getPrice());
        Glide.with(context)
                .load(hotelRooms.get(position).getImageUrl())
                .into(holder.hotelRoomPicture);

        Log.d("TAG_H", String.valueOf(hotelRooms.get(position)));
        holder.itemView.setOnClickListener(view ->
                  userClickListener.displayHotelRoom(hotelRooms.get(position)));
    }

    @Override
    public int getItemCount() {
        return hotelRooms.size();
    }


    class HotelViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hotel_number)
        TextView hotelNumber;
        @BindView(R.id.hotel_room_price)
        TextView hotelRoomPrice;
        @BindView(R.id.hotel_room_available)
        TextView hotelRoomAvailable;
        @BindView(R.id.hotel_room_picture)
        ImageView hotelRoomPicture;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

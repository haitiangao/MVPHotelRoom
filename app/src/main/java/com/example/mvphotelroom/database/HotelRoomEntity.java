package com.example.mvphotelroom.database;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "HotelRooms")
public class HotelRoomEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int hotelRoomID;

    @ColumnInfo(name = "available")
    private boolean available;
    @ColumnInfo(name = "imageUrl")
    private String  imageUrl;
    @ColumnInfo(name = "price")
    private int price;

    public HotelRoomEntity(int hotelRoomID, boolean available, String imageUrl, int price) {
        this.hotelRoomID = hotelRoomID;
        this.available = available;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    @Ignore
    public HotelRoomEntity(boolean available, String imageUrl, int price) {
        this.available = available;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    protected HotelRoomEntity(Parcel in) {
        hotelRoomID = in.readInt();
        available = in.readByte() != 0;
        imageUrl = in.readString();
        price = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(hotelRoomID);
        dest.writeByte((byte) (available ? 1 : 0));
        dest.writeString(imageUrl);
        dest.writeInt(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HotelRoomEntity> CREATOR = new Creator<HotelRoomEntity>() {
        @Override
        public HotelRoomEntity createFromParcel(Parcel in) {
            return new HotelRoomEntity(in);
        }

        @Override
        public HotelRoomEntity[] newArray(int size) {
            return new HotelRoomEntity[size];
        }
    };

    public int getHotelRoomID() {
        return hotelRoomID;
    }

    public void setHotelRoomID(int hotelRoomID) {
        this.hotelRoomID = hotelRoomID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

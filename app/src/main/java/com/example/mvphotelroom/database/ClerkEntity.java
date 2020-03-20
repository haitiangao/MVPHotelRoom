package com.example.mvphotelroom.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Clerks")
public class ClerkEntity {

    @PrimaryKey(autoGenerate = true)
    private int clerkID;

    @ColumnInfo (name = "clerkUserName")
    private String clerkUserName;

    @ColumnInfo (name = "clerkPassword")
    private String clerkPassword;

    public ClerkEntity(int clerkID, String clerkUserName, String clerkPassword) {
        this.clerkID = clerkID;
        this.clerkUserName = clerkUserName;
        this.clerkPassword = clerkPassword;
    }

    @Ignore
    public ClerkEntity(String clerkUserName, String clerkPassword) {
        this.clerkUserName = clerkUserName;
        this.clerkPassword = clerkPassword;
    }

    public int getClerkID() {
        return clerkID;
    }

    public void setClerkID(int clerkID) {
        this.clerkID = clerkID;
    }

    public String getClerkUserName() {
        return clerkUserName;
    }

    public void setClerkUserName(String clerkUserName) {
        this.clerkUserName = clerkUserName;
    }

    public String getClerkPassword() {
        return clerkPassword;
    }

    public void setClerkPassword(String clerkPassword) {
        this.clerkPassword = clerkPassword;
    }
}

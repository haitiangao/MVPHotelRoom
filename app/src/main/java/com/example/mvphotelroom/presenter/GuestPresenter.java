package com.example.mvphotelroom.presenter;


import android.util.Log;

public class GuestPresenter implements  Contract.GuestInterface{
    private Contract.View mainView;


    public GuestPresenter(Contract.View mainView) {
        this.mainView = mainView;
        Log.d("TAG_H", String.valueOf(mainView));

    }


    @Override
    public void checkingIn(){
        Log.d("TAG_H", String.valueOf(mainView));
        mainView.openCheckFrag();
    }



}

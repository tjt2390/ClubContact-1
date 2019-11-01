package com.example.eventstrackerapp.ui.allUsers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewAllUsersViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public ViewAllUsersViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("This is view all users fragment");
    }

    public LiveData<String> getText(){
        return mText;
    }
}
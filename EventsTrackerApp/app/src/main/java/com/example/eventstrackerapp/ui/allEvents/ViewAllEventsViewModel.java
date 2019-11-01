package com.example.eventstrackerapp.ui.allEvents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewAllEventsViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public ViewAllEventsViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("This is view all users fragment");
    }

    public LiveData<String> getText(){
        return mText;
    }
}
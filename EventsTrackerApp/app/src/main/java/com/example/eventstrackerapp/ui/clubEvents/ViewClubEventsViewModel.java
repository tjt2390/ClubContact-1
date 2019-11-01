package com.example.eventstrackerapp.ui.clubEvents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewClubEventsViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public ViewClubEventsViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("This is view club events fragment");
    }

    public LiveData<String> getText(){
        return mText;
    }
}
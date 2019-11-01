package com.example.eventstrackerapp.ui.clubMembers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewClubMembersViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public ViewClubMembersViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("This is view club members fragment");
    }

    public LiveData<String> getText(){
        return mText;
    }
}
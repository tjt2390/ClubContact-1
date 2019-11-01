package com.example.eventstrackerapp.ui.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eventstrackerapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomView extends LinearLayout {
    // Instance variables
    private static final String TAG = "CustomView";

    private Calendar calendar = Calendar.getInstance();
    private ImageView previoutButton, nextButton;
    private int month, year;
    private GridView gridView;
    private static final int MAX_CALENDAR_COLUMNS = 42;
    private TextView currentDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private FloatingActionButton addEventButton;

    private Context context;

    // Constructors
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        initializeUILayout();

    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    private void initializeUILayout(){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_calendar, this);
        previoutButton = view.findViewById(R.id.previous_month);
        nextButton = view.findViewById(R.id.next_month);
        currentDate = view.findViewById(R.id.display_current_date);
        addEventButton = view.findViewById(R.id.add_calendar_event);
        gridView = view.findViewById(R.id.calendar_grid);
    }

    private void setUpCalendarAdapter(){
        List<Date> dayValueInCells = new ArrayList<Date>();
        
    }

}

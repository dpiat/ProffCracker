package com.sinhro.mentorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sinhro.mentorapp.Model.EventsList;

public class ShowEventsListActivity extends AppCompatActivity {
    private ListView listView;
    private LinearLayout titleLinearLayout;
    private void initAllViews(){
        titleLinearLayout = findViewById(R.id.events_title_layout);
        View titleView = getLayoutInflater().inflate(
                R.layout.title,titleLinearLayout,false
        );
        LinearLayout home = titleView.findViewById(R.id.events_title_leftButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleLinearLayout.addView(titleView);

        listView = findViewById(R.id.events_listView);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_activity);

        initAllViews();

//        FragmentManager fragmentManager =getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        ExampleFragment fragment = new ExampleFragment();
//        fragmentTransaction.add(R.id.fragment_container, fragment);
//        fragmentTransaction.commit();
        EventsList events = getEvents();
        listView.setAdapter(
                new EventsListAdapter(
                        events,
                        getApplicationContext()
                )
        );


    }

    private EventsList getEvents(){
        Bundle arguments = getIntent().getExtras();
        EventsList eventsList = null;
        if (arguments!= null)
            eventsList = (EventsList) arguments.getSerializable(EventsList.class.getSimpleName());
        return eventsList;
    }
}

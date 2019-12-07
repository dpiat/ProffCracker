package com.sinhro.mentorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinhro.mentorapp.Model.FullEvent;

public class ShowFullEventActivity extends AppCompatActivity {

    private LinearLayout titleLinearLayout;
    private TextView taskTitleTextView,
            taskDescriptionTextView;

    private void findViews(){
        titleLinearLayout = findViewById(R.id.fullEvent_title_layout);
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

        taskDescriptionTextView= findViewById(R.id.fullEvent_body_taskDescription_textView);
        taskTitleTextView= findViewById(R.id.fullEvent_body_taskTitle_textView);
    }
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_info_activity);

        findViews();

        FullEvent task = getCurrentTask();

        taskDescriptionTextView.setText(task.getDescription());
    }

    private FullEvent getCurrentTask(){
        Bundle arguments = getIntent().getExtras();
        FullEvent tasksList = null;
        if (arguments!= null)
            tasksList = (FullEvent) arguments.getSerializable(FullEvent.class.getSimpleName());
        return tasksList;
    }
}

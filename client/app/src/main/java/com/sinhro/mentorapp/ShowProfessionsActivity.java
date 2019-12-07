package com.sinhro.mentorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sinhro.mentorapp.Model.ProfessionsList;

public class ShowProfessionsActivity extends AppCompatActivity {
    private LinearLayout titleLinearLayout;
    private ListView professionInfos_listView;

    private void findElems(){
        professionInfos_listView = findViewById(R.id.professionInfos_listView);
        titleLinearLayout = findViewById(R.id.professionInfos_title_layout);
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
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profession_list_activity);

        findElems();

        ProfessionsListAdapter adapter = new ProfessionsListAdapter(
            getProfessions(),getApplicationContext()
        );
        professionInfos_listView.setAdapter(adapter);

    }

    private ProfessionsList getProfessions(){
        Bundle arguments = getIntent().getExtras();
        ProfessionsList profsList = null;
        if (arguments!= null) {
            profsList = (ProfessionsList) arguments.getSerializable(ProfessionsList.class.getSimpleName());
        }
        return profsList;
    }

}

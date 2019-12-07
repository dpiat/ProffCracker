package com.sinhro.mentorapp;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sinhro.mentorapp.API.MyRequestService;
import com.sinhro.mentorapp.Model.EventsList;
import com.sinhro.mentorapp.Model.FullEvent;
import com.sinhro.mentorapp.Model.Profession;
import com.sinhro.mentorapp.Model.ProfessionsList;

import java.util.LinkedList;
import java.util.List;

public class ProfessionsListAdapter implements ListAdapter {
    ProfessionsList professionsList;
    Context context;

    public ProfessionsListAdapter(ProfessionsList professionsList,Context context) {
        this.context = context;
        this.professionsList = professionsList;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return professionsList.getProfessionList().size();
    }

    @Override
    public Object getItem(int position) {
        return professionsList.getProfessionList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.profession_list_item, null);
        }

        List<Profession> professions = professionsList.getProfessionList();
        Profession currProfession = (Profession) professions.get(position);

        TextView title = convertView.findViewById(R.id.profession_item_title);
        title.setText(currProfession.name);

        ProgressBar progressBar = convertView.findViewById(R.id.profession_item_progressBar);
        progressBar.setMax(100);
        progressBar.setProgress((int) (currProfession.getPercent()*100));


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowEventsListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                EventsList eventsList = MyRequestService.loadEventsFor(
                        professionsList.getProfessionList().get(position).getName()
                );
                intent.putExtra(EventsList.class.getSimpleName(), eventsList);

                context.startActivity(intent);
                //context.startActivity(intent);
            }
        });

        return convertView;
    }


    private void moveToEvents(){
        Intent intent = new Intent(context, ShowEventsListActivity.class);
        List<FullEvent> eventsList = new LinkedList<>();

        eventsList.add(new FullEvent("111","222","12:12","com1",1.1,2.2));
        eventsList.add(new FullEvent("100","566","00:12","com1",13.1,266.2));
        eventsList.add(new FullEvent("122","212","23:45","com1",13.31,25.2));
        EventsList tasks = new EventsList(eventsList);

        intent.putExtra(EventsList.class.getSimpleName(),tasks);

        context.startActivity(intent);

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

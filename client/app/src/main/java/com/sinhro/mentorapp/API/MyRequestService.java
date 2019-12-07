package com.sinhro.mentorapp.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinhro.mentorapp.Model.EventsList;
import com.sinhro.mentorapp.Model.FullEvent;
import com.sinhro.mentorapp.Model.Profession;
import com.sinhro.mentorapp.Model.ProfessionsList;
import com.sinhro.mentorapp.Utils.MyLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class MyRequestService {

    Context context;

    public MyRequestService(Context context) {
        this.context = context;

    }

    private String authKey;

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public void doRequest(Consumer<ProfessionsList> professionsListConsumer, String token)  {

        String url="http://192.168.43.140:8008";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String json="{'method' : 'getInfoUser', 'access_token' : '"+token+"'}";

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("_______json obj : " + response.toString());
                            ProfessionsList professionsList = deserialize(response.toString());
                            professionsListConsumer.accept(professionsList);

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("volley error 71");
                            error.printStackTrace();

                        }
                    });
            requestQueue.add(jsonObjectRequest);
            requestQueue.start();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private ProfessionsList deserialize(String json){
        ProfessionsList professionsList = new ProfessionsList(new ArrayList<>());
        ObjectMapper objectMapper = new ObjectMapper().configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
        );
        try {
            List<Profession> profs = new ArrayList<>();
            HashMap<Integer,Profession> map;
            map = ((HashMap<Integer,Profession>) objectMapper.readValue(json, HashMap.class));

            Set set = map.entrySet();
            for (Object en : set) {
                Profession prof = ((Profession) en);
                profs.add(prof);
            }



//            map.get()
//            map.forEach(new BiConsumer<Integer, Profession>() {
//                @Override
//                public void accept(Integer integer, Profession profession) {
//                    profs.add(profession);
//                }
//            });
            professionsList = new ProfessionsList(new ArrayList<>(map.values()));
            professionsList = new ProfessionsList(profs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return professionsList;
    }


    public static void test(Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://www.google.com";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLogger.getInstance().log("response is :" + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLogger.getInstance().log("error response");
            }
        });

        queue.add(stringRequest);
        queue.start();
    }

    public static ProfessionsList getProfessions(String vkKey){
        List<Profession> professions = new LinkedList<>();

        professions.add(new Profession("prof 1",0.2));
        professions.add(new Profession("prof 2",0.3));
        professions.add(new Profession("prof 3",0.4));
        professions.add(new Profession("prof 4",0.1));
        ProfessionsList profs = new ProfessionsList(professions);
        return profs;
    }

    public static EventsList loadEventsFor(String profession){
        List<FullEvent> eventsList = new LinkedList<>();

        eventsList.add(new FullEvent(profession,"222","12:12","com1",1.1,2.2));
        eventsList.add(new FullEvent(profession,"566","00:12","com1",13.1,266.2));
        eventsList.add(new FullEvent(profession,"212","23:45","com1",13.31,25.2));
        EventsList events = new EventsList(eventsList);

        return events;
    }


}

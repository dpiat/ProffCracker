package com.sinhro.mentorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sinhro.mentorapp.API.MyRequestService;
import com.sinhro.mentorapp.API.VkApi;
import com.sinhro.mentorapp.API.WebViewClient;
import com.sinhro.mentorapp.Model.EventsList;
import com.sinhro.mentorapp.Model.FullEvent;
import com.sinhro.mentorapp.Model.ProfessionsList;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class LoginActivity extends AppCompatActivity {

    LinearLayout loginLayout;
    Button loginButton;
    MyRequestService myRequestService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        myRequestService = new MyRequestService(this);

        String sas = "a4fsaacc_tok=21edd22&asdsda&";

        loginButton = findViewById(R.id.login_button);
        loginLayout = findViewById(R.id.login_layout);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                playWebView();
//                MyRequestService.test(getApplicationContext());
//                System.out.println("\n_____");
//                System.out.println(MyLogger.getInstance().getLogs());
//                System.out.println("\n_____");

//                moveToTasks();
//                    moveToProfessions();

                try {
                    LoginActivity.this.loadWebView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        try {
            loadWebView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWebView() throws IOException {
        VkApi  vkApi=new VkApi("7234771","1d6af65c1d6af65c1d6af65ce91d04928f11d6a1d6af65c4082309c5689a6e6e87c2870",this
        );
        String auth =vkApi.auth();
        WebView webview = findViewById(R.id.login_webView);
/*
      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      String json="{\n" +
              "  \"code\": \"1\",\n" +
              "  \"professions\": {\n" +
              "    \"0\": {\n" +
              "      \"name\": \"Philology\",\n" +
              "      \"percent\": \"0.041916167664670656\"\n" +
              "    },\n" +
              "    \"1\": {\n" +
              "      \"name\": \"Physics\",\n" +
              "      \"percent\": \"0.023952095808383235\"\n" +
              "    },\n" +
              "    \"2\": {\n" +
              "      \"name\": \"History\",\n" +
              "      \"percent\": \"0.023952095808383235\"\n" +
              "    },\n" +
              "    \"3\": {\n" +
              "      \"name\": \"IT\",\n" +
              "      \"percent\": \"0.017964071856287425\"\n" +
              "    },\n" +
              "    \"4\": {\n" +
              "      \"name\": \"Medicine\",\n" +
              "      \"percent\": \"0.005988023952095809\"\n" +
              "    },\n" +
              "    \"5\": {\n" +
              "      \"name\": \"Nentrepreneurship\",\n" +
              "      \"percent\": \"0.005988023952095809\"\n" +
              "    }\n" +
              "  }\n" +
              "}";


       HashMap<Integer,Profession> map =objectMapper.readValue(json,HashMap.class);
       System.out.println("map:"+map.toString());
     *//*  JsonNode matrix = objectMapper.readValue(json, JsonNode.class);
       System.out.println("here");
       matrix.forEach(array -> {
           System.out.println("Next Values:");
           System.out.println(array.at("/4").asDouble());
           System.out.println(array.at("/8").asInt());
       });*/


//       System.out.println("blia:"+profession.toString());
        webview.getSettings().setJavaScriptEnabled(true);
        WebViewClient webViewClient= new WebViewClient(this,
                new Consumer<String>() {
                    @Override
                    public void accept(String token) {
                        myRequestService.doRequest(
                                LoginActivity.this::moveToProfessions
                                ,token);
                    }
                });

        webview.setWebViewClient(webViewClient);
        webview.loadUrl(auth);
        System.out.println("auth is:"+auth);
    }



    private void moveToProfessions(ProfessionsList professionsList){
        Intent intent = new Intent(LoginActivity.this, ShowProfessionsActivity.class);

//        ProfessionsList profs = MyRequestService.getProfessions("admkasdnl1n2l213");

        intent.putExtra(ProfessionsList.class.getSimpleName(),professionsList);

        startActivity(intent);

    }

    private void moveToTasks(){
        Intent intent = new Intent(LoginActivity.this, ShowEventsListActivity.class);
        List<FullEvent> eventsList = new LinkedList<>();

        eventsList.add(new FullEvent("111","222","12:12","com1",1.1,2.2));
        eventsList.add(new FullEvent("100","566","00:12","com1",13.1,266.2));
        eventsList.add(new FullEvent("122","212","23:45","com1",13.31,25.2));
        EventsList tasks = new EventsList(eventsList);

        intent.putExtra(EventsList.class.getSimpleName(),tasks);

        startActivity(intent);

    }
/*
    private void playWebView(){
        VkApi vkApi= null;
        try {
            vkApi = new VkApi("7234771",
                    "1d6af65c1d6af65c1d6af65ce91d04928f11d6a1d6af65c4082309c5689a6e6e87c2870"
                    ,this
            );
            String auth = vkApi.auth("7234771");

            WebView webview = findViewById(R.id.login_webView);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setWebViewClient(new com.example.myapplication.WebViewClient());
            webview.loadUrl(auth);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

}

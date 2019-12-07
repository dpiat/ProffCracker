package com.sinhro.mentorapp.API;

import android.content.Context;
import android.os.Build;
//import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.util.HashMap;

/**
 * VK API sample implementation for Java 8.
 * @see https://github.com/liosha2007/vkontakte-api
 * @author liosha2007
 * @author aNNiMON
 */
public final class VkApi {
    private  static Context context;
    private static final String API_VERSION = "5.21";
    private static   String appid;
    private static final String AUTH_URL = "https://oauth.vk.com/authorize"
            + "?client_id={APP_ID}"
            + "&scope={PERMISSIONS}"
            + "&redirect_uri={REDIRECT_URI}"
            + "&display={DISPLAY}"
            + "&v={API_VERSION}"
            + "&response_type=token";

    private static final String API_REQUEST = "https://api.vk.com/method/{METHOD_NAME}"
            + "?{PARAMETERS}"
            + "&access_token={ACCESS_TOKEN}"
            + "&v=" + API_VERSION;



    private final String accessToken;

    public VkApi(String appId, String accessToken,Context context) throws IOException {
        this.accessToken = accessToken;
        this.context=context;
       this.appid=appId;
    }

     public String auth() throws IOException {
        String reqUrl = AUTH_URL
                .replace("{APP_ID}", appid)
                .replace("{PERMISSIONS}", "photos")
                .replace("{REDIRECT_URI}", "m.vk.com")
                .replace("{DISPLAY}", "page")
                .replace("{API_VERSION}", API_VERSION);

       return reqUrl;
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getDialogs() throws IOException {
        return invokeApi("messages.getDialogs", null);
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getHistory(String userId, int offset, int count, boolean rev) throws IOException {
        return invokeApi("messages.getHistory", Params.create()
                .add("user_id", userId)
                .add("offset", String.valueOf(offset))
                .add("count", String.valueOf(count))
                .add("rev", rev ? "1" : "0"));
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getAlbums(String userId) throws IOException {
        return invokeApi("photos.getAlbums", Params.create()
                .add("owner_id", userId)
                .add("photo_sizes", "1")
                .add("thumb_src", "1"));
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String invokeApi(String method, Params params) throws IOException {
         String parameters="";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            parameters = (params == null) ? "" : params.build();
        }
        String reqUrl = API_REQUEST
                .replace("{METHOD_NAME}", method)
                .replace("{ACCESS_TOKEN}", accessToken)
                .replace("{PARAMETERS}&", parameters);
        return invokeApi(reqUrl);
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String invokeApi(String requestUrl) throws IOException {
      /*  final StringBuilder result = new StringBuilder();
        System.out.println("request is:"+requestUrl);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest
                (Request.Method.GET, requestUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("req:"+response.toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });

        requestQueue.add(request);
        requestQueue.start();*/
        return "";
    }



    private static class Params {

        public static Params create() {
            return new Params();
        }

        private final HashMap<String, String> params;

        private Params() {
            params = new HashMap<>();
        }

        public Params add(String key, String value) {
            params.put(key, value);
            return this;
        }

//        @RequiresApi(api = Build.VERSION_CODES.N)
        public String build() {
            if (params.isEmpty()) return "";
            final StringBuilder result = new StringBuilder();
            params.keySet().forEach(key -> {
                result.append(key).append('=').append(params.get(key)).append('&');
            });
            return result.toString();
        }
    }
}
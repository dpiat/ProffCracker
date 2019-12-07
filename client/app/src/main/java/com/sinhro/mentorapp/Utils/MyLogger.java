package com.sinhro.mentorapp.Utils;

public class MyLogger {
    private static MyLogger ourInstance=null ;

    public static MyLogger getInstance() {
        if (ourInstance == null)
            ourInstance = new MyLogger();
        return ourInstance;
    }

    public StringBuilder stringBuilder;
    final String DEF_PREFIX = "MyLog";
    private MyLogger() {

        stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        log("Custom Logger successfully created.");
    }

    public void log(String prefix,String message){
        stringBuilder.append("\n");
        stringBuilder.append(prefix);
        stringBuilder.append(" : ");
        stringBuilder.append(message);
    }

    public void log (String message){
        log(DEF_PREFIX,message);
    }

    public String getLogs(){
        log("Getting logs");
        return stringBuilder.toString();
    }
}

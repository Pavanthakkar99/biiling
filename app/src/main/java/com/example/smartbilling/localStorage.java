package com.example.smartbilling;

import android.content.Context;
import android.content.SharedPreferences;

public class localStorage {
    private static localStorage mInst;
    private Context context;
    private static  String GLOBAL_KEY="sharedPrefrens";

    public localStorage(Context context) {
        this.context = context;
    }

    private static String MOBILE_NO="mobileNO";
    private static String METER_ID="meterId";
    private static String USER_NAME="userName";
    private static String USER_Id="userId";



    public static synchronized localStorage getInstance(Context context){
        if (mInst==null){
            mInst=new localStorage(context);

        }
        return mInst;


    }
    public void saveUser (String mobileNo,String userName,String meterId,String userId){
        SharedPreferences sharedPreferences=context.getSharedPreferences(GLOBAL_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(MOBILE_NO,mobileNo);
        editor.putString(METER_ID,meterId);
        editor.putString(USER_NAME,userName);
        editor.putString(USER_Id,userId);
        editor.apply();


    }
    public String getMobileNo(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(GLOBAL_KEY,Context.MODE_PRIVATE);
        return sharedPreferences.getString(MOBILE_NO,"");

    } public String getMeterId(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(GLOBAL_KEY,Context.MODE_PRIVATE);
        return sharedPreferences.getString(METER_ID,"");

    }
    public String getUserName(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(GLOBAL_KEY,Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME,"");

    } public String getUserId(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(GLOBAL_KEY,Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_Id,"");

    }




}

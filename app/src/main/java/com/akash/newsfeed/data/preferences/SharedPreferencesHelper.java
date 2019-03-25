package com.akash.newsfeed.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.akash.newsfeed.di.ApplicationContext;
import com.akash.newsfeed.di.PreferenceInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class SharedPreferencesHelper implements PreferencesHelper{


    private final SharedPreferences mShared;
    private SharedPreferences.Editor editor;

    public static final String file_name = "JSONData";
    public static final String key = "JSON";

    @Inject
    public SharedPreferencesHelper(@ApplicationContext Context context,
                                   @PreferenceInfo String file_name){
        mShared = context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
    }

    @Override
    public void setList(String key, List<ApiResponse.Article> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor = mShared.edit();
        editor.putString(key, json);
        editor.apply();
    }

    @Override
    public List<ApiResponse.Article> getList(String key) {
        String offlinedata = mShared.getString(key, null);
        List<ApiResponse.Article> list;

        if (offlinedata !=null){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<ApiResponse.Article>>(){}.getType();
            list = gson.fromJson(offlinedata, listType);
            //Log.d(TAG, "getting from SP and string length"+offlinedata.length()+" and list "+list.size());
            return list;
        }
        return null;
    }
}

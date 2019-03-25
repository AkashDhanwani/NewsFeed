package com.akash.newsfeed.data.preferences;


import com.akash.newsfeed.data.network.model_classes.ApiResponse;

import java.util.List;

public interface PreferencesHelper {

    void setList(String key, List<ApiResponse.Article> list);

    List<ApiResponse.Article> getList(String key);

}

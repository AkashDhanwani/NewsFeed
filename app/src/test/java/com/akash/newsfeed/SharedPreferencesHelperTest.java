package com.akash.newsfeed;

import android.content.Context;
import android.content.SharedPreferences;

import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.akash.newsfeed.data.preferences.SharedPreferencesHelper;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SharedPreferencesHelperTest {

    private static final String title= "dummyTitle";
    private static final String author = "Author";
    private static final String description = "description";
    private static final String url = "https://dummy.com";
    private static final String urlTOimage = "https://dummyimage.com";
    private static final String publishAt = "3/2/1 GO";
    private static final String content = "content is here";
    private static final String name = "Times of India";

    private List<ApiResponse.Article> list;
    private ApiResponse apiResponse;
    private ApiResponse.Article articleList;
    private ApiResponse.Source source;
    private SharedPreferencesHelper mMockSharedPreferenceHelper;
    private SharedPreferencesHelper mMockFailedSharedPreferenceHelper;

    @Mock
    private
    Context context;

    String file="file";
    String key= "Hi";

    @Mock
    private
    SharedPreferences mMockSharedPreferences;
    @Mock
    private
    SharedPreferences mMockBrokenSharedPreferences;
    @Mock
    private
    SharedPreferences.Editor mMockEditor;
    @Mock
    private
    SharedPreferences.Editor mMockBrokenEditor;

    @Before
    public void initMocks(){
        source = new ApiResponse().new Source(name);
        articleList = new ApiResponse().new Article(source,author, title,description, url, urlTOimage, publishAt, content);
        apiResponse = new ApiResponse();

        list = apiResponse.getArticles();

        mMockSharedPreferenceHelper = createMockSharedPreference();

        mMockFailedSharedPreferenceHelper = createBrokenMockSharedPreference();
    }

    //The tests aren't working, don't know why
    @Test
    public void saveAndRead(){
        mMockSharedPreferenceHelper.setList(key, list);

        List<ApiResponse.Article> list1 = mMockSharedPreferenceHelper.getList(key);
        Gson gson = new Gson();
        String json = gson.toJson(list1);
        String json1 = gson.toJson(list);

        assertThat("Checking that the string has been persisted and read correctly",
                json1, is(equalTo(json)));
    }

    @Test
    public void saveAndReadFail(){
        mMockFailedSharedPreferenceHelper.setList(key, list);

        List<ApiResponse.Article> list1 = mMockFailedSharedPreferenceHelper.getList(key);
        Gson gson = new Gson();
        String json = gson.toJson(list1);
        String json1 = gson.toJson(list);

        boolean find = json.equals(json1);

        assertThat("Makes sure writing to a broken SharedPreferencesHelper returns false", find,
                is(false));
    }

    private SharedPreferencesHelper createMockSharedPreference() {
        // Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
        // correctly.

        Gson gson = new Gson();
        String json = gson.toJson(list);

        when(mMockSharedPreferences.getString(key, null)).thenReturn(json);
        // Mocking a successful commit.
        when(mMockEditor.commit()).thenReturn(true);
        // Return the MockEditor when requesting it.
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);
        return new SharedPreferencesHelper(context, file);
    }

    private SharedPreferencesHelper createBrokenMockSharedPreference() {
        // Mocking a commit that fails.
        when(mMockBrokenEditor.commit()).thenReturn(false);
        // Return the broken MockEditor when requesting it.
        when(mMockBrokenSharedPreferences.edit()).thenReturn(mMockBrokenEditor);
        return new SharedPreferencesHelper(context, file);
    }
}

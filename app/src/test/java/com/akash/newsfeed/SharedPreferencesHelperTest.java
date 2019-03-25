package com.akash.newsfeed;

import android.content.SharedPreferences;

import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.akash.newsfeed.data.preferences.SharedPreferencesHelper;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

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
    private ApiResponse.Article articleList;
    private ApiResponse.Source source;
    private SharedPreferencesHelper mMockSharedPreferenceHelper;
    private SharedPreferencesHelper mMockFailedSharedPreferenceHelper;

    @Mock
    SharedPreferences mMockSharedPreferences;
    @Mock
    SharedPreferences mMockBrokenSharedPreferences;
    @Mock
    SharedPreferences.Editor mMockEditor;
    @Mock
    SharedPreferences.Editor mMockBrokenEditor;

    @Before
    public void initMocks(){
        source = new ApiResponse().new Source(name);
        articleList = new ApiResponse().new Article(source,author, title,description, url, urlTOimage, publishAt, content);

        list.add(articleList);

        //mMockSharedPreferenceHelper = createMockSharedPreference();

      //  mMockFailedSharedPreferenceHelper = createBrokenMockSharedPreference();
    }


//    private SharedPreferencesHelper createMockSharedPreference() {
//        // Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
//        // correctly.
//        when(mMockSharedPreference.getL)
//        when(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_EMAIL), anyString()))
//                .then
//        when(mMockSharedPreferences.getLong(eq(SharedPreferencesHelper.KEY_DOB), anyLong()))
//                .thenReturn(mSharedPreferenceEntry.getDateOfBirth().getTimeInMillis());
//        // Mocking a successful commit.
//        when(mMockEditor.commit()).thenReturn(true);
//        // Return the MockEditor when requesting it.
//        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);
//        return new SharedPreferencesHelper(mMockSharedPreferences);
//    }
}

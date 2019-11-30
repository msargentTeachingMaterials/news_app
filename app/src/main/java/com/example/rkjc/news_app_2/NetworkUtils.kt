package com.example.rkjc.news_app_2

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "https://newsapi.org/v1/"

//add moshi object
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

//add retrofit object
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()



//add interface for making network call for retrofit to implement

interface NewsApiService{
    // TODO implement api key hiding in build.gradle
    @GET("articles?=the-next-web&apiKey=027ae063a4b145d9a7dbf456b328d889")
    fun getNewsItems(): Deferred<Articles>
}

//make retrofit service available using object

object NewsApi{
    val retrofitService : NewsApiService by lazy{
        retrofit.create(NewsApiService::class.java)
    }
}


//
//import android.net.Uri;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Scanner;
//
//public class NetworkUtils {
//    final static String BASE_URL ="https://newsapi.org/v1/articles";
//    final static String PARAM_SOURCE = "";
//    public static final String PARAM_SORT_BY = "sortBy";
//    final static String PARAM_API_KEY = "apiKey";
//    final static String API_KEY = "027ae063a4b145d9a7dbf456b328d889";
//
//
//    public static URL buildUrl() {
//        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
//                .appendQueryParameter(PARAM_SOURCE, "the-next-web")
//                .appendQueryParameter(PARAM_SORT_BY, "latest")
//                .appendQueryParameter(PARAM_API_KEY, API_KEY)
//                .build();
//
//        URL url = null;
//        try {
//            url = new URL(builtUri.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        return url;
//    }
//
//    /**
//     * This method returns the entire result from the HTTP response.
//     *
//     * @param url The URL to fetch the HTTP response from.
//     * @return The contents of the HTTP response.
//     * @throws IOException Related to network and stream reading
//     */
//    public static String getResponseFromHttpUrl(URL url) throws IOException {
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        try {
//            InputStream in = urlConnection.getInputStream();
//
//            Scanner scanner = new Scanner(in);
//            scanner.useDelimiter("\\A");
//
//            boolean hasInput = scanner.hasNext();
//            if (hasInput) {
//                return scanner.next();
//            } else {
//                return null;
//            }
//        } finally {
//            urlConnection.disconnect();
//        }
//    }
//}

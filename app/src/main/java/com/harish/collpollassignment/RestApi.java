package com.harish.collpollassignment;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by harish on 22/09/16.
 */
public class RestApi {
    public static final String TAG = RestApi.class.getName();

    private Retrofit mRetrofit;
    private RestApiInterface restApiInterface;
    private Gson gson;

    public RestApi(String url) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);

        gson = new Gson();

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        restApiInterface = mRetrofit.create(RestApiInterface.class);

    }

    public Call<List<Train>> getTrains() {

        return restApiInterface.getTrains("trains");
    }

    public interface RestApiInterface
    {
        @GET("{path}")
        Call<List<Train>> getTrains(@Path("path") String path);

    }
}

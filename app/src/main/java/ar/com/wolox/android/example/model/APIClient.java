package ar.com.wolox.android.example.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * API Client - starts the session
 */
public final class APIClient {

    public static final String BASE_URL = "https://android-training.herokuapp.com";
    public static Retrofit retrofit;

    private APIClient() {

    }

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
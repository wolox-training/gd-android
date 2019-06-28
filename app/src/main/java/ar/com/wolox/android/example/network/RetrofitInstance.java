package ar.com.wolox.android.example.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitInstance
 */
public final class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://android-training.herokuapp.com/";

    /**
     *
     * @return RET
     */
<<<<<<< 6906d9660e42bce4526161a18af532046a008493

    private RetrofitInstance() {
        //not called
    }

    /**
     * @return retrofit
     */
=======
>>>>>>> login acceso done
    public static Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

<<<<<<< 6906d9660e42bce4526161a18af532046a008493
=======
    private RetrofitInstance() {
        //not called
    }

>>>>>>> login acceso done
}

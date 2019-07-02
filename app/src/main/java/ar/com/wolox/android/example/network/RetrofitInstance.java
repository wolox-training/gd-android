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
<<<<<<< 7695e23f3f1eeffcf25948879232819097d5a278

=======
<<<<<<< f659878fe59f73bd543640d4850a1338a51cb752
=======
>>>>>>> Login acceso (#3)
    private RetrofitInstance() {
        //not called
    }

    /**
     * @return retrofit
     */
<<<<<<< 7695e23f3f1eeffcf25948879232819097d5a278
=======
>>>>>>> Login acceso (#3)
>>>>>>> Login acceso (#3)
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

<<<<<<< 7695e23f3f1eeffcf25948879232819097d5a278
=======
<<<<<<< f659878fe59f73bd543640d4850a1338a51cb752
    private RetrofitInstance() {
        //not called
    }

=======
>>>>>>> Login acceso (#3)
>>>>>>> Login acceso (#3)
}

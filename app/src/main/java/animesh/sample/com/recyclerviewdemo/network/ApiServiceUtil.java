package animesh.sample.com.recyclerviewdemo.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceUtil {

    private static ApiServiceUtil mApiServiceUtil;
    private Retrofit mRetrofit;

    private final String BASE_URL = "https://reqres.in/";

    private ApiServiceUtil() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static synchronized ApiServiceUtil getInstance() {
        if (mApiServiceUtil == null) {
            mApiServiceUtil = new ApiServiceUtil();
        }
        return mApiServiceUtil;
    }

    public APIInterface getApiInterface() {
        return mRetrofit.create(APIInterface.class);
    }

}

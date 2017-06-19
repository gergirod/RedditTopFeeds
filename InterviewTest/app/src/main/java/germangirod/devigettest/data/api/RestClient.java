package germangirod.devigettest.data.api;

import germangirod.devigettest.BuildConfig;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by germangirod on 12/9/15.
 */
public class RestClient {

    private static Api REST_Client;

    static {
        setupRestClient();
    }

    private RestClient() {

    }

    public static Api get() {
        return REST_Client;
    }

    private static void setupRestClient() {

        Retrofit builder = new Retrofit.Builder().baseUrl(BuildConfig.ROOT)
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        REST_Client = builder.create(Api.class);
    }

    private static OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).addInterceptor(interceptor).build();
        return client;
    }
}

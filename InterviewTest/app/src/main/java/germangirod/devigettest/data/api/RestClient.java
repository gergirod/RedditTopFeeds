package germangirod.devigettest.data.api;

import com.squareup.okhttp.OkHttpClient;
import germangirod.devigettest.BuildConfig;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

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

        RestAdapter builder = new RestAdapter.Builder().setEndpoint(BuildConfig.ROOT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .build();

        REST_Client = builder.create(Api.class);
    }
}

package germangirod.devigettest.data.api;

import germangirod.devigettest.data.model.TopListing;
import rx.Observable;

/**
 * Created by germangirod on 12/9/15.
 */
public class TopApi implements Top{
    @Override public Observable<TopListing> getTopNews(String count, String limit, String after) {
        return RestClient.get().getTopNews(count, limit, after);
    }
}

package germangirod.devigettest.data.api;

import germangirod.devigettest.data.model.TopListing;
import rx.Observable;

/**
 * Created by germangirod on 12/9/15.
 */
public interface Top {

    Observable<TopListing> getTopNews(String count, String limit, String after);

}

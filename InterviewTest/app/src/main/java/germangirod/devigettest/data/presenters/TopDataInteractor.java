package germangirod.devigettest.data.presenters;

import germangirod.devigettest.data.model.Children;
import germangirod.devigettest.data.model.TopListing;
import java.util.List;
import rx.Subscription;

/**
 * Created by germangirod on 6/19/17.
 */

public interface TopDataInteractor {

    interface OnFinishedListener {
        void onFinished(List<Children> childrens, String after);

        void onError(String message);
    }

    Subscription getTopNews(OnFinishedListener listener, String totalItems, String after);

}

package germangirod.devigettest.data.presenters;

import germangirod.devigettest.data.api.TopApi;
import germangirod.devigettest.data.model.TopListing;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by germangirod on 6/19/17.
 */

public class TopListinsDataInteractorImpl implements TopDataInteractor {

    private TopApi topApi;
    private static final String LIMIT="10";


    public TopListinsDataInteractorImpl(){

        topApi = new TopApi();

    }

    @Override public Subscription getTopNews(final OnFinishedListener listener, final String totalItems, String after) {
        return topApi.getTopNews(totalItems, LIMIT, after)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TopListing>() {
                    @Override public void call(TopListing topListing) {
                        listener.onFinished(topListing.getData().getChildren(), topListing.getData().getAfter());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        listener.onError(throwable.getMessage());
                    }
                });
    }
}

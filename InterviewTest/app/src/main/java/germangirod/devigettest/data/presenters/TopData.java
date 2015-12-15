package germangirod.devigettest.data.presenters;

import germangirod.devigettest.data.api.Top;
import germangirod.devigettest.data.api.TopApi;
import germangirod.devigettest.data.model.TopListing;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by germangirod on 12/10/15.
 */
public class TopData {

    private TopPresenter topPresenter;
    private static final String LIMIT="10";

    public void setView(TopPresenter topPresenter){
        this.topPresenter = topPresenter;
    }

    public void getTopNews(String totalItems, String after){

        Top top = new TopApi();

        top.getTopNews(totalItems, LIMIT, after)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TopListing>() {
                    @Override public void call(TopListing topListing) {
                        topPresenter.getTopNews(topListing.getData().getChildren(), topListing.getData().getAfter());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        topPresenter.getTopNewsError(throwable);
                    }
                });


    }

}

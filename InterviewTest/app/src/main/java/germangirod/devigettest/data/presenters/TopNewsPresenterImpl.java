package germangirod.devigettest.data.presenters;

import android.os.Bundle;
import android.view.View;
import germangirod.devigettest.data.model.Children;
import germangirod.devigettest.data.model.TopListing;
import germangirod.devigettest.ui.MainActivity;
import java.util.List;
import org.parceler.Parcels;
import rx.Subscription;

import static germangirod.devigettest.util.Constants.SAVESTATE_AFTER_RESPONSE;
import static germangirod.devigettest.util.Constants.SAVESTATE_TOP_RESPONSE;

/**
 * Created by germangirod on 6/19/17.
 */

public class TopNewsPresenterImpl implements TopNewsPresenter, TopListinsDataInteractorImpl.OnFinishedListener{

    private MainActivity mainActivity;
    private TopDataInteractor topDataInteractor;
    private String totalItems;
    private String after;
    private Subscription subscription;

    public TopNewsPresenterImpl(MainActivity mainActivity, TopDataInteractor topDataInteractor, final String totalItems, String after){

        this.mainActivity = mainActivity;
        this.topDataInteractor = topDataInteractor;

    }

    @Override public void onFinished(List<Children> childrens, String after) {
        mainActivity.presentDataOnList(childrens, after);
    }

    @Override public void onError(String message) {
        mainActivity.showError(message);
    }

    @Override public void onLoadMore(String totalItems, String after) {
        subscription = topDataInteractor.getTopNews(this, totalItems, after);
    }

    @Override public void onRestoreData(Bundle bundle) {
        mainActivity.childrenList = Parcels.unwrap(bundle.getParcelable(SAVESTATE_TOP_RESPONSE));
        mainActivity.afters = bundle.getString(SAVESTATE_AFTER_RESPONSE);
        mainActivity.presentDataOnList(mainActivity.childrenList, mainActivity.afters);
    }

    @Override public void showProgress() {
        mainActivity.loading.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        mainActivity.loading.setVisibility(View.GONE);
    }

    @Override public void onActivityCreated() {
        subscription = topDataInteractor.getTopNews(this, totalItems, after);
    }

    @Override public void onStop() {
        subscription.unsubscribe();
    }

}

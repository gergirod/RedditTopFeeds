package germangirod.devigettest.data.presenters;

import android.os.Bundle;

/**
 * Created by germangirod on 6/19/17.
 */

public interface TopNewsPresenter {

    void showProgress();

    void hideProgress();

    void onActivityCreated();

    void onStop();

    void onError(String error);

    void onLoadMore(String totalItems, String after);

    void onRestoreData(Bundle bundle);

}

package germangirod.devigettest.data.presenters;

import germangirod.devigettest.data.model.Children;
import java.util.List;

/**
 * Created by germangirod on 12/10/15.
 */
public interface TopPresenter {

    void getTopNews(List<Children> childrens, String after);

    void getTopNewsError(Throwable throwable);

}

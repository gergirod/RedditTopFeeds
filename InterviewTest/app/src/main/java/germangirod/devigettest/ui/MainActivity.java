package germangirod.devigettest.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import germangirod.devigettest.R;
import germangirod.devigettest.data.model.Children;
import germangirod.devigettest.data.presenters.TopData;
import germangirod.devigettest.data.presenters.TopPresenter;
import germangirod.devigettest.ui.adapter.ListAdapter;
import germangirod.devigettest.ui.util.EndlessScrollListener;
import germangirod.devigettest.util.ImageSaverTask;
import java.util.List;
import org.parceler.Parcels;

public class MainActivity extends Activity implements ListAdapter.OnItemClickListener, TopPresenter {

    private static final String SAVESTATE_TOP_RESPONSE = "top_response";
    private static final String SAVESTATE_AFTER_RESPONSE = "after";
    @InjectView(R.id.top_list) ListView listView;
    @InjectView(R.id.loading) ProgressBar loading;
    int totalItems = 0;
    String afters = "";
    private ListAdapter listAdapter;
    private List<Children> childrenList;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        listAdapter = new ListAdapter(getApplicationContext());

        listAdapter.setOnItemClickListener(this);

        listView.setOnScrollListener(new EndlessScrollListener() {
            @Override public boolean onLoadMore(int page, int totalItemsCount) {

                totalItems += 10;
                getListItems();
                return true;
            }
        });

        listView.setAdapter(listAdapter);
        if (savedInstanceState != null) {

            childrenList = Parcels.unwrap(savedInstanceState.getParcelable(SAVESTATE_TOP_RESPONSE));
            afters = savedInstanceState.getString(SAVESTATE_AFTER_RESPONSE);
            getTopNews(childrenList, afters);
        } else {

            getListItems();
        }
    }

    public void getListItems() {
        loading.setVisibility(View.VISIBLE);
        TopData topData = new TopData();
        topData.setView(this);
        topData.getTopNews(String.valueOf(totalItems), afters);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVESTATE_TOP_RESPONSE, Parcels.wrap(listAdapter.childrens));
        outState.putString(SAVESTATE_AFTER_RESPONSE, afters);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override public void onItemClick(int position, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

        saveImageToGallery(url);
    }

    private void saveImageToGallery(final String url) {

        new ImageSaverTask(getApplication()).execute(url);
    }

    @Override public void getTopNews(List<Children> childrens, String after) {
        afters = after;
        if (totalItems < 10) {
            listAdapter.swapData(childrens);
        } else {
            listAdapter.addData(childrens);
        }

        loading.setVisibility(View.GONE);
    }

    @Override public void getTopNewsError(Throwable throwable) {
        loading.setVisibility(View.GONE);
    }
}

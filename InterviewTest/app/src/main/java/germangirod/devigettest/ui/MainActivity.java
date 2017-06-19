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
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import germangirod.devigettest.R;
import germangirod.devigettest.data.model.Children;
import germangirod.devigettest.data.presenters.TopListinsDataInteractorImpl;
import germangirod.devigettest.data.presenters.TopNewsPresenterImpl;
import germangirod.devigettest.ui.adapter.ListAdapter;
import germangirod.devigettest.ui.util.EndlessScrollListener;
import germangirod.devigettest.util.ImageSaverTask;
import java.util.List;
import org.parceler.Parcels;

import static germangirod.devigettest.util.Constants.SAVESTATE_AFTER_RESPONSE;
import static germangirod.devigettest.util.Constants.SAVESTATE_TOP_RESPONSE;

public class MainActivity extends Activity implements ListAdapter.OnItemClickListener {

    @BindView(R.id.top_list) ListView listView;
    public @BindView(R.id.loading) ProgressBar loading;
    int totalItems = 0;
    public String afters = "";
    private ListAdapter listAdapter;
    public List<Children> childrenList;
    private TopNewsPresenterImpl topNewsPresenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        topNewsPresenter = new TopNewsPresenterImpl(this, new TopListinsDataInteractorImpl(), String.valueOf(totalItems), afters);
        setListView();

        if (savedInstanceState != null) {
            topNewsPresenter.onRestoreData(savedInstanceState);
        } else {
            getListItems();
        }
    }

    private void setListView(){
        listAdapter = new ListAdapter(getApplicationContext());

        listAdapter.setOnItemClickListener(this);

        listView.setOnScrollListener(new EndlessScrollListener() {
            @Override public boolean onLoadMore(int page, int totalItemsCount) {

                totalItems += 10;
                topNewsPresenter.showProgress();
                topNewsPresenter.onLoadMore(String.valueOf(totalItemsCount),afters);
                return true;
            }
        });

        listView.setAdapter(listAdapter);
    }

    public void getListItems() {
        topNewsPresenter.showProgress();
        topNewsPresenter.onActivityCreated();
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


    public void presentDataOnList(List<Children> childrens, String after){
        topNewsPresenter.hideProgress();
        afters = after;
        if (totalItems < 10) {
            listAdapter.swapData(childrens);
        } else {
            listAdapter.addData(childrens);
        }
    }

    public void showError(String errorMessage){

        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

    }
}

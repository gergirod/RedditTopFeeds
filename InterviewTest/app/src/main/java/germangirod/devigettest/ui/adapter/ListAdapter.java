package germangirod.devigettest.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import germangirod.devigettest.R;
import germangirod.devigettest.data.model.Children;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by germangirod on 12/9/15.
 */
public class ListAdapter extends BaseAdapter {

    public List<Children> childrens = new ArrayList<>();
    Context context;
    OnItemClickListener listener;

    public ListAdapter(Context context){
        this.context= context;
    }


    @Override public int getCount() {
        return childrens.size();
    }

    @Override public Object getItem(int position) {
        return this.childrens.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void addData(List<Children> data){
        childrens.addAll(data);

        notifyDataSetChanged();
    }

    public void swapData(List<Children> data) {
        childrens.clear();

        if (data != null) {
            childrens.addAll(data);
        }

        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(this.context).inflate(R.layout.list_row, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView)convertView.findViewById(R.id.list_text);
            holder.time=(TextView)convertView.findViewById(R.id.list_hours);
            holder.author=(TextView)convertView.findViewById(R.id.list_author);
            holder.thumbnail=(ImageView)convertView.findViewById(R.id.thumbail);
            holder.comments =(TextView)convertView.findViewById(R.id.comments);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.title.setText(childrens.get(position).getData().getTitle());

        holder.time.setText(childrens.get(position).getData().getDate());

        holder.author.setText(childrens.get(position).getData().getAuthor());

        String thumb= childrens.get(position).getData().getThumbnail();

        if(thumb.equals(" ") || thumb.equals("")){
            holder.thumbnail.setVisibility(View.GONE);
        }else{
            holder.thumbnail.setVisibility(View.VISIBLE);
            Picasso.with(context).load(thumb).into(holder.thumbnail);

        }

        holder.comments.setText(childrens.get(position).getData().getNum_comments()+" comments");

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if(childrens.get(position).getData().getPreview()!=null){
                    onReviewClicked(position);
                }
            }
        });

        return convertView;
    }

    private void onReviewClicked(int position) {
        if (listener != null) {
            listener.onItemClick(position, getImageUrl(position));
        }
    }

    public String getImageUrl(int position){
        return childrens.get(position).getData().getPreview().getImages().get(0).getSource().getUrl();
    }


    public  class ViewHolder{

         public TextView title;
         public TextView time;
         public TextView author;
         public ImageView thumbnail;
        public TextView comments;


    }

    public  interface OnItemClickListener {
         void onItemClick(int position,  String url);
    }

}

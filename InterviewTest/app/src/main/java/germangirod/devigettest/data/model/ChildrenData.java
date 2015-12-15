package germangirod.devigettest.data.model;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.ReadableInstant;
import org.parceler.Parcel;

/**
 * Created by germangirod on 12/9/15.
 */
@Parcel
public class ChildrenData {

    public String domain;
    public String thumbnail;
    public String title;
    public String author;
    public int num_comments;
    public int created_utc;
    public ImagePreview preview;

    public ChildrenData(){

    }


    public String getDomain() {
        return domain;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public String getDate() {
        return createDate();
    }

    public ImagePreview getPreview() {
        return preview;
    }

    public String createDate(){
        DateTime date = new DateTime();

        Date dateCreated = new Date(created_utc*1000L);

        DateTime dateTimeCreated = new DateTime(dateCreated);
        ReadableInstant now = date;

        ReadableInstant timeCreated = dateTimeCreated;

        return transformTimeToHours(timeCreated,now);
    }

    private String transformTimeToHours(ReadableInstant now, ReadableInstant created){
        int hours = Hours.hoursBetween(now, created).getHours();

        return hours+" Hours";


    }

}

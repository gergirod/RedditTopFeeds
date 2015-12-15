package germangirod.devigettest.data.model;

import org.parceler.Parcel;

/**
 * Created by germangirod on 12/9/15.
 */
@Parcel
public class TopListing {

    public String kind;
    public DataListing data;

    public TopListing(){

    }

    public String getKind() {
        return kind;
    }

    public DataListing getData() {
        return data;
    }
}

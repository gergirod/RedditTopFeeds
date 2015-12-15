package germangirod.devigettest.data.model;

import java.util.List;
import org.parceler.Parcel;

/**
 * Created by germangirod on 12/9/15.
 */
@Parcel
public class DataListing {

    public String modhask;
    public List<Children> children;
    public String after;

    public DataListing(){

    }

    public String getModhask() {
        return modhask;
    }

    public List<Children> getChildren() {
        return children;
    }

    public String getAfter() {
        return after;
    }
}

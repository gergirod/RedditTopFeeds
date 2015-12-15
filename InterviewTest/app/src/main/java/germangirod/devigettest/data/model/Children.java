package germangirod.devigettest.data.model;

import org.parceler.Parcel;

/**
 * Created by germangirod on 12/9/15.
 */
@Parcel
public class Children {

    public String kind;

    public ChildrenData data;

    public Children(){

    }

    public String getKind() {
        return kind;
    }

    public ChildrenData getData() {
        return data;
    }
}

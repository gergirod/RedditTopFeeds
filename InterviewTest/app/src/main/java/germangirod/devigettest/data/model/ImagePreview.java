package germangirod.devigettest.data.model;

import java.util.List;
import org.parceler.Parcel;

/**
 * Created by germangirod on 12/9/15.
 */
@Parcel
public class ImagePreview {

    public List<Images> images;
    
    public ImagePreview(){

    }

    public List<Images> getImages() {
        return images;
    }
}

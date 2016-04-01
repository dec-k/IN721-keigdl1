package bit.keigdl1.aboutdunedin;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by keigdl1 on 1/04/2016.
 */
public class CustomAdapter extends ArrayAdapter<FunThing>{

    public CustomAdapter(Context context, int resource, FunThing[] objects) {
        super(context, resource, objects);
    }
}

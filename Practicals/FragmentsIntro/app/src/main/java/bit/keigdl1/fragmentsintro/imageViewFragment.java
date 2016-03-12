package bit.keigdl1.fragmentsintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Decura on 3/13/2016.
 */
public class imageViewFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Bind this classes code to the specified XML, kinda.
        //Inflate just converts the specified XML file into a set of controls that can be used.
        View fragmentView = inflater.inflate(R.layout.imageviewfrag, container, false);
        return fragmentView;
    }
}

package bit.keigdl1.fragmentsintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Decura on 3/13/2016.
 */
public class listViewFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Bind this classes code to the specified XML, kinda.
        //Inflate just converts the specified XML file into a set of controls that can be used.
        View fragmentView = inflater.inflate(R.layout.listview_fragment, container, false);

        //Get a ref to the listview object
        ListView lv = (ListView) fragmentView.findViewById(R.id.lvThings);

        //Make an array of strings
        String[] myThings = {"Peppers", "Toots", "Spicy Memes", "Stock Objects", "Froggos", "Doggos"};

        //Setup an array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( getActivity(),
                                                                 android.R.layout.simple_spinner_item,
                                                                 myThings);
        //Bind the adapter to the control
        lv.setAdapter(adapter);

        //Return a fully setup fragmentView.
        return fragmentView;
    }
}

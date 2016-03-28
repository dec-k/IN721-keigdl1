package bit.keigdl1.nounquiz;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Decura on 3/28/2016.
 */
public class DialogCorrect extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        //Get a ref to the dialog window and provide it a title
        Dialog dWin = getDialog();
        dWin.setTitle("Correct Answer!");

        //Inflate XML into an actual set of screen controls
        View dialogView = inflater.inflate(R.layout.correct_fragment,container);

        //Return the view
        return dialogView;
    }
}

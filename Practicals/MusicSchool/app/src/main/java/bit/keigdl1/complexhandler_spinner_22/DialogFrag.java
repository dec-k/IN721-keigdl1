package bit.keigdl1.complexhandler_spinner_22;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Decura on 3/19/2016.
 */
public class DialogFrag extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Get ref to window and set its title
        Dialog dialogWindow = getDialog();
        dialogWindow.setTitle("Confirm Enrolment?");

        //Inflate the xml, turning it into interactive screen controls.
        View dialogView = inflater.inflate(R.layout.dialog_fragment_display, container);

        //Ret
        return dialogView;
    }

}

package bit.keigdl1.complexhandler_spinner_22;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Decura on 3/19/2016.
 */
public class ConfirmationDialog extends DialogFragment {
    //Empty constructor
    public ConfirmationDialog(){}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Create a builder to bypass the XML & inflation portion of making a dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Set icon
        builder.setIcon(R.drawable.dootdoot);
        //Title
        builder.setTitle("Confirm Enrolment?");
        //Set confirm/cancel boxes
        builder.setNegativeButton("Yes",new YesButtonHandler());
    }

}

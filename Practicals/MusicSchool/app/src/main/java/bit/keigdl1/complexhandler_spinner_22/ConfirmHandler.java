package bit.keigdl1.complexhandler_spinner_22;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by Decura on 3/19/2016.
 */
public class ConfirmHandler extends DialogFragment {


    public class YesButtonHandler implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(true);
        }
    }
}

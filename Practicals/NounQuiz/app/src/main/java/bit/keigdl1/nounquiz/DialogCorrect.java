package bit.keigdl1.nounquiz;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        //Ref to next button, later on this will let me notify the main activity
        //that the fragment button has been clicked.
        Button btnNext = (Button)dialogView.findViewById(R.id.btnNext);

        //Bind click handler
        btnNext.setOnClickListener(new nextButtonHandler());

        //Return the view
        return dialogView;
    }

    public class nextButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //When button bound to this handler is clicked, the fragment will close.
            //If I needed to, I could return data here.
            QuestionAct qa = (QuestionAct) getActivity();
            qa.DismissFragment();
        }
    }
}

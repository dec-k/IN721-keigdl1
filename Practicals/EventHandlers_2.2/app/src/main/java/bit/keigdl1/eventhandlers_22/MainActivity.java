package bit.keigdl1.eventhandlers_22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtUsername.setOnKeyListener(new onKeyEventHandler());
    }

    //Inner class for key listener - prompts toast when '@' is typed.
    public class onKeyEventHandler implements View.OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            //Read the contents of the editText & save it into a variable.
            int viewID = v.getId();
            EditText etInput = (EditText) findViewById(viewID);
            String userName = etInput.getText().toString();

            //When enter is pressed, check if username is the appropriate length,
            //then generate a toast with correct feedback.
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                if(userName.length() == 8){
                    Toast statusToast = Toast.makeText(MainActivity.this, "Username accepted, " + userName + ".", Toast.LENGTH_LONG);
                    statusToast.show();
                }else{
                    Toast statusToast = Toast.makeText(MainActivity.this, "Username must be 8 characters long. Try again.", Toast.LENGTH_LONG);
                    statusToast.show();
                }

                //Clears the users entered text for clarify & less confusion.
                etInput.setText("");

                //Confirms that this onKey event has been handled successfully.
                return true;
            }
            return false;
        }
    }
}
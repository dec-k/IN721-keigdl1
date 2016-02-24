package bit.keigdl1.eventhandlers_21a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtNoAtSymbol = (EditText) findViewById(R.id.txtNoAtSymbol);
        txtNoAtSymbol.setOnKeyListener(new onKeyEventHandler());
    }

    //Inner class for key listener - prompts toast when '@' is typed.
    public class onKeyEventHandler implements View.OnKeyListener{

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_UP){
                //Makes this toast only show on a keydown event.
                if(keyCode == KeyEvent.KEYCODE_AT) {
                    //A key event is triggered on key up AND down,
                    // so you'll see two toasts if the event time isn't specified.
                    Toast statusToast = Toast.makeText(MainActivity.this, "Don't Type @!", Toast.LENGTH_SHORT);
                    statusToast.show();
                }
            }

            return false;
        }
    }
}

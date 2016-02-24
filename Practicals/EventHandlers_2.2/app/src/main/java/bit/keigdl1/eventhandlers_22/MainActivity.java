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

        EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtUsername.setOnKeyListener(new onKeyEventHandler());
}

    //Inner class for key listener - prompts toast when '@' is typed.
    public class onKeyEventHandler implements View.OnKeyListener{

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    Toast statusToast = Toast.makeText(MainActivity.this, "Don't Type @!", Toast.LENGTH_SHORT);
                    statusToast.show();
            }
        return false;
    }
}
    public void test(){

    }

}

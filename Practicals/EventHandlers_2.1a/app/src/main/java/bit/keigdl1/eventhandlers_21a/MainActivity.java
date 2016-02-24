package bit.keigdl1.eventhandlers_21a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtNoAtSymbol = (EditText) findViewById(R.id.txtNoAtSymbol);
    }

    //Inner class for key listener - prompts toast when '@' is typed.
    
}

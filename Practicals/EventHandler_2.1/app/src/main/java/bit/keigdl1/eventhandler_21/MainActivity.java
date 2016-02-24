package bit.keigdl1.eventhandler_21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnClickHandler = (Button) findViewById(R.id.btnEventTester);
        btnClickHandler.setOnClickListener(new SingleClickHandler());
        btnClickHandler.setOnLongClickListener(new LongClickHandler());
    }

    public class SingleClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast statusToast = Toast.makeText(MainActivity.this, "Single Click Acknowledged", Toast.LENGTH_SHORT);
            statusToast.show();
        }
    }

    public class LongClickHandler implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Toast statusToast = Toast.makeText(MainActivity.this, "Long Click Acknowledged", Toast.LENGTH_SHORT);
            statusToast.show();
            return true;
        }
    }
}

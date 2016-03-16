package bit.keigdl1.textcolourpassing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView txtCol = (TextView) findViewById(R.id.textView);
        //Save text colour as int
        int hexColour = txtCol.getCurrentTextColor();

        //Create an intent, pops activity stack.
        Intent returnIntent = new Intent();

        //Load the data you want to be returned
        returnIntent.putExtra("colourHex", hexColour);
        setResult(Activity.RESULT_OK, returnIntent);

        //Finish processing the intent change
        finish();
    }
}

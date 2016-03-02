package bit.keigdl1.intents_multiact_31;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_c);

        //Grab a ref to button control
        Button btnNextAct = (Button) findViewById(R.id.btnNextAct);
        //Bind the click listener
        btnNextAct.setOnClickListener(new btnClickHandler());
    }

    public class btnClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //Create a uri (why is it not url?) saved to a variable.
            Uri youtubeUrl = Uri.parse("http://youtube.com");
            //Create an implicit intent.
            Intent bIntent = new Intent(Intent.ACTION_VIEW, youtubeUrl);
            //Start the specified activity
            startActivity(bIntent);
        }
    }
}

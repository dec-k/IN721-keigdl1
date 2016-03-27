package bit.keigdl1.nounquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        //ref btn
        Button btnBackToStart = (Button) findViewById(R.id.btnAgain);
        //bind handler
        btnBackToStart.setOnClickListener(new btnClickHandler());
    }

    public class btnClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent goMainIntent = new Intent(EndActivity.this,LaunchActivity.class);
            startActivity(goMainIntent);
        }
    }
}

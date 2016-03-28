package bit.keigdl1.nounquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //Get a ref to the intent that launched this activity
        Intent whoLaunchedMe = getIntent();

        //Get extra data from putExtra
        int userScore = whoLaunchedMe.getIntExtra("UserScore",0);

        //Get ref to textview that will output score
        TextView txtScore = (TextView) findViewById(R.id.txtResult);
        txtScore.setText(userScore + "/11");

        //Ref & bind button to go back to the start
        Button backToStart = (Button) findViewById(R.id.btnRetry);
        backToStart.setOnClickListener(new retryButtonHandler());
    }

    public class retryButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent backToStart = new Intent(ScoreActivity.this, LaunchActivity.class);
            startActivity(backToStart);
        }
    }
}

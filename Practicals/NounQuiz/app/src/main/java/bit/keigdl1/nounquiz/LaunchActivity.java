package bit.keigdl1.nounquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        //Set ref to button
        Button goQuestionBtn = (Button) findViewById(R.id.btnStart);
        //bind btn to control
        goQuestionBtn.setOnClickListener(new btnClickHandler());
    }

    public class btnClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent goMainIntent = new Intent(LaunchActivity.this,QuestionAct.class);
            startActivity(goMainIntent);
        }
    }
}

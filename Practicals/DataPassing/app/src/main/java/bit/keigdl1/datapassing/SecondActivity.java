package bit.keigdl1.datapassing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //btn ref
        Button backBtn = (Button) findViewById(R.id.btnMain);
        //bind listener
        backBtn.setOnClickListener(new GiveDataBackToMain());
    }

    public class GiveDataBackToMain implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //Declare intent
            Intent changeToMain = new Intent(SecondActivity.this,FirstActivity.class);

            //Get ref to editText
            EditText usernameInput = (EditText) findViewById(R.id.editUsername);
            //Save input from field as string
            String input = usernameInput.getText().toString();
            //Attach extra data to the intent
            changeToMain.putExtra("username",input);

            //Swap intents back to main
            startActivity(changeToMain);
        }
    }
}

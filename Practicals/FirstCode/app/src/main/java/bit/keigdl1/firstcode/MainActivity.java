package bit.keigdl1.firstcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtRandomString = (TextView) findViewById(R.id.dogBreed);

        txtRandomString.setText(getRandomDog());
    }

    String getRandomDog(){
        String dogBreed="";

        Random r = new Random();
        int dogVal = r.nextInt(4);

        switch(dogVal){
            case 0:
                dogBreed="Shiba";
                break;
            case 1:
                dogBreed="shooby doggo";
                break;
            case 2:
                dogBreed="Tiny Doggo";
                break;
            case 3:
                dogBreed="Big Doggo";
                break;
        }

        return dogBreed;
    }
}

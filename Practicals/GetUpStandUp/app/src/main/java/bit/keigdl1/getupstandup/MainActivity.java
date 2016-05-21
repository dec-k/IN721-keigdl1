package bit.keigdl1.getupstandup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup refs
        img = (ImageView)findViewById(R.id.imageView);
        btn = (Button)findViewById(R.id.btnAnimate);

        //Bind
        btn.setOnClickListener(new animateClickHandler());
    }

    public class animateClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //Set image here so it's invisible at load.
            img.setImageResource(R.drawable.cough);

            //Start animating
            YoYo.with(Techniques.StandUp)
                    .duration(500)
                    .playOn(img);
        }
    }
}

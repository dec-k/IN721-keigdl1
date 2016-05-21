package bit.keigdl1.explodeimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.easyandroidanimations.library.BounceAnimation;
import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ref views
        img = (ImageView)findViewById(R.id.imageView);
        btn = (Button)findViewById(R.id.btnExplode);

        //bind
        btn.setOnClickListener(new explodeClickHandler());
    }

    public class explodeClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //Create exploder and animate it
            new ExplodeAnimation(img).animate();
        }
    }
}

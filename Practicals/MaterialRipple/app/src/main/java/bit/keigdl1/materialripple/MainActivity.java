package bit.keigdl1.materialripple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {
    RippleBackground rippleBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ripple animation
        rippleBackground = (RippleBackground)findViewById(R.id.content);

        ImageView iv = (ImageView)findViewById(R.id.centerImage);

        iv.setOnClickListener(new rippleClickHandler());
    }

    public class rippleClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            rippleBackground.startRippleAnimation();
        }
    }
}

package bit.keigdl1.fragmentsintro;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tablet);

        //Get ref to buttons
        Button btnImageFrag = (Button) findViewById(R.id.btnImgFragment);
        Button btnListViewFrag = (Button) findViewById(R.id.btnListFragment);

        //Bind a custom click handler to imagefrag
        btnImageFrag.setOnClickListener(new onImgBtnClickHandler());
        btnListViewFrag.setOnClickListener(new onLvBtnClickHandler());
    }

    public class onImgBtnClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v){
            //Create an instance of my fragment class
            Fragment dynamicFragment = new imageViewFragment();
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

            //Begin fragment transaction
            //Use the fragment manager to begin a transition.
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

            //Replace the placeholder with the fragment
            ft.replace(R.id.ph1, dynamicFragment);

            //Complete the transaction
            ft.commit();
        }
    }

    public class onLvBtnClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v){
            //Create an instance of my fragment class
            Fragment dynamicFragment = new listViewFragment();
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

            //Begin fragment transaction
            //Use the fragment manager to begin a transition.
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

            //Replace the placeholder with the fragment
            ft.replace(R.id.ph2, dynamicFragment);

            //Complete the transaction
            ft.commit();
        }
    }
}

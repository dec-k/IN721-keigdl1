package bit.keigdl1.langprefapp;

import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    //Attributes
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //If a shared pref doesnt exist, it is created.
        prefs = getSharedPreferences("sharedPrefs",MODE_PRIVATE);
        prefsEditor = prefs.edit();
    }

    public class SetPrefsClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View arg0){
            //Find out which language button the user has selected
            RadioGroup rg1 = (RadioGroup) findViewById(R.id.rdoGrp1);
            int checkedId = rg1.getCheckedRadioButtonId();
            RadioButton checkedButton = (RadioButton) findViewById(checkedId);
            String checkedLanguage = checkedButton.getText().toString();

            //Store the chosen language into prefs
            prefsEditor.putString("language",checkedLanguage);

            RadioGroup rg2 = (RadioGroup)findViewById(R.id.rdoGrp2);
            int checkedId2 = rg2.getCheckedRadioButtonId();
            RadioButton checkedButton2 = (RadioButton)findViewById(checkedId2);
            String checkedColour = checkedButton2.getText().toString();

            //store chosen colour too
            prefsEditor.putString("colour",checkedColour);
            prefsEditor.commit();
        }
    }
}

package bit.keigdl1.langprefapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

        //ref button + bind
        Button btnUpdatePrefs = (Button)findViewById(R.id.btnSetPref);
        btnUpdatePrefs.setOnClickListener(new SetPrefsClickHandler());

        //fetched stored lang
        //returns null if kvp not set
        String languagePreference = prefs.getString("language",null);
        String colourPref = prefs.getString("colour",null);
        if(languagePreference!=null){
            String greetingInChosenLanguage = updateActivity(languagePreference);

            //update textview
            TextView greeting = (TextView)findViewById(R.id.txtHello);
            greeting.setText(greetingInChosenLanguage);

        }
        if(colourPref!=null){
            updateColour(colourPref);
        }
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

            //update textview
            TextView greeting = (TextView)findViewById(R.id.txtHello);
            greeting.setText(updateActivity(checkedLanguage));
            updateColour(checkedColour);
        }
    }

    public String updateActivity(String language){
        String greeting = "";
        switch(language){
            case("French"):
                greeting = "Hon Hon Hon";
                break;
            case("German"):
                greeting = "Hallo Welt";
                break;
            case("Spanish"):
                greeting = "Hola Mundo";
                break;
        }

        return greeting;
    }

    public void updateColour(String colour){
        TextView greeting = (TextView)findViewById(R.id.txtHello);
        switch(colour){
            case("Red"):
                greeting.setTextColor(Color.RED);
                break;
            case("Blue"):
                greeting.setTextColor(Color.BLUE);
                break;
            case("Green"):
                greeting.setTextColor(Color.GREEN);
                break;
        }
    }
}

package bit.keigdl1.complexcontrols_22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Make a reference to form controls
        RadioGroup rdoGrpInstruments = (RadioGroup) findViewById(R.id.radioGroup);
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new onSubmitClick());
    }

    //Inner class for submit button handling
    public class onSubmitClick implements View.OnClickListener{

        @Override
        public void onClick(View v){
            //Determine which radio button has been checked.
            boolean checked = ((RadioButton) v).isChecked();

            //Create reference to confirmation txtView so I can edit its contents
            int viewID = v.getId();
            TextView txtConf = (TextView) findViewById(viewID);

            //React depending on which radio button is checked
            switch(v.getId()) {
                case R.id.rdoAccordion:
                    if(checked)
                        txtConf.setText("Accordion has been selected.");
                    break;

                case R.id.rdoBassoon:
                        txtConf.setText("Bassoon has been selected.");
                    break;

                case R.id.rdoCello:
                        txtConf.setText("Cello has been selected.");
                    break;
            }
        }
    }
}

package bit.keigdl1.complexhandler_spinner_22;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup confirm button & its listener.
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new onClickHandler());

        //Create spinner reference
        Spinner spnMonthPicker = (Spinner) findViewById(R.id.spnMonthPicker);

        //Create an adapter, which hooks the strings.xml file and the specified array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.months_array, android.R.layout.simple_spinner_item);

        //Control spinner dropdown layout/style (Can be customised with your own res xml file)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Bind the adapter to the spinner (this gives it the above code.)
        spnMonthPicker.setAdapter(adapter);
    }

    public class onClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //Setup references to the three radioButtons
            RadioButton rdoAccordion = (RadioButton) findViewById(R.id.rdoAccordion);
            RadioButton rdoBassoon = (RadioButton) findViewById(R.id.rdoBassoon);
            RadioButton rdoCello = (RadioButton) findViewById(R.id.rdoCello);

            //And a reference to the confirm dialog textView
            TextView txtConfirmDialog = (TextView) findViewById(R.id.txtConfirmText);

            //...and a reference to the spinner.
            //Patricia if you see this, I made this reference twice. Once in onCreate and once here,
            //is this really bad form?
            Spinner spnMonthPicker = (Spinner) findViewById(R.id.spnMonthPicker);

            //Determine which one is checked
            if (rdoAccordion.isChecked()){
                String selectedMonth = (String) spnMonthPicker.getSelectedItem();
                txtConfirmDialog.setText("You are now enrolled for Accordion lessons in " + selectedMonth + ".");
            }else if(rdoBassoon.isChecked()){
                String selectedMonth = (String) spnMonthPicker.getSelectedItem();
                txtConfirmDialog.setText("You are now enrolled for Bassoon lessons in " + selectedMonth + ".");
            }else if(rdoCello.isChecked()){
                String selectedMonth = (String) spnMonthPicker.getSelectedItem();
                txtConfirmDialog.setText("You are now enrolled for Cello lessons in " + selectedMonth + ".");
            }
        }
    }
}

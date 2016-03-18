package bit.keigdl1.complexhandler_rdogrp_22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup confirm button & its listener.
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new onClickHandler());
    }

    public class onClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //Setup references to the three radioButtons
            RadioButton rdoAccordion = (RadioButton) findViewById(R.id.rdoAccordion);
            RadioButton rdoBassoon = (RadioButton) findViewById(R.id.rdoBassoon);
            RadioButton rdoCello = (RadioButton) findViewById(R.id.rdoCello);

            //...and a reference to the confirm dialog textView
            TextView txtConfirmDialog = (TextView) findViewById(R.id.txtConfirmDialog);

            //Determine which one is checked
            if (rdoAccordion.isChecked()){
                txtConfirmDialog.setText("You are now enrolled for Accordion lessons.");
            }else if(rdoBassoon.isChecked()){
                txtConfirmDialog.setText("You are now enrolled for Bassoon lessons.");
            }else if(rdoCello.isChecked()){
                txtConfirmDialog.setText("You are now enrolled for Cello lessons.");
            }
        }
    }
}

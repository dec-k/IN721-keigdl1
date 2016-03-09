package bit.keigdl1.aboutdunedin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup the navigation menu
        setupNavOptionsList();

        //Get ref to listview
        ListView listOptions = (ListView) findViewById(R.id.listOptions);
        listOptions.setOnItemClickListener(new ListViewChangeIntentHandler());
    }

    public void setupNavOptionsList() {
        //Create an array with the available options
        String[] options = {"Services", "Fun Things to do", "Dining", "Shopping"};

        //Create an adapter
        ArrayAdapter<String> optionsListAdapter = new ArrayAdapter<String>(this, R.layout.option_list_item, options);

        //Get ref to list
        ListView listOptions = (ListView) findViewById(R.id.listOptions);

        //Bind adapter to ref of list
        listOptions.setAdapter(optionsListAdapter);
    }

    public class ListViewChangeIntentHandler implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Get a ref to the clicked item in the listview
            String clickedItem = (String) parent.getItemAtPosition(position).toString();
            Intent targetActivity;

            //Switch depending on the selected item
            switch(clickedItem){
                case "Services":
                    targetActivity = new Intent(MainActivity.this, Services.class);
                    break;
                case "Fun Things to do":
                    targetActivity = new Intent(MainActivity.this, FunThings.class);
                    break;
                case "Dining":
                    targetActivity = new Intent(MainActivity.this, Dining.class);
                    break;
                case "Shopping":
                    targetActivity = new Intent(MainActivity.this, Shopping.class);
                    break;
                default:
                    targetActivity = null;
            }

            if(targetActivity != null){
                //swap to the saved activity
                startActivity(targetActivity);
            }

        }
    }
}

package bit.keigdl1.aboutdunedin;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //Fun things array declaration
    FunThing[] funThingsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup the navigation menu
        setupNavOptionsList();

        //Generate all the fun things
        generateArrayOfFunThings();

        //Get ref to listview
        ListView listOptions = (ListView) findViewById(R.id.listOptions);
        listOptions.setOnItemClickListener(new ListViewChangeIntentHandler());
    }

    public void generateArrayOfFunThings(){
        //Grab the drawables using a Resource getter thing (thanks android)
        Resources rm = getResources();

        //lots of drawables
        Drawable larnach = rm.getDrawable(R.drawable.larnach_castle);
        Drawable moana = rm.getDrawable(R.drawable.moana_pool);
        Drawable monarch = rm.getDrawable(R.drawable.monarch);
        Drawable octagon = rm.getDrawable(R.drawable.octagon);
        Drawable olveston = rm.getDrawable(R.drawable.olveston);
        Drawable peninsula = rm.getDrawable(R.drawable.peninsula);
        Drawable saltwater = rm.getDrawable(R.drawable.salt_water_pool);
        Drawable speights = rm.getDrawable(R.drawable.speights_brewery);
        Drawable stkilda = rm.getDrawable(R.drawable.st_kilda_beach);
        Drawable taeri = rm.getDrawable(R.drawable.taeri_gorge_railway);

        //instantiate funthingsarray
        funThingsArray = new FunThing[10];

        //load it up homesauce
        funThingsArray[0] = new FunThing("Larnach Castle", larnach);
        funThingsArray[1] = new FunThing("Moana Pool", moana);
        funThingsArray[2] = new FunThing("Monarch", monarch);
        funThingsArray[3] = new FunThing("The Octagon", octagon);
        funThingsArray[4] = new FunThing("Olveston", olveston);
        funThingsArray[5] = new FunThing("Peninsula", peninsula);
        funThingsArray[6] = new FunThing("Salt-water Pools", saltwater);
        funThingsArray[7] = new FunThing("Speights Brewery",speights);
        funThingsArray[8] = new FunThing("St. Kilda Beach",stkilda);
        funThingsArray[9] = new FunThing("Taeri Gorge Railway",taeri);
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

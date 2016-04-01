package bit.keigdl1.aboutdunedin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FunThings extends AppCompatActivity {
    //Fun things array declaration
    FunThing[] funThingsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_things);

        //Generate a whole lot of fun things
        generateArrayOfFunThings();

        //Create an array adapter
        CustomAdapter ftAdapter = new CustomAdapter(this, R.layout.custom_list_item, funThingsArray);

        //Bind lv to adapter
        ListView lvFun = (ListView) findViewById(R.id.lvFunStuff);
        lvFun.setAdapter(ftAdapter);
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

    public class CustomAdapter extends ArrayAdapter<FunThing>{

        public CustomAdapter(Context context, int resource, FunThing[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container){
            //Make inflater to get turn XML into form controls
            LayoutInflater inflater = LayoutInflater.from(FunThings.this);

            //Inflate custom view
            View customView = inflater.inflate(R.layout.custom_list_item, container, false);

            //Ref to the controls OF THE CUSTOM VIEW
            ImageView iv = (ImageView) customView.findViewById(R.id.ivFun);
            TextView tv = (TextView) customView.findViewById(R.id.txtFun);

            //Get current input item (an instance of FunThing)
            FunThing currentItem = getItem(position);

            //Populate controls defined above with the instance data
            iv.setImageDrawable(currentItem.ftImg);
            tv.setText(currentItem.ftName);

            //return view
            return customView;
        }
    }
}

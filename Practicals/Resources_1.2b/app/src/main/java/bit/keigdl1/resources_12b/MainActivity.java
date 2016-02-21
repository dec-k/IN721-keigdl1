package bit.keigdl1.resources_12b;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create resource grabber
        Resources resolver = getResources();

        //Create reference to the int array in integers.xml
        int datesArray[] = resolver.getIntArray(R.array.FebFridays);

        //Create reference to the text field so we can code onto it.
        TextView txtFebFri = (TextView) findViewById(R.id.FebFridayString);

        //Output the entire array as a string. Not very elegant, was having trouble with for loop
        //for some reason.
        txtFebFri.append(Arrays.toString(datesArray));
    }
}

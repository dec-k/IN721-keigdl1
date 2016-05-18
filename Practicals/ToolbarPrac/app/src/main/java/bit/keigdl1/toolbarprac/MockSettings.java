package bit.keigdl1.toolbarprac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MockSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.custom_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        //Decided to create variables here, since I'm gonna need them in 3 seperate places.
        String itemTitle = (String) menuItem.getTitle();

        //Declare intent
        Intent intent;

        switch(itemTitle){
            case "Home":
                //Create intent
                intent = new Intent(this, MainActivity.class);
                //Start activity
                startActivity(intent);
                break;
            case "Settings":
                //Create intent
                intent = new Intent(this, MockSettings.class);
                //Start activity
                startActivity(intent);
                break;
            case "Search":
                //Create intent
                intent = new Intent(this, MockSearch.class);
                //Start activity
                startActivity(intent);
                break;
        }

        //Return indicator that the method has finished processing. This is not an indicator of a fail-state.
        return true;
    }
}

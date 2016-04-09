package bit.keigdl1.formattedfile;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    //Attributes
    public static String JSONFileName = "dunedin_events.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeJSONString(JSONFileName);
    }

    public String makeJSONString(String fileName){
        //The asset filename as a string
        String assetFileName = fileName;

        //String which will be loaded with JSON data and returned
        String JSONInput = "";

        //Create an asset manager
        AssetManager am = getAssets();

        try {
            //Create an input stream so we can access the file's contents in a raw form
            InputStream inputStream = am.open(assetFileName);

            //Assess the number of bytes in the file we're going to read in
            int fileSizeInBytes = inputStream.available();
            byte[] JSONBuffer = new byte[fileSizeInBytes];

            //Read the stream into the buffer and then close it
            inputStream.read(JSONBuffer);
            inputStream.close();

            //Convert that byte array into a very very long string
            JSONInput = new String(JSONBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Return the json input string
        return JSONInput;
    }
}

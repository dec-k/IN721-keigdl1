package bit.keigdl1.mosaic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    //Class variable for camera image that gets taken
    String mPhotoFileName = "";
    File mPhotoFile;
    Uri mPhotoFileURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ref bind
        Button btn = (Button)findViewById(R.id.btnTakePhoto);
        btn.setOnClickListener(new customClick());
    }

    private File makeTimeStampFile(){
        //System image folder
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //Make subdirectory
        File imageStorageDirectory = new File(imageRootPath, "MosaicSnaps");
        if(!imageStorageDirectory.exists()){
            imageStorageDirectory.mkdirs();
        }

        //Get timestamp
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        mPhotoFileName = "IMG_" + timeStamp + ".jpg";

        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + mPhotoFileName);
        return photoFile;
    }

    private class customClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //Make a timestamped, empty, photofile.
            mPhotoFile = makeTimeStampFile();

            //generate URI from that file
            mPhotoFileURI = Uri.fromFile(mPhotoFile);

            //Create an intent to launch the default camera app.
            Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //Attach uri to intent
            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoFileURI);

            //Launch intent
            startActivityForResult(imageCaptureIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                //Build a legit bitmap
                String realFilePath = mPhotoFile.getPath();
                Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilePath);

                populateIVs(userPhotoBitmap);
            }else{
                Toast.makeText(this, "Camera did not return a valid photo file.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void populateIVs(Bitmap bmp){
        //Ref imageviews
        List<ImageView> ivs = new ArrayList<ImageView>();

        ImageView iv1 = (ImageView)findViewById(R.id.iv1);
        ImageView iv2 = (ImageView)findViewById(R.id.iv2);
        ImageView iv3 = (ImageView)findViewById(R.id.iv3);
        ImageView iv4 = (ImageView)findViewById(R.id.iv4);

        ivs.add(iv1);
        ivs.add(iv2);
        ivs.add(iv3);
        ivs.add(iv4);

        iv1.setImageBitmap(bmp);
        iv2.setImageBitmap(bmp);
        iv3.setImageBitmap(bmp);
        iv4.setImageBitmap(bmp);
    }


}

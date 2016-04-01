package bit.keigdl1.aboutdunedin;

import android.graphics.drawable.Drawable;

/**
 * Created by keigdl1 on 1/04/2016.
 */
public class FunThing {
    //Things that a fun thing loves to know about
    //a real cute image
    Drawable ftImg;
    //adorable name of the fun thing
    String ftName;

    //ctor
    public FunThing(String ftName, Drawable ftImg){
        this.ftName = ftName;
        this.ftImg = ftImg;
    }
}

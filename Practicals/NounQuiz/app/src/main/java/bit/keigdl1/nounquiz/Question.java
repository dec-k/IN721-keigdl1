package bit.keigdl1.nounquiz;

import android.graphics.Bitmap;

/**
 * Created by Decura on 3/28/2016.
 */
public class Question {
    //Attributes of a question
    private String noun;
    private String answer;
    private Bitmap image;

    //Constructor
    public Question(String noun, String answer, Bitmap image){
        this.setNoun(noun);
        this.setAnswer(answer);
        this.setImage(image);
    }


    //Just a lot of accessors/mutators.
    public String getNoun() {
        return noun;
    }
    public void setNoun(String noun) {
        this.noun = noun;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }
}

package bit.keigdl1.nounquiz;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionAct extends AppCompatActivity {
    //Declare a list for the questions to be held in
    List questionList;
    //Declare a variable to store the score of the user so far
    int scoreTotal;
    //Variable for which question the quiz is currently on
    int currentlyOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Declare & instantiate a question list
        questionList = new ArrayList();

        //Initialise currentlyOn and score
        scoreTotal = 0;
        currentlyOn = 0;

        //Run genQuestions so the question list gets loaded and shuffled correctly
        genQuestions();

        //Load in a question at activity load
        loadNextQuestion();
    }

    public void genQuestions(){
        //Das - Neutral
        questionList.add(new Question("Auto", "Das", BitmapFactory.decodeFile(String.valueOf(R.drawable.das_auto))));
        questionList.add(new Question("Haus", "Das", BitmapFactory.decodeFile(String.valueOf(R.drawable.das_haus))));
        questionList.add(new Question("Schaf","Das",BitmapFactory.decodeFile(String.valueOf(R.drawable.das_schaf))));
        //Der - Masculine
        questionList.add(new Question("Apfel","Der",BitmapFactory.decodeFile(String.valueOf(R.drawable.der_apfel))));
        questionList.add(new Question("Baum","Der",BitmapFactory.decodeFile(String.valueOf(R.drawable.der_baum))));
        questionList.add(new Question("Stuhl","Der",BitmapFactory.decodeFile(String.valueOf(R.drawable.der_stuhl))));
        //Die - Feminine
        questionList.add(new Question("Ente","Die",BitmapFactory.decodeFile(String.valueOf(R.drawable.die_ente))));
        questionList.add(new Question("Hexe","Die",BitmapFactory.decodeFile(String.valueOf(R.drawable.die_hexe))));
        questionList.add(new Question("Kuh", "Die", BitmapFactory.decodeFile(String.valueOf(R.drawable.die_kuh))));
        questionList.add(new Question("Milch", "Die", BitmapFactory.decodeFile(String.valueOf(R.drawable.die_milch))));
        questionList.add(new Question("Strasse","Die",BitmapFactory.decodeFile(String.valueOf(R.drawable.die_strasse))));


        for(int i = 0; i < 100; i++){
            //Call the shuffle method 100 times to provide some semblance of a random order.
            shuffleList();
        }

        //The list should now be sufficiently shuffled.
    }

    public void shuffleList(){
        //Setup random numbers which align with index values in the question list.
        Random rndShuffle = new Random();
        int randA = rndShuffle.nextInt(12);
        int randB = rndShuffle.nextInt(12);

        //Prevent the two numbers being the same
        while(randA == randB){
            randB = rndShuffle.nextInt(12);
        }

        //Create a temp variable to hold question A
        Question temp = (Question) questionList.get(randA);

        //Put question b in question a's place
        questionList.set(randA, questionList.get(randB));

        //Add temp back at question B's location.
        questionList.set(randB, temp);
    }

    public void loadNextQuestion(){
        //Get references to the required form component
        ImageView iv = (ImageView)findViewById(R.id.imgQuestion);

        //Set question var for the currentlyOn question
        Question curOnQuestion = (Question) questionList.get(currentlyOn);

        //Use that questions image accessors to get a bitmap of its image and draw it to the iv
        iv.setImageBitmap(curOnQuestion.getImage());
    }
}

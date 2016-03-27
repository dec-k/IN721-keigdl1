package bit.keigdl1.nounquiz;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
    //Get resources as a var
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Init res
        res = getResources();

        //Declare & instantiate a question list
        questionList = new ArrayList();

        //Initialise currentlyOn and score
        scoreTotal = 0;
        currentlyOn = 0;

        //Run genQuestions so the question list gets loaded and shuffled correctly
        genQuestions();

        //Load in a question at activity load
        loadNextQuestion();

        //Setup choice button
        Button btnChoice = (Button)findViewById(R.id.btnConf);
        //bind
        btnChoice.setOnClickListener(new processBtnClick());
    }

    public class processBtnClick implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //Set question var for the currentlyOn question
            Question curOnQuestion = (Question) questionList.get(currentlyOn);

            //Get answer
            String curAnswer = curOnQuestion.getAnswer();

            //Ref rdobtns & grp
            RadioButton rdoDas = (RadioButton) findViewById(R.id.rdoDas);
            RadioButton rdoDie = (RadioButton) findViewById(R.id.rdoDie);
            RadioButton rdoDer = (RadioButton) findViewById(R.id.rdoDer);

            //Determine selected rdo
            if(rdoDas.isChecked()){
                //Determine if selected rdo is equal to answer
                if(rdoDas.getText() == curAnswer){
                    //// TODO: 3/28/2016 implement 'correct fragment'
                }else{
                    //// TODO: 3/28/2016 implement 'incorrect fragment'
                }
            }else if(rdoDie.isChecked()){
                if(rdoDie.getText() == curAnswer){
                    //// TODO: 3/28/2016 implement 'correct fragment'
                }else{
                    //// TODO: 3/28/2016 implement 'incorrect fragment'
                }
            }else if(rdoDer.isChecked()){
                if(rdoDer.getText() == curAnswer){
                    //// TODO: 3/28/2016 implement 'correct fragment'
                }else{
                    //// TODO: 3/28/2016 implement 'incorrect fragment'
                }
            }else{
                //Show anonymously created toast prompting the user to select an answer
                Toast.makeText(QuestionAct.this, "Please select an article.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void genQuestions(){
        //Das - Neutral
        questionList.add(new Question("Auto", "Das", res.getDrawable(R.drawable.das_auto)));
        questionList.add(new Question("Haus", "Das", res.getDrawable(R.drawable.das_haus)));
        questionList.add(new Question("Schaf","Das", res.getDrawable(R.drawable.das_schaf)));
        //Der - Masculine
        questionList.add(new Question("Apfel","Der",res.getDrawable(R.drawable.der_apfel)));
        questionList.add(new Question("Baum","Der",res.getDrawable(R.drawable.der_baum)));
        questionList.add(new Question("Stuhl","Der",res.getDrawable(R.drawable.der_stuhl)));
        //Die - Feminine
        questionList.add(new Question("Ente","Die",res.getDrawable(R.drawable.die_ente)));
        questionList.add(new Question("Hexe","Die",res.getDrawable(R.drawable.die_hexe)));
        questionList.add(new Question("Kuh", "Die", res.getDrawable(R.drawable.die_kuh)));
        questionList.add(new Question("Milch", "Die", res.getDrawable(R.drawable.die_milch)));
        questionList.add(new Question("Strasse","Die",res.getDrawable(R.drawable.die_strasse)));


        for(int i = 0; i < 100; i++){
            //Call the shuffle method 100 times to provide some semblance of a random order.
            shuffleList();
        }

        //The list should now be sufficiently shuffled.
    }

    public void shuffleList(){
        //Setup random numbers which align with index values in the question list.
        Random rndShuffle = new Random();
        int randA = rndShuffle.nextInt(11);
        int randB = rndShuffle.nextInt(11);

        //Prevent the two numbers being the same
        while(randA == randB){
            randB = rndShuffle.nextInt(11);
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
        iv.setImageDrawable(curOnQuestion.getImage());
    }
}

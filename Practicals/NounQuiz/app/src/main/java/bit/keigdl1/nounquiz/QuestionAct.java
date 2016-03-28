package bit.keigdl1.nounquiz;

import android.app.FragmentManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
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
    //Create fm & fragments
    // TODO: 3/28/2016 Tidy up naming conventions of fragments
    FragmentManager fm;
    DialogNext dialogNextPrompt;

    //Bundle to pass info to a fragment
    Bundle bundlePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Init res
        res = getResources();

        //Init fm & frags & bundle
        fm = getFragmentManager();
        dialogNextPrompt = new DialogNext();
        bundlePass = new Bundle();

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
                // TODO: 3/28/2016 Compare to a non-literal string. Can't use rdoDas.getText(), why?
                if("Das" == curAnswer){
                    //Call manageAnswer to handle fragment transitioning
                    manageAnswer(true);
                }else{
                    manageAnswer(false);
                }
            }else if(rdoDie.isChecked()){
                if("Die" == curAnswer){
                    manageAnswer(true);
                }else{
                    manageAnswer(false);
                }
            }else if(rdoDer.isChecked()){
                if("Der" == curAnswer){
                    manageAnswer(true);
                }else{
                    manageAnswer(false);
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

    public void DismissFragment(){
        //Dismiss screen fragment
        dialogNextPrompt.dismiss();

        //Increment the currently being used question
        currentlyOn++;

        //Load the next question to the activity
        loadNextQuestion();
    }

    public void manageAnswer(boolean answer){
        //Load up bundle with the correct type of string
        if(answer){
            bundlePass.putString("TypeOfFragment","Correct Answer!");
            //Increase score
            scoreTotal++;
        }else{
            bundlePass.putString("TypeOfFragment","Wrong Answer...");
        }

        //Set the arguments of the fragment to contain the bundle
        dialogNextPrompt.setArguments(bundlePass);

        //Give control to the fragment, showing it.
        dialogNextPrompt.show(fm,"Useless Tag");
    }
}

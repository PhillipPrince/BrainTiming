package com.example.braintimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int correctAnswer;
    int score=0;
    int numberOfQuestions=0;
    TextView resultTextView, scoreTextView, award, text;
    GridLayout grid;
    Button button0, button1, button2, button3, playAgain;
    Random rand;
    TextView timerText;


    int buttonWithAnswer=0;
    LinearLayout myGame, repeat;



    public void start(View v){

        goButton.setVisibility(v.INVISIBLE);
        myGame.setVisibility(View.VISIBLE);
        replay(findViewById(R.id.timer));
        timerText.setText("30 s");

    }
    public void newQuestion(){
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        correctAnswer=rand.nextInt(4);
        text.setText(Integer.toString(a)+"+"+Integer.toString(b));
        answers.clear();


        for(int i=0; i<=4; i++){
            if(i==correctAnswer){
                buttonWithAnswer=i;
                answers.add(a+b);
            }else {
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==a+b){
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         text=findViewById(R.id.sum);
         rand=new Random();
        goButton=findViewById(R.id.goButton);
         button0=findViewById(R.id.but1);
         button1=findViewById(R.id.but2);
         button2=findViewById(R.id.but3);
         button3=findViewById(R.id.but4);
        resultTextView=findViewById(R.id.result);
        scoreTextView=findViewById(R.id.score);
        timerText=findViewById(R.id.timer);
        myGame=findViewById(R.id.game);
        playAgain=findViewById(R.id.playAgain);
        grid=findViewById(R.id.gridLayout);
        repeat=findViewById(R.id.repeat);
        award=findViewById(R.id.award);

        myGame.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);


    }

    public void chooseAnswer(View view) {
         int j=Integer.parseInt(view.getTag().toString());
        if(buttonWithAnswer==j){
            resultTextView.setText("CORRECT");
           score++;

        }
        else {
            resultTextView.setText("WRONG");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }
    public void replay(View v) {
        score=0;
        numberOfQuestions=0;
        timerText.setText("30");
        myGame.setVisibility(View.VISIBLE);
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        repeat.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {
                repeat.setVisibility(View.VISIBLE);
                timerText.setText("0"+"s");
                myGame.setVisibility(View.INVISIBLE);
                if(score>=25){
                    award.setText("Excellent");
                }else if(score>=20 && score<25){
                    award.setText("Wonderful");
                }else if(score>=15 && score<20){
                    award.setText("Very Good");
                }else if(score>=10 && score<15){
                    award.setText("Good");
                }else if(score>=5 && score<10){
                    award.setText("Fair");
                }else if(score>=0 && score<5){
                    award.setText("Poor");
                }
            }
        }.start();
    }
}

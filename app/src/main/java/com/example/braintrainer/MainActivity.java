package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button gobutton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playagain;
    TextView results;
    TextView scoretext;
    TextView sum;
    TextView timer;
    ConstraintLayout gamelayout;
    int location;
    int score;
    int totalQuestions;
    ArrayList<Integer> answers=new ArrayList<>();

    public void playagainfn(View view){
        playagain.setVisibility(View.INVISIBLE);
        score=0;
        totalQuestions=0;
        scoretext.setText("0/0");
        results.setVisibility(View.INVISIBLE);
        startAgain();
        timer.setText("30s");

        new CountDownTimer(10100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                results.setText("Time Finished !");
                playagain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void go(View view){
        gobutton.setVisibility(View.INVISIBLE);
        gamelayout.setVisibility(View.VISIBLE);
        playagainfn(findViewById(R.id.timerTextView));
    }
    public void start(View view){
        results.setVisibility(View.VISIBLE);
        if(Integer.toString(location).equals(view.getTag().toString())){
        results.setText("Correct !");
        score++;
        }
        else {
            results.setText("Wrong :(");
        }
        totalQuestions++;
        scoretext.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestions));
        startAgain();
    }

    public void startAgain(){

        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sum.setText(Integer.toString(a)+"+"+Integer.toString(b));
        answers.clear();
        location=rand.nextInt(4);
        for(int i=0;i<4;i++){
            if (i==location){
                answers.add(a+b);
            }else{
                int c=40-(a+b);
                int d=a+b+1;
                int wrongAns=rand.nextInt(c)+d;
                answers.add(wrongAns);
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
        gobutton=findViewById(R.id.goButton);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        gamelayout=findViewById(R.id.gameLayout);
        playagain=findViewById(R.id.playAgainButton);
        results=findViewById(R.id.resultTextView2);
        timer=findViewById(R.id.timerTextView);
        sum=findViewById(R.id.textView);
        scoretext=findViewById(R.id.scoreTextView2);
        gamelayout.setVisibility(View.INVISIBLE);
        gobutton.setVisibility(View.VISIBLE);
        playagainfn(playagain);

    }
}

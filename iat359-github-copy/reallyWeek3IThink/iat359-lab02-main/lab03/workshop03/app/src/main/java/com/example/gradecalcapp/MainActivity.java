package com.example.gradecalcapp;
//https://github.com/Duder5000/iat359.git

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private final static String FINAL_MARK = "FinalMark";

    EditText assignmentsEditText, participationEditText, projectsEditText, quizzesEditText, examEditText, finalMarkText;
    SeekBar examSeekBar;
    Button resetButton;

    double assignmentsMark = 0;
    double participationMark = 0;
    double projectsMark = 0;
    double quizzesMark = 0;
    double examMark = 0;
    double finalMark = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignmentsEditText = (EditText) findViewById(R.id.assignmentsEditText);
        participationEditText = (EditText) findViewById(R.id.participationEditText);
        projectsEditText = (EditText) findViewById(R.id.projectsEditText);
        quizzesEditText = (EditText) findViewById(R.id.quizzesEditText);
        examEditText = (EditText) findViewById(R.id.examEditText);
        finalMarkText = (EditText) findViewById(R.id.finalMarkText);
        examSeekBar = (SeekBar) findViewById(R.id.examSeekBar);
        resetButton = findViewById(R.id.resetButton);

        assignmentsEditText.addTextChangedListener(this);
        participationEditText.addTextChangedListener(this);
        projectsEditText.addTextChangedListener(this);
        quizzesEditText.addTextChangedListener(this);
        examEditText.addTextChangedListener(this);
        resetButton.setOnClickListener(this);
        examSeekBar.setOnSeekBarChangeListener(this);

    }

    private void finalMarkCalc(){
        finalMark = (0.15*assignmentsMark) + (0.15*participationMark) + (0.2*projectsMark) + (0.2*quizzesMark) + (0.3*examMark);
        finalMarkText.setText(String.format("%.0f",finalMark));
    }

    private void resetAll(){
        assignmentsMark = 0;
        participationMark = 0;
        projectsMark = 0;
        quizzesMark = 0;
        examMark = 0;
        finalMark = 0;

        projectsEditText.setText(String.format("%.0f",projectsMark));
        assignmentsEditText.setText(String.format("%.0f",assignmentsMark));
        participationEditText.setText(String.format("%.0f",participationMark));
        quizzesEditText.setText(String.format("%.0f",quizzesMark));
        examEditText.setText(String.format("%.0f",examMark));

        examSeekBar.setProgress(0);
    }

//    private double checkMark(String text){
//        double val = 0;
//
//        try{
//            val = Double.parseDouble(text);
//            if(val > 100){
//                val = 100;
//                something.setText(String.format("%.0f",val));
//            }
//        }catch (Exception e){
//            val = 0;
//        }
//
//        return val;
//    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(projectsEditText.isFocused()){
           try{
               projectsMark = Double.parseDouble(projectsEditText.getText().toString());
               if(projectsMark > 100){
                   projectsMark = 100;
                   projectsEditText.setText(String.format("%.0f",projectsMark));
               }
           }catch (Exception e){
               projectsMark = 0;
           }
        }else if(assignmentsEditText.isFocused()) {
            try{
                assignmentsMark = Double.parseDouble(assignmentsEditText.getText().toString());
                if(assignmentsMark > 100){
                    assignmentsMark = 100;
                    assignmentsEditText.setText(String.format("%.0f",assignmentsMark));
                }
            }catch (Exception e){
                assignmentsMark = 0;
            }
        }else if(participationEditText.isFocused()) {
            try{
                participationMark = Double.parseDouble(participationEditText.getText().toString());
                if(participationMark > 100){
                    participationMark = 100;
                    participationEditText.setText(String.format("%.0f",participationMark));
                }
            }catch (Exception e){
                participationMark = 0;
            }
        }else if(quizzesEditText.isFocused()) {
            try{
                quizzesMark = Double.parseDouble(quizzesEditText.getText().toString());
                if(quizzesMark > 100){
                    quizzesMark = 100;
                    quizzesEditText.setText(String.format("%.0f",quizzesMark));
                }
            }catch (Exception e){
                quizzesMark = 0;
            }
        }else if(examEditText.isFocused()){
            //none
        }
        finalMarkCalc();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        examMark = seekBar.getProgress();
        examEditText.setText(String.format("%.0f",examMark));

        finalMarkCalc();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        outState.putDouble(FINAL_MARK,finalMark);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getDouble(FINAL_MARK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resetButton:
                Toast.makeText(getApplicationContext(),"resetButton",Toast.LENGTH_SHORT).show();
                resetAll();
        }
    }
}
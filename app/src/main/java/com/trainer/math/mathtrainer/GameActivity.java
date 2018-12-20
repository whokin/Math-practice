package com.trainer.math.mathtrainer;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView txtEquation;
    EditText etxtAnswer;
    Button btnCheck;
    Button btnShowAnswer;
    Button btnNextQuestion;
    RadioGroup radioGroup;
    RadioButton radLevel1;
    RadioButton radLevel2;
//    Button btn_level1;
//    Button btn_level2;

    Vibrator vibrator;

    int minBound;
    int maxBound;
    int randX;
    int randY;
    Equation equation;
    boolean answerFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Populate references for elements
        populateReference();

        // Sets default level difficulty to 1
        level1();

        // Display the question
        updateQuestion();

        // Listen for data entry in the edit text box
        etxtAnswer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        validateAnswer();

                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        });

        // Check the answer on click: green if right, red if wrong
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer();
            }
        });

        // Show the answer when clicked
        btnShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etxtAnswer.setText(equation.getStrResult());
                etxtAnswer.setBackgroundColor(Color.GREEN);
            }
        });

        // Update the question
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer();
                if (answerFound)
                    updateQuestion();
            }
        });

        radLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level1();
            }
        });

        radLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level2();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                switch (checkedId) {
                    case R.id.rad_level1:
                        if (isChecked){
                            minBound = 2;
                            maxBound = 12;
                        }
                        break;
                    case R.id.rad_level2:
                        if (isChecked){
                            minBound = 2;
                            maxBound = 100;
                        }
                        break;
                }
            }
        });
    }

    private void validateAnswer() {
        // Validates the answer compared to the input field
        equation.multiply();
        if (etxtAnswer.getText().toString().equals(equation.getStrResult())) {
            etxtAnswer.setBackgroundColor(Color.GREEN);
            answerFound = true;
        } else {
            etxtAnswer.setBackgroundColor(Color.RED);
            answerFound = false;
            if (vibrator != null && vibrator.hasVibrator()) {
                vibrator.vibrate(500);
            }
        }
    }

    private void questionDisplay() {
        // Displays the question on the text field
        String x = Integer.toString(equation.getX());
        String y = Integer.toString(equation.getY());
        String question = x + " * " + y + " = ?";
        txtEquation.setText(question);
    }

    private void populateReference() {
        // Generating the References for various elements
        txtEquation = findViewById(R.id.txt_question);
        etxtAnswer = findViewById(R.id.etxt_answer);
        btnCheck = findViewById(R.id.btn_check);
        btnShowAnswer = findViewById(R.id.btn_show_answer);
        btnNextQuestion = findViewById(R.id.btn_new);
        radioGroup = findViewById(R.id.radGroup);
        radLevel1 = findViewById(R.id.rad_level1);
        radLevel2 = findViewById(R.id.rad_level2);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    private void updateQuestion() {
        // Randomizes two new integers and updates the question text display
        randX = new Random().nextInt(maxBound - minBound) + minBound;
        randY = new Random().nextInt(maxBound - minBound) + minBound;
        equation = new Equation(randX, randY);
        questionDisplay();
        etxtAnswer.setText("");
        etxtAnswer.setBackgroundColor(0x00000000);
    }

    private void level1() {
        // sets the min and max bound to be 2 and 12 respectively
        // and colors the currently active level difficulty
        radLevel1.setBackgroundColor(Color.argb(1, 249, 166, 2));
        radLevel2.setBackgroundResource(android.R.drawable.btn_default);
        minBound = 2;
        maxBound = 12;
    }

    private void level2() {
        // sets the min and max bound to be 2 and 100 respectively
        // and colors the currently active level difficulty
        radLevel2.setBackgroundColor(Color.argb(1, 249, 166, 2));
        radLevel1.setBackgroundResource(android.R.drawable.btn_default);
        minBound = 2;
        maxBound = 100;
    }
}

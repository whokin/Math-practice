package com.trainer.math.mathtrainer;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView txtEquation;
    EditText etxtAnswer;
    Button btnCheck;
    Button btnShowAnswer;
    Button btnNextQuestion;

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
                String message = "The answer is " + equation.getStrResult();
                etxtAnswer.setText(equation.getStrResult());
                Toast.makeText(GameActivity.this, message, Toast.LENGTH_SHORT).show();
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
                else
                    Toast.makeText(GameActivity.this, "Answer not found yet.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateAnswer() {
        // Validates the answer compared to the input field
        equation.multiply();
        if (etxtAnswer.getText().toString().equals(equation.getStrResult())) {
            Toast.makeText(GameActivity.this,
                    "Correct!",
                    Toast.LENGTH_SHORT).show();
            etxtAnswer.setBackgroundColor(Color.GREEN);
            answerFound = true;
        } else {
            Toast.makeText(GameActivity.this,
                    "Try again!",
                    Toast.LENGTH_SHORT).show();
            etxtAnswer.setBackgroundColor(Color.RED);
            answerFound = false;
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
    }

    private void updateQuestion() {
        // Randomizes two new integers and updates the question text display
        randX = new Random().nextInt(12 - 2) + 2;
        randY = new Random().nextInt(12 - 2) + 2;
        equation = new Equation(randX, randY);
        questionDisplay();
        etxtAnswer.setText("");
        etxtAnswer.setBackgroundColor(0x00000000);
    }
}

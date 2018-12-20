package com.trainer.math.mathtrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    TextView txtEquation;
    EditText etxtAnswer;
    Button btnCheck;
    Button btnShowAnswer;

    // TODO: use random values
    Equation equation = new Equation(4, 5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Populate references for elements
        populateReference();

        questionDisplay();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation.multiply();
                final String answer = Integer.toString(equation.getResult());
                if (etxtAnswer.getText().toString().equals(answer)) {
                    Toast.makeText(GameActivity.this,
                            "Correct!",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(GameActivity.this,
                            "Try again!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void questionDisplay() {
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
    }


}

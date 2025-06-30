package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView displayTextView;  // For showing the ongoing expression
    private TextView resultTextView;   // For showing the result
    private String currentInput = "";  // Holds the current number
    private String expression = "";    // Holds the full expression (numbers + operators)
    private Double operand1 = null;   // First operand (before operator is pressed)
    private String operator = null;   // The operator (e.g., "+", "-", "×", "÷")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the TextViews
        displayTextView = findViewById(R.id.displayTextView);
        resultTextView = findViewById(R.id.resultTextView);

        // Find all the buttons and set OnClickListener
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonDecimal = findViewById(R.id.buttonDecimal);
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);

        // Set OnClickListener for all buttons
        buttonClear.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonDecimal.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        String buttonText = clickedButton.getText().toString();

        switch (buttonText) {
            case "C":
                clearCalculator();
                break;
            case "÷":
            case "×":
            case "-":
            case "+":
                handleOperator(buttonText);
                break;
            case "=":
                calculateResult();
                break;
            case ".":
                if (!currentInput.contains(".")) {
                    currentInput += ".";
                    displayTextView.setText(currentInput); // Update display with the decimal
                }
                break;
            default: // Number buttons
                currentInput += buttonText;
                expression += buttonText; // Append number to the full expression
                displayTextView.setText(expression); // Update display with the ongoing expression
                break;
        }
    }

    private void clearCalculator() {
        currentInput = "";
        expression = "";
        operand1 = null;
        operator = null;
        displayTextView.setText(R.string.display_hint); // Reset the expression display
        resultTextView.setText(""); // Clear the result display
    }

    private void handleOperator(String newOperator) {
        // If there's a number already entered, append the operator to the display
        if (!currentInput.isEmpty()) {
            if (operand1 == null) {
                operand1 = Double.parseDouble(currentInput);
                operator = newOperator;
                expression += " " + operator; // Append operator to expression
                displayTextView.setText(expression); // Show expression in displayTextView
                currentInput = ""; // Reset current input for the next number
            } else {
                // If there's already an operand1, calculate the result and set the new operator
                calculateResult(); // Perform the previous calculation
                operator = newOperator;
                expression = operand1 + " " + operator; // Reset expression to operand1 and new operator
                displayTextView.setText(expression); // Show new operator in the expression
            }
        }
    }

    private void calculateResult() {
        if (operand1 != null && operator != null && !currentInput.isEmpty()) {
            double operand2 = Double.parseDouble(currentInput);
            double result = 0;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "×":
                    result = operand1 * operand2;
                    break;
                case "÷":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        displayTextView.setText("Error");
                        resultTextView.setText(""); // Reset result text if error
                        return;
                    }
                    break;
            }

            // Display the result in the resultTextView only
            resultTextView.setText(String.valueOf(result)); // Show result below the expression

            // Reset values for further calculations
            operand1 = result;
            operator = null;
            currentInput = "";
            expression = ""; // Clear the full expression after calculating the result
            displayTextView.setText(""); // Clear the expression display after calculating the result
        }
    }
}

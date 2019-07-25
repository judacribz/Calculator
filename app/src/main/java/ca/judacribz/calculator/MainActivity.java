package ca.judacribz.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final private static char DIVIDE = '/';
    final private static char MULTIPLY = 'x';
    final private static char ADD = '+';
    final private static char SUBTRACT = '-';
    final private static char EQUALS = '=';

    TextView tvCalculations, tvAnswer;
    StringBuilder sb;

    boolean lastCharIsDigit = false;
    boolean containsOperator = false;

    char operator;
    double ans, num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCalculations = findViewById(R.id.tvCalculations);
        tvAnswer = findViewById(R.id.tvAnswer);
        sb = new StringBuilder();
    }


    public void appendToCalculation(String calcAppendStr) {
        sb.append(calcAppendStr);

        if (sb.toString().contains(String.valueOf(operator)) && lastCharIsDigit) {
            String[] nums = sb.toString().split(String.valueOf(operator));
            num1 = Double.valueOf(nums[0]);
            num2 = Double.valueOf(nums[1]);

            switch (operator) {
                case DIVIDE:
                    ans = num1/num2;
                    break;
                case MULTIPLY:
                    ans = num1*num2;
                    break;
                case ADD:
                    ans = num1+num2;
                    break;
                case SUBTRACT:
                    ans = num1-num2;
                    break;
            }

            tvAnswer.setText(String.valueOf(ans));
        }
        tvCalculations.setText(sb.toString());
    }

    public void deleteFromCalculation(View view) {
        if (!sb.toString().isEmpty()) {

            tvCalculations.setText(sb.toString());
        }
    }

    public void handleNumClick(View btnNum) {
        lastCharIsDigit = true;
        appendToCalculation(((Button) btnNum).getText().toString());
    }

    public void handleOpClick(View btnOperator) {
        if (!containsOperator) {

            if (!(sb.toString()).isEmpty() && lastCharIsDigit) {
                operator = ((Button) btnOperator).getText().toString().charAt(0);
                lastCharIsDigit = false;
                containsOperator = true;
                appendToCalculation(String.valueOf(operator));
            }
        }
    }
}


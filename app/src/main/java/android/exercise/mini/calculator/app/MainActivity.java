package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */

    TextView equals = (TextView) findViewById(R.id.buttonEquals);
    TextView plus = (TextView) findViewById(R.id.buttonPlus);
    TextView minus = (TextView) findViewById(R.id.buttonMinus);
    TextView clear = (TextView) findViewById(R.id.buttonClear);
    TextView zero = (TextView) findViewById(R.id.button0);
    TextView two = (TextView) findViewById(R.id.button2);
    TextView five = (TextView) findViewById(R.id.button5);
    TextView eight = (TextView) findViewById(R.id.button8);
    TextView one = (TextView) findViewById(R.id.button1);
    TextView four = (TextView) findViewById(R.id.button4);
    TextView seven = (TextView) findViewById(R.id.button7);
    View back = (View) findViewById(R.id.buttonBackSpace);
    TextView three = (TextView) findViewById(R.id.button3);
    TextView six = (TextView) findViewById(R.id.button6);
    TextView nine = (TextView) findViewById(R.id.button9);
    TextView output = (TextView) findViewById(R.id.textViewCalculatorOutput);
    View spaceBelow = (View) findViewById(R.id.spaceBelowButton1);

    output.setText(calculator.output());

    zero.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(0);
        output.setText(calculator.output());
      }
    });

    one.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(1);
        output.setText(calculator.output());
      }
    });

    two.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(2);
        output.setText(calculator.output());
      }
    });

    three.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(3);
        output.setText(calculator.output());
      }
    });

    four.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(4);
        output.setText(calculator.output());
      }
    });

    five.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(5);
        output.setText(calculator.output());
      }
    });

    six.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(6);
        output.setText(calculator.output());
      }
    });

    seven.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(7);
        output.setText(calculator.output());
      }
    });

    eight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(8);
        output.setText(calculator.output());
      }
    });

    nine.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(9);
        output.setText(calculator.output());
      }
    });

    equals.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertEquals();
        output.setText(calculator.output());
      }
    });

    plus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertPlus();
        output.setText(calculator.output());
      }
    });

    minus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertMinus();
        output.setText(calculator.output());
      }
    });

    clear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.clear();
        output.setText(calculator.output());
      }
    });
    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.deleteLast();
        output.setText(calculator.output());
      }
    });

    output.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.output();
        output.setText(calculator.output());
      }
    });
  }


  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    //outState = calculator.saveState();
    outState.putSerializable("state",calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    //calculator.loadState(savedInstanceState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
    Serializable prevState = savedInstanceState.getSerializable("state");
    calculator.loadState(prevState);
  }
}
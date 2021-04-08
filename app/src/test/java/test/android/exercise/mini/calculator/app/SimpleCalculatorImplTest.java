package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.deleteLast();
    String expected = "89";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.clear();
    String expected = "0";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    firstCalculator.insertDigit(5);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(7);

    // save current state
    Serializable savedState = firstCalculator.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    firstCalculator.clear();
    assertEquals("0", firstCalculator.output());

    // load the saved state and make sure state was loaded correctly
    secondCalculator.loadState(savedState);
    assertEquals("5+7", secondCalculator.output());
  }
  
  //  my Tests:
  @Test
  public void when_callingDeleteLastBetweenTwoNumbers_then_ShouldBeCreatedNewNumber(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(5);
    String expected = "5+17-125";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingEqualsThreeTimesWithOperations_then_ShouldCalcRightAndOutputOneDigit(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    String expected = "4";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_insertingAndClearingThenInsertingPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.clear();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator_bothWithInputs(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    firstCalculator.insertDigit(5);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(7);

    secondCalculator.insertDigit(5);
    secondCalculator.insertDigit(6);
    // save current state
    Serializable savedState = firstCalculator.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    firstCalculator.clear();
    assertEquals("0", firstCalculator.output());

    // load the saved state and make sure state was loaded correctly
    secondCalculator.loadState(savedState);
    assertEquals("5+7", secondCalculator.output());
  }

  @Test
  public void when_insertingBeforeAndAfterEqualing_then_outputShouldBeRight(){
    //given input "999-888-222=-333", expected output is "-111-333"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    for (int i=0; i<3; i++){
      calculatorUnderTest.insertDigit(9);
    }
    calculatorUnderTest.insertMinus();
    for (int i=0; i<3; i++){
      calculatorUnderTest.insertDigit(8);
    }
    calculatorUnderTest.insertMinus();
    for (int i=0; i<3; i++){
      calculatorUnderTest.insertDigit(2);
    }
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    for (int i=0; i<3; i++){
      calculatorUnderTest.insertDigit(3);
    }
    assertEquals("-111-333", calculatorUnderTest.output());
  }

  @Test
  public void when_InsertingDigitAndPushingPlusTwiceAndDeleting_then_outputIsTheInsertedDigit(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.deleteLast();
    assertEquals("9", calculatorUnderTest.output());
  }

  @Test
  public void when_insertingMinusAndPlus_then_outputIsTheInsertedDigit(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.deleteLast();
    assertEquals("9", calculatorUnderTest.output());
  }

  @Test
  public void when_PressingJustEqualsWithEmptyHistory_then_outputIs0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertEquals();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_subtractionIsNegative_then_outputIsNegativ(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    for (int i=0; i<3; i++){
      calculatorUnderTest.insertDigit(4);
    }
    calculatorUnderTest.insertMinus();
    for (int i=0; i<3; i++){
      calculatorUnderTest.insertDigit(6);
    }
    calculatorUnderTest.insertEquals();
    assertEquals("-222", calculatorUnderTest.output());
  }

  @Test
  public void when_NegativePlusHigherPositive_then_outputIsPositive(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    for (int i=0; i<3; i++){
      calculatorUnderTest.insertDigit(4);
    }
    calculatorUnderTest.insertMinus();
    for (int i=0; i<3; i++){
      calculatorUnderTest.insertDigit(6);
    }
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    for (int i=0; i<3; i++){
      calculatorUnderTest.insertDigit(3);
    }
    calculatorUnderTest.insertEquals();
    assertEquals("111", calculatorUnderTest.output());
  }
}
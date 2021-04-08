package android.exercise.mini.calculator.app;
import java.util.ArrayList;
import java.io.Serializable;

public class SimpleCalculatorImpl implements SimpleCalculator {

  protected ArrayList<String> history = new ArrayList<>();

  @Override
  public String output() {
    // todo: return output based on the current state
    if (history.size()==0){
        return "0";
    }
    String str = "";
    for (String item: history) {
      str = str.concat(item);
    }
    return str;
  }

  @Override
  public void insertDigit(int digit) {
    if (digit<0 ||  digit>9){
      throw new IllegalArgumentException();
    }
    else{
      if (history.size()==0){
        history.add(String.valueOf(digit));
        return;
      }
      String last_val = history.get(history.size()-1);
      if (!last_val.equals("+") && !last_val.equals("-")){
        history.set(history.size()-1,String.valueOf(Integer.parseInt(last_val)*10+digit));
      }
      else{
        history.add(String.valueOf(digit));
      }
    }
  }

  @Override
  public void insertPlus() {
    if (history.size()==0) {
      insertDigit(0);
      history.add("+");
      return;
    }
    if (history.get(history.size() - 1).equals("+") || history.get(history.size() - 1).equals("-")){
      return;
    }
    history.add("+");
  }

  @Override
  public void insertMinus() {
    if (history.size()==0) {
      insertDigit(0);
      history.add("-");
      return;
    }
    if (history.get(history.size() - 1).equals("+") || history.get(history.size() - 1).equals("-")){
      return;
    }
    history.add("-");
  }

  @Override
  public void insertEquals() {
    if (history.size()==0){
      return;
    }
    int sum= Integer.parseInt(history.get(0));
    int i=1;
    while(i< history.size()){
      if (history.get(i).equals("+")){
        sum+= Integer.parseInt(history.get(i+1));
        i+=2;
      }
      else if (history.get(i).equals("-")){
        sum-= Integer.parseInt(history.get(i+1));
        i+=2;
      }
    }
    history.clear();
    history.add(String.valueOf(sum));
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    if (history.size()>0) {
      String last_val = history.get(history.size()-1);
      if (last_val.equals("+") || last_val.equals("-")){
        history.remove(history.size()-1);
      }
      else{
        if (Integer.parseInt(last_val)/10 == 0){
          history.remove(history.size()-1);
        }
        else{
          history.set(history.size()-1,String.valueOf(Integer.parseInt(last_val)/10));
        }
      }
    }
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    history.clear();
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    // todo: insert all data to the state, so in the future we can load from this state
    state.history.addAll(history);
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
    history.clear();
    history.addAll(((CalculatorState) prevState).history);
  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    protected ArrayList<String> history = new ArrayList<>();
  }
}

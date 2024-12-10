package edu.grinnell.csc207.util;

/**
 *
 * A set of methods needed for calculations shared between quck and interactive
 * calculator.
 *
 * @author A.J. Trimble
 *
 */
public class ExtraUtils {

  /**
   *
   * The length of a properly formatted call to store.
   *
   */
  private static final int VALID_STORE_SIZE = 7;

  /**
   *
   * Stores the value in the register.
   *
   * @param index    the l;etter whose under which we want to store data.
   * @param calc     the calculator where the value to be stored is.
   * @param register a register where the data will be stored.
   *
   */
  public void storeHandler(char index, BFCalculator calc, BFRegisterSet register) {
    register.store(index, calc.get());
  } // end method

  /**
   *
   * Determines if a given string would be a valid call to store.
   *
   * @param input the string that will be checked
   * @return boolean that is true if it is would be valid call
   *
   */
  public static boolean isStore(String input) {
    if (input.length() == VALID_STORE_SIZE) {
      if (input.substring(0, "STORE".length()).equals("STORE")) {
        if (Character.isLowerCase(input.charAt(("STORE".length() + 1)))) {
          return true;
        } // end if
      } // end if
    } // end if
    return false;
  } // end method

  /**
   *
   * Determines if the given string is a opperation (+, -, *, or /).
   *
   * @param input the string that will be checked
   * @return boolean that is true if it is a opperation
   *
   */
  public static boolean isOperation(String input) {
    char firstChar = input.charAt(0);
    return (input.length() == 1 && ((firstChar == '+' || firstChar == '-' || firstChar == '*' || firstChar == '/')));
  } // end method

  /**
   *
   * Returns the BigFraction stored under the register.
   *
   * @param input    the register
   * @param register The register (the data strucure holding the values)
   * @return BigFraction the value under the the given register.
   *
   */
  public static BigFraction getBigFraction(String input, BFRegisterSet register) {
    if (input.length() == 1 && Character.isLowerCase(input.charAt(0))) {
      return register.get(input.charAt(0));
    } else {
      return new BigFraction(input);
    } // else
  } // end method

  /**
   *
   * Returns true if the given string is a number repersented as a whole
   * number or a fraction. If it is a lowercase letter, it will return true if
   * it is a valid register
   *
   * @param input    the string to be checked
   * @param register The register (to be checked if input is a lowercase
   *                 letter)
   * @return Boolean the true or false if it is a number
   *
   */
  public static boolean isNumber(String input, BFRegisterSet register) {
    if (input.length() == 1 && Character.isLowerCase(input.charAt(0))) {
      return register.get(input.charAt(0)) != null;
    } // if
    boolean hasSlash = false;
    if (input.isEmpty() || (input.charAt(0) != '-' && !Character.isDigit(input.charAt(0)))) {
      return false;
    } // if
    for (int i = 1; i < input.length(); i++) {
      if (input.charAt(i) == '/') {
        if (hasSlash) {
          return false;
        } else {
          hasSlash = true;
        } // ifelse
      } else {
        if (!Character.isDigit(input.charAt(i))) {
          return false;
        } // if
      } // else
    } // for
    return true;
  } // end method

  /**
   *
   * Returns the register given in a properly formatted user call to store.
   *
   * @param input the string to be checked
   * @return char the valid register if found
   *
   */
  public static char registerChar(String input) {
    if (input.length() >= "STORE".length()) {
      if (input.substring(0, "STORE".length()).equals("STORE")) {
        if (Character.isLowerCase(input.charAt(("STORE".length() + 1)))) {
          return input.charAt(("STORE".length() + 1));
        } // end if
      } // end if
    } // end if
    throw new IllegalArgumentException("Invaild register");
  } // end method

  /**
   *
   * Runs calculations based on the input it is given. It will mutate the
   * calcs it is given which is how this data can be acsessed. It will return
   * true if all the commands given are valid, and false if not.
   *
   * @param formula  the string from which the opperations drawn will be done.
   * @param calcs    an array of calculators which will handle and store
   *                 everything related to one set of calculations.
   * @param register the register in which values can be stored and gotten.
   * @param i        The current calculator being used.
   * @param operation     the opperation being run. defaults to 'a'
   * @return boolean wether or not the given data was a valid call which got
   *         run.
   *
   */
  public static boolean calcRunner(String formula, BFCalculator[] calcs, BFRegisterSet register, int i,
      char operation) {
    String[] inputs = formula.split(" ");
    boolean numNext = false;
    boolean invalidInput = false;
    if (isStore(formula)) {
      register.store(registerChar(formula), calcs[(i + 1) % 2].get());
      calcs[i % 2].add(calcs[(i + 1) % 2].get());
    } else {
      for (int x = 0; x < inputs.length; x++) {
        if (x == 0) {
          if (isNumber(inputs[x], register)) {
            calcs[i % 2].add(getBigFraction(inputs[x], register));
          } else {
            invalidInput = true;
            break;
          } // ifelse
        } else if (!numNext && isOperation(inputs[x])) {
          operation = inputs[x].charAt(0);
          numNext = true;
        } else if (numNext && isNumber(inputs[x], register) && operation != 'a') {
          if (operation == '+') {
            calcs[i % 2].add(getBigFraction(inputs[x], register));
          } else if (operation == '-') {
            calcs[i % 2].subtract(getBigFraction(inputs[x], register));
          } else if (operation == '*') {
            calcs[i % 2].multiply(getBigFraction(inputs[x], register));
          } else if (operation == '/') {
            calcs[i % 2].divide(getBigFraction(inputs[x], register));
          } // if else
          operation = 'a';
          numNext = false;
        } else {
          invalidInput = true;
          break;
        } // else
      } // or
    } // else
    return (invalidInput || numNext);
  } // calc runner

} // end class

package edu.grinnell.csc207.util;

/**
 *
 * Contains methods for calling math function on the default value which it
 * hold.
 * Returns that value when propmted. Lets the code calculate and hold said
 * calcuation.
 *
 * @author A.J. Trimble
 *
 */
public class BFCalculator {

  /**
   * Sets defaultValue to 0/0.
   *
   */
  private BigFraction defaultValue = new BigFraction(0, 0);

  /**
   * Returns the current value in defaultValue.
   *
   * @return defaultValue.
   *
   */
  public BigFraction get() {
    return defaultValue;
  } // get()

  /**
   * Adds the given value to defaultValue.
   *
   * @param val The value that the user wants to add to defaultValue.
   */
  public void add(BigFraction val) {
    defaultValue = defaultValue.add(val);
    defaultValue = defaultValue.fractional();
  } // end add

  /**
   * Subtracts the given value to defaultValue.
   *
   * @param val The value that the user wants to subtract from defaultValue.
   */
  public void subtract(BigFraction val) {
    defaultValue = defaultValue.subtract(val);
    defaultValue = defaultValue.fractional();
  } // end subtract

  /**
   * Multiplies the given value by defaultValue.
   *
   * @param val The value that the user wants to multiplie by defaultValue.
   */
  public void multiply(BigFraction val) {
    defaultValue = defaultValue.multiply(val);
    defaultValue = defaultValue.fractional();
  } // end multipy

  /**
   * Divides defaultValue by the given value.
   *
   * @param val The value that the user wants to divide defaultValue by.
   */
  public void divide(BigFraction val) {
    defaultValue = defaultValue.divide(val);
    defaultValue = defaultValue.fractional();
  } // end divide

  /**
   * Resets defaultValue to 0/0.
   *
   */
  public void clear() {
    defaultValue = new BigFraction(0, 0);
  } // end clear
} // end class

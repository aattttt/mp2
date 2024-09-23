package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Samuel A. Rebelsky
 * @author Jenifer Silva
 * @author A.J. Trimble
 * 
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+

  /*
   * (1) Denominators are always positive. Therefore, negative fractions
   * are represented with a negative numerator. Similarly, if a fraction
   * has a negative numerator, it is negative.
   *
   * (2) Fractions are not necessarily stored in simplified form. To
   * obtain a fraction in simplified form, one must call the `simplify`
   * method.
   */

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /** The default numerator when creating fractions. */
  private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(2);

  /** The default denominator when creating fractions. */
  private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(7);

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * Warning! Not yet implemented.
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    boolean fractionalNumber = false;
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '/' && str.charAt(i) != '-') {
        System.err.println("Error: Invalid input.");
        return;
      } // end if
      if (str.charAt(i) == '/') {
        fractionalNumber = true;
      } // end if
    } // end forloop
    if (fractionalNumber){
      String[] values = str.split("/");
      if (Integer.valueOf(values[1]) == 0){
        System.err.println("Error: Division by 0");
        return;
      }
      this.num = BigInteger.valueOf(Integer.valueOf(values[0]));
      this.denom = BigInteger.valueOf(Integer.valueOf(values[1]));
    } else {
      this.num = BigInteger.valueOf(Integer.valueOf(str));
      this.denom = BigInteger.valueOf(1); 
    } 
  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    if (this.denom.intValue() == 0){
      return new BigFraction(addend.num, addend.denom);
    }
    resultDenominator = this.denom.multiply(addend.denom);
    // The numerator is more complicated
    resultNumerator = (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)


    /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction subtract(BigFraction subtracted) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    if (this.denom.intValue() == 0){
      return new BigFraction(subtracted.num, subtracted.denom);
    }
    // The denominator of the result is the product of this object's
    // denominator and subtract's denominator
    resultDenominator = this.denom.multiply(subtracted.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(subtracted.denom)).subtract(subtracted.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero
    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()

  //Multiply 2 fractions
  public BigFraction multiply(BigFraction other) {
    BigInteger top = this.num.multiply(other.num);
    BigInteger bottom = this.denom.multiply(other.denom);
    return new BigFraction(top, bottom);
}

public BigFraction divide(BigFraction other) {
  BigInteger top = this.num.multiply(other.denom);
  BigInteger bottom = this.denom.multiply(other.num);
  return new BigFraction(top, bottom);
}

public BigFraction fractional() {
  BigInteger GCD = this.num.gcd(this.denom);
  if (GCD.equals(BigInteger.valueOf(0))){
    return this;
  } else {
    return new BigFraction(this.num.divide(GCD), this.denom.divide(GCD));
  }
}
} // class BigFraction
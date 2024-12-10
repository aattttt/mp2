package edu.grinnell.csc207.util;

/**
 *
 * Handles the register which stores values specified by the user.
 *
 * @author A.J. Trimble
 *
 */
public class BFRegisterSet {

  /**
   *
   * The length of a the alphabet.
   *
   */
  private static final int ALPHABET_LENGTH = 26;

  /**
   * Initializes the register.
   *
   */
  private BigFraction[] arrRegister;

  /**
   * Creates a register which is repersented as an array of 26 BigFractions.
   *
   */
  public BFRegisterSet() {
    arrRegister = new BigFraction[ALPHABET_LENGTH];
  } // end method

  /**
   * Stores the given value in the current register.
   *
   * @param register The letter which data will be stored under.
   * @param val      The value that the user wants store.
   */
  public void store(char register, BigFraction val) {
    int index = ((int) register) - 'a';
    arrRegister[index] = val;
  } // end method

  /**
   *
   * Returns the value under the given register.
   * Returns null if nothing was under the register.
   *
   * @param register the letter the user wants value from
   * @return BigFraction The value that was stored under the register.
   *
   */
  public BigFraction get(char register) {
    int index = ((int) register) - 'a';
    return arrRegister[index];
  } // end method
} // class BigFraction

package edu.grinnell.csc207.util;

/**
* 
* Handles the register which stores values specified by the user.
* @author A.J. Trimble
*
*/
public class BFRegisterSet {
    BigFraction[] arrRegister;

/**
* Creates a register which is repersented as an array of 26 BigFractions.
*
*/
    public BFRegisterSet() {
        arrRegister = new BigFraction[26]; 
    } // end method

/**
* Stores the given value in the current register.
*
* @param  register  The letter which data will be stored under.
* @param  val  The value that the user wants store.
*/
    public void store(char register, BigFraction val) {
        int index = ((int) register) - 97;
        arrRegister[index] = val;
    } // end method

/**
* 
* Returns the value under the given register.
* Returns null if nothing was under the register.
*
* @param  register the letter the user wants value from
* @return BigFraction The value that was stored under the register.
* 
*/
    public BigFraction get(char register){
        int index = ((int) register) - 97;
        return arrRegister[index];
    } // end method
} // class BigFraction
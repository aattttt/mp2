package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
* 
* A set of methods needed for calculations shared between quck and interactive calculator.
* @author A.J. Trimble
*
*/
public class ExtraUtils {

/**
* 
* If a valid expression is given, it adds its first number to the calculator, 
* then runs the next number on it with the given opperations.
*
* @param  str the given expression.
* @param  calc a calcuator which will hold the result of the expression.
* @param  register a register where values can be fetched from.
*
*/
  public void argsUnderstander(String str, BFCalculator calc, BFRegisterSet register) {
    String[] inputs = str.split(" ");
    for (int i = 0; i < inputs.length; i++) {
        if (i == 0) {
            if (Character.isDigit(inputs[i].charAt(0)) || inputs[i].charAt(0) == '-') {
                BigFraction fraction = new BigFraction(inputs[i]);
                calc.add(fraction);
            } else if (Character.isLowerCase(inputs[i].charAt(0))) {
                calc.add(register.get(inputs[i].charAt(0)));
            } // end else if
        }  else if (inputs[i].charAt(0) == '+') {
            calc.add(getNextFraction(inputs, i, register));
        } else if (inputs[i].charAt(0) == '-') {
            calc.subtract(getNextFraction(inputs, i, register));
        } else if (inputs[i].charAt(0) == '*') {
            calc.multiply(getNextFraction(inputs, i, register));
        } else if (inputs[i].charAt(0) == '/') {
            calc.divide(getNextFraction(inputs, i, register));
        } // end else if
    }// end for loop
  } // end agrsUnderstander

/**
* 
* Fetches the next fraction seen in the expression, regardless of wether it is a BigFraction or a register.
*
* @param  inputs[] the given expression.
* @param  i the current location in the expression.
* @param  register a register where values can be fetched from.
* @return BigFraction the next value in the expression.
*
*/
  public BigFraction getNextFraction(String inputs[], int i, BFRegisterSet register) {
    if (Character.isLowerCase(inputs[i + 1].charAt(0))){
        return register.get(inputs[i + 1].charAt(0));
    } else if (Character.isDigit(inputs[i + 1].charAt(0))) {
        return new BigFraction(inputs[i + 1]);
    } else {
        System.err.println("Error: Invalid input.4");
        return new BigFraction("0/0");
    } // end if else
   } // end method

/**
* 
* Stores the value in the register
*
* @param  index the l;etter whose under which we want to store data.
* @param  calc the calculator where the value to be stored is.
* @param  register a register where the data will be stored.
*
*/
    public void storeHandler(char index, BFCalculator calc, BFRegisterSet register) {
        register.store(index, calc.get());
    } // end method

    public String wholeNumber(BigFraction mixed) {
        if (mixed.fractional().denominator().equals(BigInteger.valueOf(1))){
            return mixed.fractional().num.toString();
        } else {
            return mixed.fractional().toString();
        } // end if else
    } // end method
} // end class
package edu.grinnell.csc207;

public class mp {
    
}
/*
package edu.grinnell.csc207.util;

public class ExtraUtils {
  public void argsUnderstander(String str, BFCalculator calc, BFRegisterSet register) {
    String[] inputs = str.split(" ");
    
    for (int i = 0; i < inputs.length; i++) {
        if (Character.isDigit(inputs[i].charAt(0)) || inputs[i].charAt(0) == '-') {
            BigFraction fraction = new BigFraction(inputs[i]);
            calc.add(fraction);
        } else if (inputs[i].length() > 3) {
            if (inputs[i].substring(0,5).equals("STORE")) {
                if (i + 1 < inputs.length) {
                    if (Character.isLowerCase(inputs[i + 1].charAt(0))) {
                        register.store(inputs[i + 1].charAt(0), calc.get());
                    } else {
                        System.err.println("Error: Invalid input.1");
                    }
                } else {
                    System.err.println("Error: Invalid input.2");
                } // end else
            } else {
                System.err.println("Error: Invalid input.3" + inputs[i].substring(0,4));
            }
        } // end of if
        if (inputs[i].equals('+')) {
            calc.add(getNextFraction(inputs, i, register));
        }
        if (inputs[i].equals('-')) {
            calc.subtract(getNextFraction(inputs, i, register));
        }
    }// end for loop
    return;
  } // end agrsUnderstander


  public BigFraction getNextFraction(String inputs[], int i, BFRegisterSet register) {
    if (Character.isLowerCase(inputs[i].charAt(0))){
        return register.get(inputs[i].charAt(0));
    } else if (Character.isDigit(inputs[i].charAt(0))) {
        return new BigFraction(inputs[i + 1]);
    } else {
        System.err.println("Error: Invalid input.4");
        return new BigFraction("0/0");
    }
  }
}

 */
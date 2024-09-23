package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.ExtraUtils;

/**
 * Takes input from the command line and runs calculations based on it.
 *
 *
 * @param args
 *   Strings from the command line.
 * 
 * @author A.J. Trimble
 */
public class QuickCalculator {
/**
 * Takes input from the command line and runs calculations based on it.
 * Prints results to the terminal.
 *
 * @param args
 *   Strings from the command line.
 */
    public static void main(String[] args) {
        ExtraUtils utils = new ExtraUtils();
        BFRegisterSet register = new BFRegisterSet();
        BFCalculator[] calcs = new BFCalculator[args.length];  
        for (int i = 0; i < args.length; i++) {
            utils.argsUnderstander(args[i], calcs[i], register);
            if (args[i].length() > 4 ) {
                if (args[i].substring(0,5).equals("STORE")){
                    if (Character.isLowerCase(args[i].charAt(6))) {
                        utils.storeHandler(args[i].charAt(6), calcs[i-1], register);
                        System.out.println(args[i] + " -> STORED");
                        continue;
                    } // end if
                } // end if
            } // end if
            if (Character.isLowerCase(args[i].charAt(0)) && args[i].length() == 1) {
                System.out.println(args[i] + " -> " + register.get(args[i].charAt(0)));
            } else {
                System.out.println(args[i] + " -> " + utils.wholeNumber(calcs[i].get()));
            } // end else
        } // end for
    } // main method
} // end class

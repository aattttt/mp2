package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import static edu.grinnell.csc207.util.ExtraUtils.calcRunner;
import static edu.grinnell.csc207.util.ExtraUtils.isStore;

/**
 * Takes input from the command line and runs calculations based on it.
 *
 *
 * @author A.J. Trimble
 */
public class QuickCalculator {

  /**
   * Takes input from the command line and runs calculations based on it.
   * Prints results to the terminal.
   *
   * @param args Strings from the command line.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    BFRegisterSet register = new BFRegisterSet();
    BFCalculator[] calcs = new BFCalculator[2];
    calcs[0] = new BFCalculator();
    calcs[1] = new BFCalculator();
    if (args.length == 0) {
      pen.println("FAILED [Invalid expression]");
    } else { 
      for (int i = 0; i < args.length; i++) {
        char operation = 'a';
        boolean invalidInput = calcRunner(args[i], calcs, register, i, operation);
        if (invalidInput) {
          pen.println(args[i] + " -> FAILED [Invalid expression]");
        } else {
          if (isStore(args[i])) {
            pen.println(args[i] + " -> STORED");
          } else {
            pen.println(args[i] + " -> " + (calcs[i % 2].get()).toString());
          } // if else
        } // else
        calcs[(i + 1) % 2].clear();
      } // main method
    } // main method
  } // end else
} // class

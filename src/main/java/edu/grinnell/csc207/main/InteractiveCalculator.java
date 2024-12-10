package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import static edu.grinnell.csc207.util.ExtraUtils.calcRunner;
import static edu.grinnell.csc207.util.ExtraUtils.isStore;

/**
 * Takes input from the user with scanner. Prints calculations based on input to
 * the terminal. QUIT to end loop.
 *
 * @author A.J. Trimble
 *
 */
public class InteractiveCalculator {

  /**
   * Takes input from the user with scanner. Prints calculations based on
   * input to the terminal. QUIT to end loop.
   *
   * @param args Strings from the command line.
   *
   */
  public static void main(String[] args) {
    BFRegisterSet register = new BFRegisterSet();
    BFCalculator[] calcs = new BFCalculator[2];
    calcs[0] = new BFCalculator();
    calcs[1] = new BFCalculator();
    PrintWriter pen = new PrintWriter(System.out, true);
    for (int i = 1;; i++) {
      Scanner eyes = new Scanner(System.in);
      pen.flush();
      String stuff = eyes.nextLine();
      if (stuff.equals("QUIT")) {
        return;
      } // end if
      char operation = 'a';
      boolean invalidInput = calcRunner(stuff, calcs, register, i, operation);
      if (invalidInput) {
        pen.printf("*** ERROR [Invalid expression] ***");
      } else {
        if (isStore(stuff)) {
          pen.printf("STORED");
        } else {
          pen.printf((calcs[i % 2].get()).toString());
        } // if else
      } // else
      calcs[(i + 1) % 2].clear();
    } // for
  } // main method
} // end class

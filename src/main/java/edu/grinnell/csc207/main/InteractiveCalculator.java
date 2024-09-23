package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.ExtraUtils;

/**
 * Takes input from the user with scanner.
 * Prints calculations based on input to the terminal.
 * QUIT to end loop.
 * @author A.J. Trimble
 *
 */
public class InteractiveCalculator {
/**
 * Takes input from the user with scanner.
 * Prints calculations based on input to the terminal.
 * QUIT to end loop.
 *
 */
    public static void main(String[] args) {
        ExtraUtils utils = new ExtraUtils();
        BFRegisterSet register = new BFRegisterSet();
        BFCalculator[] calcs = new BFCalculator[2];
        calcs[0] = new BFCalculator();
        calcs[1] = new BFCalculator();
        PrintWriter pen = new PrintWriter(System.out, true);
        for (int i = 1;; i++) {
            Scanner eyes = new Scanner(System.in);
            pen.flush();
            String stuff = eyes.nextLine();
            if (stuff.equals("QUIT")){
                return;
            } // end if
            if (stuff.length() > 4 ) {
                if (stuff.substring(0,5).equals("STORE")){
                    if (Character.isLowerCase(stuff.charAt(6))) {
                        utils.storeHandler(stuff.charAt(6), calcs[(i-1)%2], register);
                        System.out.println(stuff + " -> STORED");
                        i--;
                        continue;
                    } // end if
                } // end if
            } // end if
            for (int x = 0; x < stuff.length(); x++) {
                if (Character.isLowerCase(stuff.charAt(x))) {
                    if (register.get(stuff.charAt(x)) == null){
                        System.out.println("*** ERROR [Invalid expression: unstored register called] ***");
                        stuff = "kick out";
                        break;
                    } // end if
                } // end if
                if (!Character.isDigit(stuff.charAt(x)) && !Character.isLowerCase(stuff.charAt(x)) && stuff.charAt(x) != '+' && stuff.charAt(x) != '-' && stuff.charAt(x) != '/' && stuff.charAt(x) != '*' && stuff.charAt(x) != ' ') {
                    System.out.println("*** ERROR [Invalid expression] ***");
                    stuff = "kick out";
                    break;
                } // end if
            } // end for
            if (stuff.equals("kick out")){
                continue;
            } // end if
            calcs[i%2].clear();
            utils.argsUnderstander(stuff, calcs[i%2], register);
            if (Character.isLowerCase(stuff.charAt(0)) && stuff.length() == 1) {
                System.out.println(register.get(stuff.charAt(0)));
            } else {
                System.out.println(utils.wholeNumber(calcs[i%2].get()));  
            } // end if else
            pen.flush();
        } // end for
    } // main method
} // end class

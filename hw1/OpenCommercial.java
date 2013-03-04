/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package cs61b;

/* OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();

    /* Replace this comment with your solution.  */
    URL myURL = new URL("http://www."+inputLine+".com/");
   
    BufferedReader URLreader = new BufferedReader(new InputStreamReader(myURL.openStream()));
    String line1, line2, line3, line4, line5;
    line1 = URLreader.readLine();
    line2 = URLreader.readLine();
    line3 = URLreader.readLine();
    line4 = URLreader.readLine();
    line5 = URLreader.readLine();
    System.out.println(line5);
    System.out.println(line4);
    System.out.println(line3);
    System.out.println(line2);
    System.out.println(line1);
  }
}


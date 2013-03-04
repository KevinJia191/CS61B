/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package cs61b;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author SuperUser
 */
public class Nuke2 {

    public static void main(String[] arg) throws Exception {
        BufferedReader keyboard;
        String inputLine;

        keyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.flush();
        inputLine = keyboard.readLine();

        System.out.println(inputLine.substring(0,1)+inputLine.substring(2,inputLine.length()));
    }
}

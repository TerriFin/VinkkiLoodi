/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.io;

/**
 *
 * @author Niko
 */
public interface IO {

    String nextLine();

    void printLine(String text);
    
    void printLine(String text, ColorPlacer colorPlacer);

    int nextInt();
}

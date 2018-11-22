/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.io;

import java.util.Scanner;

/**
 *
 * @author Niko
 */
public class KomentoriviIO implements IO {

    private Scanner scanner;

    public KomentoriviIO(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void printLine(String text) {
        System.out.println(text);
    }

    @Override
    public int nextInt() {
        return Integer.parseInt(scanner.nextLine());
    }

}

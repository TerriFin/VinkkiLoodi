/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.io;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niko
 */
public class StubIO implements IO {

    private List<String> input;
    private int i;
    private List<String> output;

    public StubIO(List<String> input) {
        this.input = input;
        this.output = new ArrayList<>();
    }

    @Override
    public String nextLine() {
        return input.get(i++);
    }

    @Override
    public void print(String text) {
        output.add(text);
    }
    
    @Override
    public void printLine(String text) {
        output.add(text);
    }

    public List<String> getOutput() {
        return output;
    }

    @Override
    public int nextInt() {
        return Integer.parseInt(input.get(i++));
    }
}

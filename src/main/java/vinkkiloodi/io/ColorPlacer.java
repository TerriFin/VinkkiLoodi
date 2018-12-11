/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.io;

/**
 *
 * @author samisaukkonen
 */
public class ColorPlacer {

    private ColorFlag[] flags;

    public ColorPlacer(ColorFlag... flags) {
        this.flags = flags;
    }

    public String colorize(String input) {
        String[] spacedInput = input.split(" ");

        for (ColorFlag flag : flags) {
            if (flag.sanaJokaVarjataan < spacedInput.length) {
                spacedInput[flag.sanaJokaVarjataan] = addColor(spacedInput[flag.sanaJokaVarjataan], flag.vari);
            }
        }
        
        return assembleStringFromArray(spacedInput);
    }

    private String addColor(String text, String color) {
        color = color.toLowerCase().trim();

        if (color.equals("red")) {
            return TextColors.red + text + TextColors.stopColor;
        } else if (color.equals("green")) {
            return TextColors.green + text + TextColors.stopColor;
        } else if (color.equals("yellow")) {
            return TextColors.yellow + text + TextColors.stopColor;
        } else if (color.equals("blue")) {
            return TextColors.blue + text + TextColors.stopColor;
        } else if (color.equals("purple")) {
            return TextColors.purple + text + TextColors.stopColor;
        } else if (color.equals("cyan")) {
            return TextColors.cyan + text + TextColors.stopColor;
        } else if (color.equals("white")) {
            return TextColors.white + text + TextColors.stopColor;
        }

        return text;
    }
    
    private String assembleStringFromArray(String[] input) {
        String toReturn = "";
        
        for (int i = 0; i < input.length; i++) {
            if (i == input.length - 1) {
                toReturn += input[i];
            } else {
                toReturn += input[i] + " ";
            }
        }
        
        return toReturn;
    }
}

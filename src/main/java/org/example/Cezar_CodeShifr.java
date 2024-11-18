package org.example;

import java.io.*;
import java.util.Scanner;

public class Cezar_CodeShifr {
    private String text = "";
    private int literal_out = 0;
    private final char[] alfaber;

    public Cezar_CodeShifr(String text, int literal_out) {
        this.setText(text);
        this.setLiteral_out(literal_out);
        alfaber = createCharArray(" абвгґдеєжзиіїйклмнопрстуфхцчшщьюя");
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setLiteral_out(int literal_out) {
        this.literal_out = literal_out;
    }

    public int getLiteral_out() {
        return this.literal_out;
    }

    public String Chezar_shifrator(String text, int literal_out) {
        char[] char_text = text.toCharArray();
        int[] Num_text = numericalofLetters(text.toLowerCase(), alfaber);
        StringBuilder Shifr = new StringBuilder();
        for (int i = 0; i < Num_text.length; i++) {
            int newIndex = (Num_text[i] + literal_out) % alfaber.length;
            if (newIndex < 0) newIndex += alfaber.length;
            if(Character.isUpperCase(char_text[i])){
                Shifr.append(Character.toUpperCase(alfaber[newIndex]));
            }
            else {
                Shifr.append(alfaber[newIndex]);
            }
        }
        return Shifr.toString();
    }

    public String Chezar_deshifrator(String text, int literal_out) {
        char[] char_text = text.toCharArray();
        int[] Num_text = numericalofLetters(text.toLowerCase(), alfaber);
        StringBuilder Shifr = new StringBuilder();
        for (int i = 0; i < Num_text.length; i++) {
            int newIndex = (Num_text[i] - literal_out) % alfaber.length;
            if (newIndex < 0) newIndex += alfaber.length;
            if(Character.isUpperCase(char_text[i])){
                Shifr.append(Character.toUpperCase(alfaber[newIndex]));}
            else {
                Shifr.append(alfaber[newIndex]);
            }
        }
        return Shifr.toString();
    }


    public void fileImport(String fileName, String message, int literalOut) {
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(fileName);

            writer.write(message + ";" + literalOut + System.lineSeparator());
            writer.close();

            if (!file.exists()) {
                System.out.println("File is created with name: " + fileName);
            } else {
                System.out.println("Data added to existing file: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void file_export(String file_name){
        File file = new File(file_name);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        StringBuilder full_text = new StringBuilder();
        try(Scanner scanner = new Scanner(file)){

            while(scanner.hasNextLine()) {
            full_text.append(scanner.nextLine());
            }
            String[] variables = full_text.toString().split(";");
            if (variables.length >= 2) {
                this.text = variables[0];
                this.literal_out = Integer.parseInt(variables[1]);
            } else {
                System.out.println("File does not contain enough data.");
            }
        }catch (IOException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }


    public int[] numericalofLetters(String text, char[] alfaber) {
        int[] letters_index = new int[text.length()];
        char[] char_text = text.toCharArray();

        for (int i = 0; i < char_text.length; i++) {
            boolean found = false;
            for (int j = 0; j < alfaber.length; j++) {
                if (char_text[i] == alfaber[j]) {
                    letters_index[i] = j;
                    found = true;
                    break;
                }
            }
            if (!found) {
                letters_index[i] = -1;  // Мітка для невідомих символів
            }
        }
        return letters_index;
    }

    public String lettersFromNumbers(int[] number_text) {
        StringBuilder new_text = new StringBuilder();
        for (int i = 0; i < number_text.length; i++) {
            if (number_text[i] == -1) {
                new_text.append(' ');  // Додаємо пробіл для символів, яких немає в алфавіті
            } else {
                new_text.append(alfaber[number_text[i]]);
            }
        }
        return new_text.toString();
    }

    private char[] createCharArray(String input) {
        char[] result = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            result[i] = input.charAt(i);
        }

        return result;
    }
}

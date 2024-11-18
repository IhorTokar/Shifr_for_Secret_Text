package org.example;
import java.io.*;
import java.util.Scanner;

public class Wizeners_CodeShifr {
    private String text = "";
    private String code_text = "";
    private final char[] alfaber;



    public Wizeners_CodeShifr(String text, String code_text) {
        this.setText(text);
        this.setCode_text(code_text);
        alfaber = createCharArray(" абвгґдеєжзиіїйклмнопрстуфхцчшщьюя");
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setCode_text(String code_text) {
        this.code_text = code_text;
    }

    public String getCode_text() {
        return this.code_text;
    }

    public String Wizener_shifrator(String text, String code_text) {
        char[] char_text = text.toCharArray();
        int[] Num_text = numericalofLetters(text.toLowerCase(), alfaber);

        String Filled_code_text = code_text_fill(code_text, text);
        int[] Num_code_text = numericalofLetters(Filled_code_text.toLowerCase(), alfaber);

        StringBuilder Shifr = new StringBuilder();

        for (int i = 0; i < Num_text.length; i++) {
            int newIndex = (Num_text[i] + Num_code_text[i]) % alfaber.length;
            if (newIndex < 0) newIndex =+ alfaber.length;
            if(Character.isUpperCase(char_text[i])){
                Shifr.append(Character.toUpperCase(alfaber[newIndex]));
            }
            else {
                Shifr.append(alfaber[newIndex]);
            }
        }
        return Shifr.toString();
    }

    public String Wizener_deshifrator(String text, String code_text) {
        char[] char_text = text.toCharArray();
        int[] Num_text = numericalofLetters(text.toLowerCase(), alfaber);

        String Filled_code_text = code_text_fill(code_text, text);
        int[] Num_code_text = numericalofLetters(Filled_code_text.toLowerCase(), alfaber);

        StringBuilder Shifr = new StringBuilder();
        for (int i = 0; i < Num_text.length; i++) {
            int newIndex = (Num_text[i] - Num_code_text[i]) % alfaber.length;
            if (newIndex < 0) newIndex += alfaber.length;
            if(Character.isUpperCase(char_text[i])){
                Shifr.append(Character.toUpperCase(alfaber[newIndex]));}
            else {
                Shifr.append(alfaber[newIndex]);
            }
        }
        return Shifr.toString();
    }
    public void fileImport(String fileName, String message, String code_text) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("File is created with name: " + fileName);
            } else {
                System.out.println("Data added to existing file: " + fileName);
            }

            FileWriter writer = new FileWriter(fileName, true);  // true для дописування
            writer.write(message + "::" + code_text + System.lineSeparator());
            writer.close();

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
            String[] variables = full_text.toString().split("::");
            if (variables.length >= 2) {
                this.text = variables[0];
                this.code_text = variables[1];
            } else {
                System.out.println("File does not contain enough data.");
            }
        }catch (IOException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }

    public String code_text_fill(String code_text, String text){
        StringBuilder filled_code_text = new StringBuilder();
        int text_length = text.length();
        int code_length = code_text.length();

        for (int i = 0; i < text_length; i++) {
            filled_code_text.append(code_text.charAt(i % code_length));
        }
        return filled_code_text.toString();
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

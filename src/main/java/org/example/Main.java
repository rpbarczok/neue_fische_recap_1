package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        File pwFile = new File (System.getProperty("user.dir")+"\\src\\main\\java\\org\\example\\commonPws.txt");

        if (pwFile.isFile()) {
            File pwFileFiltered = new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\example\\commonPwsFiltered.txt");
            if (pwFileFiltered.createNewFile()) {

                FileWriter writer = new FileWriter(pwFileFiltered);

                Scanner reader = new Scanner(pwFile);

                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    if (valLen(data) && valNum(data) && valCap(data) && valLow(data)) {
                        writer.write(data+System.lineSeparator());
                    }
                }
            }
        } else {
            System.out.println("Keine Passwort-Liste gefunden");
        }

        Scanner sc=new Scanner(System.in);
        System.out.println("Bitte wähle ein Passwort und gebe es ein:");
        String password=sc.nextLine();

        if (!valLen(password)) {
            System.out.println("Passwort ist zu kurz");
        }
        if (!valNum(password))  {
            System.out.println("Passwort muss eine Ziffer enthalten");
        }
        if (!valCap(password))  {
            System.out.println("Passwort muss einen Großbuchstaben enthalten");
        }
        if (!valLow(password))  {
            System.out.println("Passwort muss einen Kleinbuchstaben enthalten");
        }
    }

    public static boolean valLen(String password) {
        return password.length() >= 8;
    }

    public static boolean valNum(String password) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static boolean valCap(String password) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static boolean valLow(String password) {
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static boolean valCommon(String password) throws FileNotFoundException {
        File pwFileFiltered = new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\example\\commonPwsFiltered.txt");
        if (pwFileFiltered.isFile()) {
            Scanner reader = new Scanner(pwFileFiltered);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (password.equals(data)) {
                    return false;
                }
            }
        }
        return  true;
    }
}
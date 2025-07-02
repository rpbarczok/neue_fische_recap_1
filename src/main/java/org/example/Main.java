package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static String fileSeperator = FileSystems.getDefault().getSeparator();
    static String pathToMain = fileSeperator + "src" + fileSeperator + "main" + fileSeperator + "java" + fileSeperator + "org" + fileSeperator +  "example" + fileSeperator;
    static String specialChar = "!\"#$%&'*+,./:;=?@\\^`|~";

    public static void main(String[] args) throws IOException {

        File pwFile = new File (System.getProperty("user.dir") + pathToMain + "commonPws.txt");

        if (pwFile.isFile()) {
            File pwFileFilteredCustomized = new File(System.getProperty("user.dir") + pathToMain + "commonPwsFilteredCustomized.txt");
            if (pwFileFilteredCustomized.createNewFile()) {

                FileWriter writer = new FileWriter(pwFileFilteredCustomized);

                Scanner reader = new Scanner(pwFile);

                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    if (valLen(data) && valNum(data) && valCap(data) && valLow(data)) {
                        writer.write(data+System.lineSeparator());
                    }
                }
            }
        }

        Scanner sc=new Scanner(System.in);
        System.out.println("Bitte wähle ein Passwort und gebe es ein:");
        String password=sc.nextLine();

        valPW(password);

        valSpecial(password);

    }

    public static boolean valPW(String password) {
        boolean isGoodPW =true;
        if (!valLen(password)) {
            System.out.println("Passwort ist zu kurz");
            isGoodPW =false;
        }
        if (!valNum(password))  {
            System.out.println("Passwort muss eine Ziffer enthalten");
            isGoodPW =false;
        }
        if (!valCap(password))  {
            System.out.println("Passwort muss einen Großbuchstaben enthalten");
            isGoodPW =false;
        }
        if (!valLow(password)) {
            System.out.println("Passwort muss einen Kleinbuchstaben enthalten");
            isGoodPW =false;
        }
        if (!valSpecial(password)) {
            System.out.println("Passwort muss ein Sonderzeichen (" + specialChar + ") enthalten");
        }
        if (isGoodPW) {
            System.out.println("Passwort entspricht den Regeln");
        }

        return isGoodPW;
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
        File pwFileFiltered = new File(System.getProperty("user.dir") + pathToMain + "commonPwsFiltered.txt");
        if (pwFileFiltered.isFile()) {
            Scanner reader = new Scanner(pwFileFiltered);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (password.equals(data)) {
                    return false;
                }
            }
        }
        File pwFileFilteredCustomized = new File(System.getProperty("user.dir") + pathToMain + "commonPwsFilteredCustomized.txt");
        if (pwFileFilteredCustomized.isFile()) {
            Scanner reader = new Scanner(pwFileFilteredCustomized);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (password.equals(data)) {
                    return false;
                }
            }
        }
        return  true;
    }

    public static boolean valSpecial(String password) {
        boolean containsSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            for (int j = 0; j < specialChar.length(); j++) {
                if (password.charAt(i) == specialChar.charAt(j)) {
                    containsSpecial = true;
                    break;
                }
            }
            if (containsSpecial) {
                break;
            }
        }
        return containsSpecial;
    }
}
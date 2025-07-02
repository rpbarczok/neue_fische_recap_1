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
    static String lineSeparator= System.lineSeparator();
    static String fileSeparator = FileSystems.getDefault().getSeparator();
    static String pathToMain = fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator + "org" + fileSeparator +  "example" + fileSeparator;
    static String specialChar = "!\"#$%&'*+,./:;=?@\\^`|~";
    static String[] abc = {"A","B","C","D","E","F","G","H","I","J","K","L","M", "N", "O", "P","Q","R","S","T","U","V","W","X","Y","Z"};

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
                        writer.write(data+lineSeparator);
                    }
                }
            }
        }

        System.out.println("Vorgeschlagenes Passwort: " + genPw());
        Scanner sc=new Scanner(System.in);
        System.out.println("Bitte wähle ein Passwort und gebe es ein:");
        String password=sc.nextLine();

        System.out.println(valPW(password));;

    }

    public static String valPW(String password) throws FileNotFoundException {
        String message="";

        if (!valLen(password)) {
            message += "Passwort ist zu kurz"+lineSeparator;
        }
        if (!valNum(password))  {
            message += "Passwort muss eine Ziffer enthalten"+lineSeparator;
        }
        if (!valCap(password))  {
            message +="Passwort muss einen Großbuchstaben enthalten"+lineSeparator;
        }
        if (!valLow(password)) {
            message += "Passwort muss einen Kleinbuchstaben enthalten"+lineSeparator;
        }
        if (!valSpecial(password)) {
            message +="Passwort muss ein Sonderzeichen (" + specialChar + ") enthalten"+lineSeparator;
        }
        if (!valCommon(password)) {
            message += "Passwort taucht in einer Liste der häufigsten Passwörter auf" +lineSeparator;
        }
        if (message.isEmpty()) {
            message = "Passwort entspricht den Regeln"+lineSeparator;
        }

        return message;
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

    public static String genPw() throws FileNotFoundException {
        String password = "";
        boolean isGoodPW = false;
        while (!isGoodPW) {
            password = "";
            for (int i = 0; i < 8; i++) {
                switch ((int) (Math.random() * 10 + 1)) {
                    case 1,2,3:
                        int num = (int) (Math.random() * 10);
                        password += num;
                        break;
                    case 4,5,6:
                        password += randomCap();
                        break;
                    case 7,8,9:
                        password += randomCap().toLowerCase();
                        break;
                    default:
                        password += randomSpecial();
                }
            }
            if (valPW(password).equals("Passwort entspricht den Regeln"+lineSeparator)) {
                isGoodPW = true;
            }
        }

        return password;
    }


    public static String randomCap() {
        int num = (int) (Math.random() * abc.length);
        return abc[num];
    }

    public static String randomSpecial() {
        int num = (int) (Math.random() * specialChar.length());
        return String.valueOf(specialChar.charAt(num));
    }
}
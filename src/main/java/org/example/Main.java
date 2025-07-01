package org.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("Please choose your password:");
        String password=sc.nextLine();

        String response = "Gutes Passwort";
        if (!valLen(password)) {
            response = "Passwort ist zu kurz";
        }
        if (!valNum(password))  {
            response = "Passwort muss eine Ziffer enthalten";
        }
        if (!valCap(password))  {
            response = "Passwort muss einen Großbuchstaben enthalten";
        }
        if (!valLow(password))  {
            response = "Passwort muss einen Kleinbuchstaben enthalten";
        }
        System.out.println(response);
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
}
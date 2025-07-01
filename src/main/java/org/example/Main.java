package org.example;

import java.io.File;
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


        Scanner sc=new Scanner(System.in);
        System.out.println("Bitte wähle ein Passwort und gebe es ein:");
        String password=sc.nextLine();

        File pwFile = new File ("commonPws.txt");
        if (pwFile.isFile()) {
            File pwFileFiltered = new File("commonPwsFiltered.txt");
            if (!pwFileFiltered.isFile()){
                createCPWList();
            }
        } else {
            System.out.println("Passwort-Liste nicht gefunden");
        }

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

    public static void createCPWList() throws IOException {
        System.out.println("Starting creating file");
        ArrayList<String> list=new ArrayList<>();
        File file = new File("commonPws.txt");
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            if (data.length() >= 8) {
                list.add(data);
            }
        }
        File passwordFile = new File("commonPwsFiltered.txt");
        if (passwordFile.createNewFile()) {
            System.out.println("File created: " + passwordFile.getName());
        } else {
            System.out.println("File already exists.");
        }
        FileWriter writer = new FileWriter("commonPwsFiltered.txt");
        writer.write(list.toString());
        System.out.println("Finished creating file");
    }
}
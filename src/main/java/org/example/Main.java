package org.example;

import java.util.Scanner;

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
        System.out.println(response);
    }

    public static boolean valLen(String password) {
        return password.length() >= 8;
    }
}
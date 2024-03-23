package com.alfred;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    final static byte MONTHS_IN_YEARS = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {

        int principal = (int) readNumber("Principal($1k - $1m): ", 1000, 1000000);
        float annualInterest = (float) readNumber("Annual interest rate(1 - 30): ", 0, 30);
        byte years = (byte) readNumber("Period(years) (1 - 30 years): ", 0, 30);

        printMortgage(principal, annualInterest, years);
        printPaymentSchedule(years, principal, annualInterest);
    }

    private static void printMortgage(int principal, float annualInterest, byte years) {
        double finalMortgage = calculateMortgage(principal, annualInterest, years);
        String formattedMortgage = NumberFormat.getCurrencyInstance().format(finalMortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + formattedMortgage);
    }

    private static void printPaymentSchedule(byte years, int principal, float annualInterest) {
        System.out.println();
        System.out.println("Payment Schedule");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEARS; month++) {
            double balance = calculateRemainingPayment(principal, annualInterest, years, month);
            String formattedBalance = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println(formattedBalance);
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value > min && value <= max)
                break;
            System.out.println("Enter a value between " + min + "and " + max);
        }
        return value;
    }


    public static double calculateMortgage(int principal, float annualInterest, byte years) {
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEARS;
        int numberOfPayments = years * MONTHS_IN_YEARS;

        float numerator = (1 + monthlyInterest);
        double poweredNumerator = Math.pow(numerator, numberOfPayments);
        double numeratorAnswer = monthlyInterest * poweredNumerator;
        double denominator = poweredNumerator - 1;
        double finalMortgage = principal * (numeratorAnswer / denominator);
        return finalMortgage;
    }

    public static double calculateRemainingPayment(int principal, float annualInterest, byte years, short numberOfPaymentsMade) {
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEARS;
        int numberOfPayments = years * MONTHS_IN_YEARS;

        double numerator = principal * ((Math.pow(1 + monthlyInterest, numberOfPayments)) -
                (Math.pow(1 + monthlyInterest, numberOfPaymentsMade)));
        double denominator = (Math.pow(1 + monthlyInterest, numberOfPayments)) - 1;
        double remainingPayments = numerator / denominator;
        return remainingPayments;
    }
}

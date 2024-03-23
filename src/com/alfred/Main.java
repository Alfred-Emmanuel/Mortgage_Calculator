package com.alfred;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int principal = (int) readNumber("Principal($1k - $1m): ", 1000, 1000000);
        float annualInterest = (float) readNumber("Annual interest rate(1 - 30): ", 0, 30);
        byte years = (byte) readNumber("Period(years) (1 - 30 years): ", 0, 30);

        double finalMortgage = calculateMortgage(principal, annualInterest, years);
        String currency = NumberFormat.getCurrencyInstance().format(finalMortgage);
        System.out.println("Mortgage: " + currency);
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
        final byte MONTHS_IN_YEARS = 12;
        final byte PERCENT = 100;

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEARS;
        int numberOfPayments = years * MONTHS_IN_YEARS;


        float numerator = (1 + monthlyInterest);
        double poweredNumerator = Math.pow(numerator, numberOfPayments);
        double numeratorAnswer = monthlyInterest * poweredNumerator;
        double denominator = poweredNumerator - 1;
        double finalMortgage = principal * (numeratorAnswer / denominator);
        return finalMortgage;
    }
}

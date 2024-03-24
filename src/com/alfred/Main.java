package com.alfred;

public class Main {

    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal($1k - $1m): ", 1000, 1000000);
        float annualInterest = (float) Console.readNumber("Annual interest rate(1 - 30): ", 0, 30);
        byte years = (byte) Console.readNumber("Period(years) (1 - 30 years): ", 0, 30);

        var calculator = new MortgageCalculator(principal, annualInterest, years);

        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
    }
}

package com.alfred;

public class MortgageCalculator {
    private static final byte PERCENT = 100;
    private static final byte MONTHS_IN_YEARS = 12;
    private int principal;
    private float annualInterest;
    private byte years;

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }


    public double calculateMortgage() {
        float monthlyInterest = getMonthlyInterest();
        int numberOfPayments = getNumberOfPayments();

        float numerator = (1 + monthlyInterest);
        double poweredNumerator = Math.pow(numerator, numberOfPayments);
        double numeratorAnswer = monthlyInterest * poweredNumerator;
        double denominator = poweredNumerator - 1;
        double finalMortgage = principal * (numeratorAnswer / denominator);
        return finalMortgage;
    }

    public double calculateRemainingPayment(short numberOfPaymentsMade) {
        float monthlyInterest = getMonthlyInterest();
        int numberOfPayments = getNumberOfPayments();

        double numerator = principal * ((Math.pow(1 + monthlyInterest, numberOfPayments)) -
                (Math.pow(1 + monthlyInterest, numberOfPaymentsMade)));
        double denominator = (Math.pow(1 + monthlyInterest, numberOfPayments)) - 1;
        double remainingPayments = numerator / denominator;
        return remainingPayments;
    }

    public double[] getRemainingBalances() {
        var balances = new double[getNumberOfPayments()];
        for (short month = 1; month <= balances.length; month++)
            balances[month - 1] = calculateRemainingPayment(month);
        return balances;
    }

    private int getNumberOfPayments() {
        return years * MONTHS_IN_YEARS;
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEARS;
    }
}


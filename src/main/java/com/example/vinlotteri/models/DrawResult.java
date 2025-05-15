package com.example.vinlotteri.models;

public class DrawResult {
    private String wineName;
    private double winePrice;
    private String winnerName;
    private int ticketNumber;

    public DrawResult(String wineName, double winePrice, String winnerName, int ticketNumber) {
        this.wineName = wineName;
        this.winePrice = winePrice;
        this.winnerName = winnerName;
        this.ticketNumber = ticketNumber;
    }

    public String getWineName() {
        return wineName;
    }

    public double getWinePrice() {
        return winePrice;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }
}

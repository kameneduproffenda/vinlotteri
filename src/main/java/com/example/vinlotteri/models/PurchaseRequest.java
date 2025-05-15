package com.example.vinlotteri.models;

import java.util.List;

public record PurchaseRequest (String buyerName, List<Integer> ticketNumbers) {}

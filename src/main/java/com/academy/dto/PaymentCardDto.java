package com.academy.dto;

import lombok.Data;

@Data
public class PaymentCardDto {
    private String number;
    private String expDate;
    private String cardHolderName;
    private String cvv;
}

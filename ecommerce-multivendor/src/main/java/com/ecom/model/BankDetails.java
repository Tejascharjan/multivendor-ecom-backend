package com.ecom.model;

import lombok.Data;

@Data
public class BankDetails {
    private String accountNumber;
    private  String accountHolder;
    private String ifscCode;
}

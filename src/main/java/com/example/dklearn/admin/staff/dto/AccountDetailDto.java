package com.example.dklearn.admin.staff.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDetailDto {
    private String accountName;
    private String accountNumber;
    private String bankName;
    private String currency;
}

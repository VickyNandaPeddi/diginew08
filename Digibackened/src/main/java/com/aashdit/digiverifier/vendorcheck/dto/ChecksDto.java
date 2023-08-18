package com.aashdit.digiverifier.vendorcheck.dto;

import lombok.Getter;
import lombok.Setter;

import javax.swing.text.StringContent;

@Setter
@Getter
public class ChecksDto {


    private String courceName;
    private Long quantity;
    private Double perUnitPrice;
    private String colorCode;
    private String totalCode;


}

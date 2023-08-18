package com.aashdit.digiverifier.vendorcheck.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
@Setter
public class VendorAttributeDto {

    private String sourceName;
    private ArrayList<String> vendorAttirbuteValue;

}

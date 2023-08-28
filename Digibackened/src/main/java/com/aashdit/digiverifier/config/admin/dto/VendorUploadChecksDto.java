package com.aashdit.digiverifier.config.admin.dto;

import com.aashdit.digiverifier.vendorcheck.dto.VendorAttributeDto;
import lombok.*;

import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendorUploadChecksDto<t> {

    private String userFirstName;
    private Long VendorChecks;
    private byte[] document;
    private String documentname;
    private String AgentColor;
    private String colorHexCode;
    private Long colorId;
    private String checkUniqueId;

    private ArrayList<VendorAttributeDto> vendorAttirbuteValue;


    public VendorUploadChecksDto(String userFirstName, Long vendorChecks, byte[] document, String documentname, String agentColor, String colorHexCode, Long colorId) {
        this.userFirstName = userFirstName;
        VendorChecks = vendorChecks;
        this.document = document;
        this.documentname = documentname;
        AgentColor = agentColor;
        this.colorHexCode = colorHexCode;
        this.colorId = colorId;
    }

    public VendorUploadChecksDto(String userFirstName, Long vendorChecks, byte[] document, String documentname, String agentColor, String colorHexCode, Long colorId, ArrayList<VendorAttributeDto> vendorAttirbuteValue) {
        this.userFirstName = userFirstName;
        VendorChecks = vendorChecks;
        this.document = document;
        this.documentname = documentname;
        AgentColor = agentColor;
        this.colorHexCode = colorHexCode;
        this.colorId = colorId;
        this.vendorAttirbuteValue = vendorAttirbuteValue;
    }

    public VendorUploadChecksDto(String userFirstName, Long vendorChecks, byte[] document, String documentname, String agentColor, String colorHexCode, Long colorId, String checkUniqueId) {
        this.userFirstName = userFirstName;
        VendorChecks = vendorChecks;
        this.document = document;
        this.documentname = documentname;
        AgentColor = agentColor;
        this.colorHexCode = colorHexCode;
        this.colorId = colorId;
        this.checkUniqueId = checkUniqueId;
    }

}

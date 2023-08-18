package com.aashdit.digiverifier.config.candidate.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

@Data
public class EpfoDataResDTO {
	
	String uan;
	
	String name;
	
	String company;
	
	String doe;
	
	String doj;
}

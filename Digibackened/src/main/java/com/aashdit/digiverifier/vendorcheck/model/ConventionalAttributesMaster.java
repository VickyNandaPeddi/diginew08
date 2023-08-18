/**
 *
 */
package com.aashdit.digiverifier.vendorcheck.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import com.aashdit.digiverifier.config.superadmin.model.Source;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ${ashwani}
 */

@Getter
@Setter
@Entity
@Table(name = "t_dgv_conventional_attributes_remarks_master")
public class ConventionalAttributesMaster implements Serializable {

    private static final long serialVersionUID = 3513694544081413484L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conventional_attributes_id")
    private Long Id;
    @Lob
    @Column(name = "check_attributes")
    private ArrayList<String> checkAttibutes;
    @Column(name = "source_ids")
    private ArrayList<Long> sourceIds;


}

package com.aashdit.digiverifier.common.model;

import com.aashdit.digiverifier.common.enums.ContentCategory;
import com.aashdit.digiverifier.common.enums.ContentSubCategory;
import com.aashdit.digiverifier.common.enums.ContentType;
import com.aashdit.digiverifier.common.enums.FileType;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_dgv_content")
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_id")
	Long contentId;
	
	@Column(name = "candidate_id")
	private Long candidateId;
	
	@Column(name = "bucket_name")
	private String bucketName;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "file_type")
	@Enumerated(EnumType.STRING)
	private FileType fileType;
	
	@Column(name = "content_type")
	@Enumerated(EnumType.STRING)
	private ContentType contentType;
	
	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	private ContentCategory contentCategory;

	@Type(type="org.hibernate.type.BinaryType")
    @Column(name = "document", columnDefinition="LONGBLOB")
    private byte[] Document;
	
	@Column(name = "sub_category")
	@Enumerated(EnumType.STRING)
	private ContentSubCategory contentSubCategory;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;
	
}

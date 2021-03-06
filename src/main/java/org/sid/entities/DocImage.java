package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "DocImage")
public class DocImage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long Id;
	
	private String docType;
	
	private String docName;
	
	private String url;
	
	@Lob
	private byte[] data;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public DocImage(String docType, String docName, byte[] data) {
		super();
		this.docType = docType;
		this.docName = docName;
		this.data = data;
	}

	public DocImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DocImage(String docType, String docName, String url, byte[] data) {
		super();
		this.docType = docType;
		this.docName = docName;
		this.url = url;
		this.data = data;
	}

	
	
	
	
}

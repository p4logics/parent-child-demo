package com.parent.child.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection = "child_details")
@JsonIgnoreProperties
public class Child {
	public static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String parentId;
	private Double paidAmount;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

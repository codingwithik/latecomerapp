package com.example.lateComerApp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(length = 80)
	private String employeeName;
	@Column(length = 70, unique = true)
	private String email;
	@Column(columnDefinition = "Text")
	private String address;
	private Double bill;
	@Temporal(TemporalType.TIME)
	private Date arrivalTime;
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@PrePersist
	private void setAddedDate() {
		this.dateCreated = new Date();
	}
	
	@PreUpdate
	private void setUpdatedDate() {
		this.dateUpdated = new Date();
	}
	
	@Override
	public boolean equals(Object obj) {

		return this.id.equals(((Employee) obj).getId());

	}
}

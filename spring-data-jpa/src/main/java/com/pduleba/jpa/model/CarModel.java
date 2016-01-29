package com.pduleba.jpa.model;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "owner")
public @Data class CarModel {
	
	public CarModel(String name, Integer wheelsNumber, Clob spec, Blob image) {
		super();
		this.name = name;
		this.wheelsNumber = wheelsNumber;
		this.spec = spec;
		this.image = image;
		this.createdIn = new Date(System.currentTimeMillis());
	}

	@Id
	@GeneratedValue(generator = "car-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-sequence-generator", sequenceName = "CAR_SEQ", initialValue = 1, allocationSize = 20)
	private Long id;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "CREATED_IN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdIn;
	
	@Column(name = "WHEELS_NUMBER")
	private Integer wheelsNumber;
	
	@Lob
	@Column(name = "SPEC")
	private Clob spec;
	
	@Lob
	@Column(name = "IMAGE")
	private Blob image;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = OwnerModel.class)
	@JoinColumn(name = "ID_OWNER")
	private OwnerModel owner;

	@Override
	public String toString() {
		return "CarModel [id=" + id + "]";
	}
	
}

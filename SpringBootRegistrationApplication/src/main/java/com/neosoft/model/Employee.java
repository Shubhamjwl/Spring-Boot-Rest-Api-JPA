package com.neosoft.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Emp_tab")
public class Employee {

	@Id
	@GeneratedValue
	private Integer employeeId;
	@Column(name = "first_name")
	@NotNull(message = "First name is mandatory field")
	private String firstName;
	@Column(name = "sur_name")
	@NotNull(message = "Sur name is mandatory field")
	private String surName;
	@Column(name = "DOB")
	@NotNull(message = "DOB is mandatory field")
	@Past(message = "DOB  should be past date")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone ="Asia/Kolkata")
	private Date employeeDob;
	@Column(name = "mobile_number")
	@NotNull(message = "Mobile number is mandatory field")
	// @Range(max = 10,min = 10,message = "Mobile number should be numerical")
	private Long mobileNumber;
	@Column(name = "DOJ")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone ="Asia/Kolkata")
	@NotNull(message = "DOJ is mandatory field")
	private Date employeeDoj;
	@Column(name = "country")
	@NotNull(message = "Country is mandatory field")
	private String country;
	@Column(name = "state")
	@NotNull(message = "State is mandatory field")
	private String state;
	@Column(name = "city")
	private String city;
	@Column(name = "pincode")
	@NotNull(message = "Pincode is mandatory field")
	private Integer pincode;
	@JsonIgnore
	@Column(name = "active_flag")
	private Integer activeFlag;

}

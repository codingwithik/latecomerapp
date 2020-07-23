package com.example.lateComerApp.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class AddEmployee {
	
	private String employeeName;
	private String email;
	private String address;
	private Date arrivalTime;
}

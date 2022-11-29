package com.billingapi.model;
import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String userName;

}

package com.ez.launer.user.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserAllVO {
	private int no;
	private int userCode;
	private String name;
	private String email;
	private String pwd;
	private String socialLoginKey;
	private String socialLoginHost;
	private String hp;
	private Timestamp lastAccessDate;
	private Timestamp startDate;
	private Timestamp outDate;
	private int point;
	private int officeNo;
	private String address;
	private String addressDetail;
	private String zipcode;
	private String entermethod;
	private int addressNo;
	private double latY;
	private double lonX;
	private String randomCode;
	
}

package com.exam.client.dto;

import java.io.Serializable;

public class DeviceDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	private int idSession;
	private int idCompany=0;
	private int idService;
	private int status;
	private int part1Id;
	private int part2Id=0;
	private String time1="";
	private String time1B;
	private String time2="";
	private String time2B;
	private int type1Id;
	private String type2Name;
	private int type2Id;
	private String address1="";
	private String location1;
	private String notes;
	private String data1="";
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPart1Id() {
		return part1Id;
	}

	public void setPart1Id(int part1Id) {
		this.part1Id = part1Id;
	}

	public int getPart2Id() {
		return part2Id;
	}

	public void setPart2Id(int part2Id) {
		this.part2Id = part2Id;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime1B() {
		return time1B;
	}

	public void setTime1B(String time1b) {
		time1B = time1b;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getTime2B() {
		return time2B;
	}

	public void setTime2B(String time2b) {
		time2B = time2b;
	}

	public int getType1Id() {
		return type1Id;
	}

	public void setType1Id(int type1Id) {
		this.type1Id = type1Id;
	}

	public String getType2Name() {
		return type2Name;
	}

	public void setType2Name(String type2Name) {
		this.type2Name = type2Name;
	}

	public int getType2Id() {
		return type2Id;
	}

	public void setType2Id(int type2Id) {
		this.type2Id = type2Id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getLocation1() {
		return location1;
	}

	public void setLocation1(String location1) {
		this.location1 = location1;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}
	
	
	

}

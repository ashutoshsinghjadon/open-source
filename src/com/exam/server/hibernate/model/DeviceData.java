package com.exam.server.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Device Entity
 * Author Ashutosh
 */

@Entity(name="Device_Data")
public class DeviceData {
	@Id @GeneratedValue
	int id;
	
	@Column(name="ID_Session")
	private int idSession;
	
	@Column(name="ID_Company")
	private int idCompany;
	
	@Column(name="ID_Service")
	private int idService;
	
	@Column(name="Status")
	private int status;
	
	@Column(name="Part1_ID")
	private int part1Id;
	
	@Column(name="Part2_ID")
	private int part2Id;
	
	@Column(name="Time_1")
	private Date time1;
	
	@Column(name="Time_1B")
	private Date time1B;
	
	@Column(name="Time_2")
	private Date time2;
	
	@Column(name="Time_2B")
	private Date time2B;
	
	@Column(name="Type1_ID")
	private int type1Id;
	
	@Column(name="Type2_NAME")
	private String type2Name;
	
	@Column(name="Type2_ID")
	private int type2Id;
	
	@Column(name="Address_1")
	private String address1;
	
	@Column(name="Location_1")
	private String location1;
	
	@Column(name="Notes")
	private String notes;
	
	@Column(name="Data_1")
	private String data1;

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

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public Date getTime1B() {
		return time1B;
	}

	public void setTime1B(Date time1b) {
		time1B = time1b;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}

	public Date getTime2B() {
		return time2B;
	}

	public void setTime2B(Date time2b) {
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

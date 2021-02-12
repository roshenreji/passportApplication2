package com.mindtree.codingChallenge3.passportApplication.Entity;

public class Passport {

	private int id;
	private String passportNumber;
	private Person person;
	private int personId;

	public Passport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passport(int id, String passportNumber, Person person) {
		super();
		this.id = id;
		this.passportNumber = passportNumber;
		this.person = person;
	}
	public Passport(int id, String passportNumber, int personId) {
		super();
		this.id = id;
		this.passportNumber = passportNumber;
		this.personId = personId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}

package com.mindtree.codingChallenge3.passportApplication.Entity;

public class Person {

	private int id;
	private String name;
	private String birthPlace;
	private int age;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(int id, String name, String birthPlace, int age) {
		super();
		this.id = id;
		this.name = name;
		this.birthPlace = birthPlace;
		this.age = age;
	}

	public Person(String name, String birthPlace) {
		super();

		this.name = name;
		this.birthPlace = birthPlace;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

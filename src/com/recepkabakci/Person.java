package com.recepkabakci;

import java.time.LocalDate;

public abstract class Person {
	private String    firstName;  // must
	private String    middleName; // optinal
	private String    lastName;	// must
	private String    gender;  // optinal
	private boolean   married; // optional
	private LocalDate birthDate;  // optional
	
	public abstract void getMarried(String newLastName) throws CannotMarryException;
	
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName  = lastName;
	}

	@Override
	public String toString() {
		return "Person [Name=" + this.getFullName() + ", gender=" + this.gender
				+ ", married=" + this.married + ", birthDate=" + this.birthDate + "]";
	}

	private String getFullName() {
		return this.middleName == null ? this.firstName + " " + this.lastName : 
										 this.firstName + " " + this.middleName + " " + this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	
	public String getGender() {
		return this.gender;
	}

	public boolean isMarried() {
		return this.married;
	}

	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	protected void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	protected void setGender(String gender) {
		this.gender = gender;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	protected void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}

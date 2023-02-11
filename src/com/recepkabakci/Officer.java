package com.recepkabakci;

import java.time.LocalDate;

public class Officer extends Employee {
	private static int ACT_NUMBER = 0;
	
	private Officer(String firstName, String lastName, double startSalary, LocalDate startDate) {  // employee'den gelen ve implemente etmek zorunda olduğumuz
		super(firstName, lastName, startSalary, startDate);											// constructor. Ama private yapıp dışarıdan kullanmayı engelledik
	}

	private Officer(OfficerBuilder officerBuilder) {	// BuilderPattern'inin asıl constructor'u bu. özel olmalı ki başka kimse çağıramasın
		super(officerBuilder.firstName, officerBuilder.lastName, officerBuilder.startSalary, officerBuilder.startDate); // employee sınıfının constructor'unu çağırıyoruz
		this.setBirthDate(officerBuilder.birthDate);  // hiyerarşik yapıda niteliklere erişemediğimizden protected setter metodlarını kullanıyoruz
		this.setGender(officerBuilder.gender);
		this.setMiddleName(officerBuilder.middleName);
		this.setMarried(officerBuilder.married);
	}


	@Override
	public String createIdNumber() {
		Officer.ACT_NUMBER++;
		String numAsString = String.format("M-%03d", Officer.ACT_NUMBER);
		return numAsString;
	}

	@Override
	public double getIncreaseRate() {
		return 0.09;
	}

	@Override
	public void getMarried(String newLastName) throws CannotMarryException {
		this.setMarried(true);
		this.setLastName(newLastName);
	}

	public static class OfficerBuilder {
		private String    firstName;   // must
		private String    middleName;  // optinal
		private String    lastName;    // must
		private String    gender;      // optinal
		private boolean   married;     // optional
		private LocalDate birthDate;   // optional
		private LocalDate startDate;   // must
		private double    startSalary; // must

		public OfficerBuilder(String firstName, String lastName, LocalDate startDate, double salary) {
			this.firstName = firstName;
			this.lastName  = lastName;
			this.startDate = startDate;
			this.startSalary = salary;
		}
		
		public OfficerBuilder middleName(String middle) {
			this.middleName = middle;
			return this;
		}
		
		public OfficerBuilder gender (String gender) {
			this.gender = gender;
			return this;
		}
		
		public OfficerBuilder married (boolean married) {
			this.married = married;
			return this;
		}
		
		public OfficerBuilder birthDate(LocalDate birth) {
			this.birthDate = birth;
			return this;
		}
		
		public Officer build() {
			return new Officer(this);
		}
	}
	
}

package com.bilgeadam.marathon02;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Janitor extends Employee {
	private static int ACT_NUMBER = 0;
	
	private Janitor(String firstName, String lastName, double startSalary, LocalDate startDate) {  // employee'den gelen ve implemente etmek zorunda olduğumuz
		super(firstName, lastName, startSalary, startDate);											// constructor. Ama private yapıp dışarıdan kullanmayı engelledik
	}

	private Janitor(JanitorBuilder janitorBuilder) {	// BuilderPattern'inin asıl constructor'u bu. özel olmalı ki başka kimse çağıramasın
		super(janitorBuilder.firstName, janitorBuilder.lastName, janitorBuilder.startSalary, janitorBuilder.startDate); // employee sınıfının constructor'unu çağırıyoruz
		this.setBirthDate(janitorBuilder.birthDate);  // hiyerarşik yapıda niteliklere erişemediğimizden protected setter metodlarını kullanıyoruz
		this.setGender(janitorBuilder.gender);
		this.setMiddleName(janitorBuilder.middleName);
		this.setMarried(janitorBuilder.married);
	}


	@Override
	public String createIdNumber() {
		Janitor.ACT_NUMBER++;
		String numAsString = String.format("Ç-%03d", Janitor.ACT_NUMBER);
		return numAsString;
	}

	@Override
	public void getMarried(String newLastName) throws CannotMarryException {
		this.setMarried(true);
		this.setLastName(newLastName);
	}

	@Override
	public double getIncreaseRate() {
		return 0.085;
	}

	public static class JanitorBuilder {
		private String    firstName;   // must
		private String    middleName;  // optinal
		private String    lastName;    // must
		private String    gender;      // optinal
		private boolean   married;     // optional
		private LocalDate birthDate;   // optional
		private LocalDate startDate;   // must
		private double    startSalary; // must

		public JanitorBuilder(String firstName, String lastName, LocalDate startDate, double salary) {
			this.firstName = firstName;
			this.lastName  = lastName;
			this.startDate = startDate;
			this.startSalary = salary;
		}
		
		public JanitorBuilder middleName(String middle) {
			this.middleName = middle;
			return this;
		}
		
		public JanitorBuilder gender (String gender) {
			this.gender = gender;
			return this;
		}
		
		public JanitorBuilder married (boolean married) {
			this.married = married;
			return this;
		}
		
		public JanitorBuilder birthDate(LocalDate birth) {
			this.birthDate = birth;
			return this;
		}
		
		public Janitor build() {
			return new Janitor(this);
		}
	}
}

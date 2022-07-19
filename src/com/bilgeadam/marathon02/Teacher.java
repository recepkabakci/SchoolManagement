package com.bilgeadam.marathon02;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Teacher extends Employee {
	private static int ACT_NUMBER = 0;
	
	private Teacher(String firstName, String lastName, double startSalary, LocalDate startDate) {  // employee'den gelen ve implemente etmek zorunda olduğumuz
		super(firstName, lastName, startSalary, startDate);											// constructor. Ama private yapıp dışarıdan kullanmayı engelledik
	}

	private Teacher(TeacherBuilder teacherBuilder) {	// BuilderPattern'inin asıl constructor'u bu. özel olmalı ki başka kimse çağıramasın
		super(teacherBuilder.firstName, teacherBuilder.lastName, teacherBuilder.startSalary, teacherBuilder.startDate); // employee sınıfının constructor'unu çağırıyoruz
		this.setBirthDate(teacherBuilder.birthDate);  // hiyerarşik yapıda niteliklere erişemediğimizden protected setter metodlarını kullanıyoruz
		this.setGender(teacherBuilder.gender);
		this.setMiddleName(teacherBuilder.middleName);
		this.setMarried(teacherBuilder.married);
	}


	@Override
	public String createIdNumber() {
		Teacher.ACT_NUMBER++;
		String numAsString = String.format("Ö-%03d", Teacher.ACT_NUMBER);
		return numAsString;
	}
	
	@Override
	public void getMarried(String newLastName) throws CannotMarryException {
		this.setMarried(true);
		this.setLastName(newLastName);
	}

	@Override
	public double getIncreaseRate() {
		return 0.10;
	}


	public static class TeacherBuilder {
		private String    firstName;   // must
		private String    middleName;  // optinal
		private String    lastName;    // must
		private String    gender;      // optinal
		private boolean   married;     // optional
		private LocalDate birthDate;   // optional
		private LocalDate startDate;   // must
		private double    startSalary; // must

		public TeacherBuilder(String firstName, String lastName, LocalDate startDate, double salary) {
			this.firstName = firstName;
			this.lastName  = lastName;
			this.startDate = startDate;
			this.startSalary = salary;
		}
		
		public TeacherBuilder middleName(String middle) {
			this.middleName = middle;
			return this;
		}
		
		public TeacherBuilder gender (String gender) {
			this.gender = gender;
			return this;
		}
		
		public TeacherBuilder married (boolean married) {
			this.married = married;
			return this;
		}
		
		public TeacherBuilder birthDate(LocalDate birth) {
			this.birthDate = birth;
			return this;
		}
		
		public Teacher build() {
			return new Teacher(this);
		}
	}
}

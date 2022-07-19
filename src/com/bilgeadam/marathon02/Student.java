package com.bilgeadam.marathon02;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Student extends Person {
	private static int  STUDENT_COUNT = 0;
	private String      idNumber;
	private List<Class> classes;

	// diğer özellikler eklenmeli ve ya Builder ya da farklı yapıcı metodlar ile değerler atanmalı
	public Student(String firstName, String lastName) {
		super(firstName, lastName);
		Student.STUDENT_COUNT++;
		LocalDate date        = LocalDate.now();
		String    numAsString = String.format(date.getYear() + "-%03d", Student.STUDENT_COUNT);
		this.idNumber = numAsString;
		this.classes = new LinkedList<>();
	}

	@Override
	public String toString() {
		return "Student [idNumber=" + this.idNumber + ", toString()=" + super.toString() + "]";
	}

	@Override
	public void getMarried(String newLastName) throws CannotMarryException {
		throw new CannotMarryException();

	}
	
	public void addClass(Class clazz) {
		this.classes.add(clazz);
	}
	
	public String listClasses() {
		StringBuilder classList = new StringBuilder();
		for (Class class1 : classes) {
			classList.append("\t").append(class1.getName()).append("\n");
		}
		classList.append("\n");
		return classList.toString();
	}

}

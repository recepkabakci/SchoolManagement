package com.bilgeadam.marathon02;

import java.util.Arrays;

public class Class {
	private String    name;
	private Teacher   master;
	private Teacher   assistant;
	private int       maxClassPopulation;
	private int       actClassPopulation;
	private Student[] students;

	public Class(String name, int classPopulation) {
		super();
		this.name               = name;
		this.maxClassPopulation = classPopulation;
		this.actClassPopulation = 0;
		this.students           = new Student[classPopulation];
	}
	
	public void addStudent(Student student) /*throws Exception*/ {
//		if (this.actClassPopulation >= this.maxClassPopulation) {
//			throw new Exception("Max number of students for the class is exceeded");
//		}
//		this.students[this.actClassPopulation] = student;
//		this.actClassPopulation++;
		
		if (this.actClassPopulation < this.maxClassPopulation) {
			this.students[this.actClassPopulation] = student;
			this.actClassPopulation++;
		}
		else {
			System.err.println("Max number of students for the class is exceeded");
		}
	}

	public Teacher getMaster() {
		return this.master;
	}

	public void setMaster(Teacher master) {
		this.master = master;
	}

	public Teacher getAssistant() {
		return this.assistant;
	}

	public void setAssistant(Teacher assistant) {
		this.assistant = assistant;
	}

	public String getName() {
		return this.name;
	}

	public int getMaxClassPopulation() {
		return this.maxClassPopulation;
	}

	public Student[] getStudents() {
		return this.students;
	}

	@Override
	public String toString() {
		return "Class [name=" + this.name + ", master=" + this.master + ", assistant=" + this.assistant
				+ ", classPopulation=" + this.maxClassPopulation + ", students=" + Arrays.toString(this.students) + "]";
	}
}

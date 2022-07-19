package com.bilgeadam.marathon02;

import java.time.LocalDate;

public class SalaryIncrease {
	private LocalDate date;
	private double beforeSalary;
	private double afterSalary;
	
	public SalaryIncrease(LocalDate date, double beforeSalary, double afterSalary) {
		super();
		this.date         = date;
		this.beforeSalary = beforeSalary;
		this.afterSalary  = afterSalary;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public double getBeforeSalary() {
		return this.beforeSalary;
	}

	public double getAfterSalary() {
		return this.afterSalary;
	}
}

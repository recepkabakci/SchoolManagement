package com.bilgeadam.marathon02;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public abstract class Employee extends Person {
	private String    idNumber;
	private LocalDate startDate;        // must
	private LocalDate endDate;          // sonradan belirlenebilir
	private double    startSalary;      // must
	private String[]  telephoneNumbers; // optional

	public Employee(String firstName, String lastName, double startSalary, LocalDate startDate) {
		super(firstName, lastName);
		this.startDate   = startDate;
		this.startSalary = startSalary;
		this.endDate     = null;
		this.idNumber    = this.createIdNumber();
	}

	public abstract String createIdNumber();

	public abstract double getIncreaseRate();

	private SalaryIncrease[] calculateCurrentSalary() {
		LocalDate today        = LocalDate.now();
		long      workedMonths = ChronoUnit.MONTHS.between(this.getStartDate(), today);
		long      numOf6Months = workedMonths / 6;

		SalaryIncrease[] salaryIncreases = new SalaryIncrease[(int)numOf6Months];
		
		double    salary       = this.getStartSalary();
		LocalDate increaseDate = this.startDate;
		
		for (int i = 0; i < numOf6Months; i++) {
			double newSalary = salary * (1 + this.getIncreaseRate());
			increaseDate = increaseDate.plusMonths(6);
			salaryIncreases[i] = new SalaryIncrease(increaseDate, salary, newSalary);
			salary = newSalary;
		}
		return salaryIncreases;
	}

	public String salaryHistory() {
		StringBuilder history = new StringBuilder();
		history.append("\t").append(this.idNumber).append(" - ")
				.append(this.getFirstName()).append(" ")
				.append(this.getLastName())
				.append(" - İşe Başlangıç: ").append(this.startDate)
				.append("\n");
		
		SalaryIncrease[] salaryIncreases = this.calculateCurrentSalary();
		for (int i = 0; i < salaryIncreases.length; i++) {
			
			history.append("\t\t").append(i+1).append(". Artış - ").append(salaryIncreases[i].getDate())
					.append(" - Artış Öncesi: ").append(String.format("%.2f", salaryIncreases[i].getBeforeSalary()))
					.append(" - Artış Sonrası: ").append(String.format("%.2f", salaryIncreases[i].getAfterSalary())).append("\n");
		}
		history.append("\n");
		return history.toString();
	}
	
	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setStartDate(LocalDate start) {
		this.startDate = start;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public double getStartSalary() {
		return this.startSalary;
	}

	public String[] getTelephoneNumbers() {
		return this.telephoneNumbers;
	}

	public void setHomeTelephone(String telephone) {
		this.telephoneNumbers[Commons.HOME] = telephone;
	}

	public void setGSMTelephone(String telephone) {
		this.telephoneNumbers[Commons.GSM] = telephone;
	}

	@Override
	public String toString() {
		return "Employee [idNumber=" + this.idNumber + ", startDate=" + this.startDate + ", endDate=" + this.endDate
				+ ", startSalary=" + this.startSalary + ", telephoneNumbers=" + Arrays.toString(this.telephoneNumbers)
				+ ", toString()=" + super.toString() + "]";
	}

}

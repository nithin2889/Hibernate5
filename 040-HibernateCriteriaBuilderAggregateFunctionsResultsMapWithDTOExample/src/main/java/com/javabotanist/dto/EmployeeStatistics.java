package com.javabotanist.dto;

public class EmployeeStatistics {

	private Long totalNumberOfEmployees;
	private Long distinctNumberOfEmployees;
	private Double maxSalaryOfEmployees;
	private Double avgSalaryOfEmployees;
	private Double sumSalaryOfEmployees;
	
	public EmployeeStatistics(Long totalNumberOfEmployees, Long distinctNumberOfEmployees, Double maxSalaryOfEmployees,
			Double avgSalaryOfEmployees, Double sumSalaryOfEmployees) {
		super();
		this.totalNumberOfEmployees = totalNumberOfEmployees;
		this.distinctNumberOfEmployees = distinctNumberOfEmployees;
		this.maxSalaryOfEmployees = maxSalaryOfEmployees;
		this.avgSalaryOfEmployees = avgSalaryOfEmployees;
		this.sumSalaryOfEmployees = sumSalaryOfEmployees;
	}

	public Long getTotalNumberOfEmployees() {
		return totalNumberOfEmployees;
	}

	public Long getDistinctNumberOfEmployees() {
		return distinctNumberOfEmployees;
	}

	public Double getMaxSalaryOfEmployees() {
		return maxSalaryOfEmployees;
	}

	public Double getAvgSalaryOfEmployees() {
		return avgSalaryOfEmployees;
	}

	public Double getSumSalaryOfEmployees() {
		return sumSalaryOfEmployees;
	}

	@Override
	public String toString() {
		return "EmployeeStatistics [totalNumberOfEmployees=" + totalNumberOfEmployees + ", distinctNumberOfEmployees="
				+ distinctNumberOfEmployees + ", maxSalaryOfEmployees=" + maxSalaryOfEmployees
				+ ", avgSalaryOfEmployees=" + avgSalaryOfEmployees + ", sumSalaryOfEmployees=" + sumSalaryOfEmployees
				+ "]";
	}
	
}

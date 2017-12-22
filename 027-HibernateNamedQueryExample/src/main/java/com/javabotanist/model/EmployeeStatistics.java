package com.javabotanist.model;

public class EmployeeStatistics {

	private Long empCount;
	private Double avgSal;
	private Double minSal;
	private Double maxSal;
	private Double sumSal;
	
	public EmployeeStatistics(Long empCount, Double avgSal, Double minSal, Double maxSal, Double sumSal) {
		super();
		this.empCount = empCount;
		this.avgSal = avgSal;
		this.minSal = minSal;
		this.maxSal = maxSal;
		this.sumSal = sumSal;
	}

	public Long getEmpCount() {
		return empCount;
	}

	public void setEmpCount(Long empCount) {
		this.empCount = empCount;
	}

	public Double getAvgSal() {
		return avgSal;
	}

	public void setAvgSal(Double avgSal) {
		this.avgSal = avgSal;
	}

	public Double getMinSal() {
		return minSal;
	}

	public void setMinSal(Double minSal) {
		this.minSal = minSal;
	}

	public Double getMaxSal() {
		return maxSal;
	}

	public void setMaxSal(Double maxSal) {
		this.maxSal = maxSal;
	}

	public Double getSumSal() {
		return sumSal;
	}

	public void setSumSal(Double sumSal) {
		this.sumSal = sumSal;
	}

	@Override
	public String toString() {
		return "EmployeeStatistics [empCount=" + empCount + ", avgSal=" + avgSal + ", minSal=" + minSal + ", maxSal="
				+ maxSal + ", sumSal=" + sumSal + "]";
	}
	
}

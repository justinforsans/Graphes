package org.insa.graphs.model;

public class LabelStar extends Label implements Comparable<Label>{
	private double estimatedCost;
	
	public LabelStar (int sommet, double estimatedCost) {
		super(sommet);
		this.setEstimatedCost(estimatedCost);
	}
	
	public double getEstimatedCost() {
		return estimatedCost;
	}
	
	public void setEstimatedCost (double estimatedCost) {
		this.estimatedCost=estimatedCost;
	}

	public double getTotalCost() {
		return getCout()+getEstimatedCost();
	}
}

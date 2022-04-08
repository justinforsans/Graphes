package org.insa.graphs.model;

public class Label implements Comparable<Label>{

	private int sommet;
	private boolean marque;
	private double cout = Double.POSITIVE_INFINITY;
	private Arc pere;
	
	public double getCout() {
		return cout;
	}

	public int getSommet() {
		return sommet;
	}

	public void setSommet(int sommet) {
		this.sommet = sommet;
	}

	public boolean isMarque() {
		return marque;
	}

	public void setMarque(boolean marque) {
		this.marque = marque;
	}

	public Arc getPere() {
		return pere;
	}

	public void setPere(Arc pere) {
		this.pere = pere;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

	
	public Label(int sommet) {
		super();
		this.sommet = sommet;
		this.marque = false;
	}

	@Override
	public int compareTo(Label arg0) {
		return Double.compare(cout,arg0.getCout());
	}

	

	
}

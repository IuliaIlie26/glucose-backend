package com.fils.glucose.domain.risk.factors;

public class RiskScore {

	private Double naylorScore;
	private Double caliskanScore;
	private Double vanLeeuwenScore;
	private Double teedeScore;
	private Double nandaScore;

	public RiskScore(Double naylorScore, Double caliskanScore, Double vanLeeuwenScore, Double teedeScore,
			Double nandaScore) {
		this.naylorScore = naylorScore;
		this.caliskanScore = caliskanScore;
		this.vanLeeuwenScore = vanLeeuwenScore;
		this.teedeScore = teedeScore;
		this.nandaScore = nandaScore;
	}

	public Double getNaylorScore() {
		return naylorScore;
	}

	public Double getCaliskanScore() {
		return caliskanScore;
	}

	public Double getVanLeeuwenScore() {
		return vanLeeuwenScore;
	}

	public Double getTeedeScore() {
		return teedeScore;
	}

	public Double getNandaScore() {
		return nandaScore;
	}

}
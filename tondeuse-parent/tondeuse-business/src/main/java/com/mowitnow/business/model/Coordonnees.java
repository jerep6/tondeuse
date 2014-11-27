package com.mowitnow.business.model;

import com.google.common.base.Preconditions;

public class Coordonnees {
	private Integer	x;
	private Integer	y;

	public Coordonnees(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Ajoute les coordonnées en paramètre.
	 *
	 * @param coordonneesToAdd
	 *            coordonnées à ajouter
	 * @return nouvel objet coordonnées représentant la somme des coordonnées actuel et de ceux passés en paramètre
	 */
	public Coordonnees add(Coordonnees coordonneesToAdd) {
		Preconditions.checkNotNull(coordonneesToAdd);
		return new Coordonnees(coordonneesToAdd.getX() + x, coordonneesToAdd.getY() + y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Coordonnees other = (Coordonnees) obj;
		if (x == null) {
			if (other.x != null) return false;
		} else if (!x.equals(other.x)) return false;
		if (y == null) {
			if (other.y != null) return false;
		} else if (!y.equals(other.y)) return false;
		return true;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (x == null ? 0 : x.hashCode());
		result = prime * result + (y == null ? 0 : y.hashCode());
		return result;
	}

	public Coordonnees substract(Coordonnees coordonneesToAdd) {
		Preconditions.checkNotNull(coordonneesToAdd);
		return new Coordonnees(x - coordonneesToAdd.getX(), y - coordonneesToAdd.getY());
	}

	@Override
	public String toString() {
		return "Coordonnee [x=" + x + ", y=" + y + "]";
	}

}

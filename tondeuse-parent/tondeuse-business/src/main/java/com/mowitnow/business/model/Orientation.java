package com.mowitnow.business.model;

/**
 * Orientation géographique
 *
 * @author jpinsolle
 *
 */
public enum Orientation {
	NORD("N", 0, new Coordonnees(0, 1)), //
	SUD("S", 180, new Coordonnees(0, -1)), //
	EST("E", 90, new Coordonnees(1, 0)), //
	OUEST("W", 270, new Coordonnees(-1, 0));

	public static Orientation valueOfByAngle(Integer angle) {
		for (Orientation uneEnum : Orientation.values()) {
			if (uneEnum.getAngle().equals(angle)) {
				return uneEnum;
			}
		}

		throw new IllegalArgumentException("Aucune " + Orientation.class.getSimpleName() + " pour l'angle " + angle);
	}

	public static Orientation valueOfByCode(String code) {
		for (Orientation uneEnum : Orientation.values()) {
			if (uneEnum.getCode().equals(code)) {
				return uneEnum;
			}
		}

		throw new IllegalArgumentException("Aucune " + Orientation.class.getSimpleName() + " pour le code " + code);
	}

	/** Code fonctionnel de l'orientation */
	private String		code;
	/** Angle correspondant à l'orientation */
	private Integer		angle;
	/** Vecteur à ajouter lors d'une operation de translation */
	private Coordonnees	translationUnite1;

	private Orientation(String code, Integer angle, Coordonnees translationUnite1) {
		this.code = code;
		this.angle = angle;
		this.translationUnite1 = translationUnite1;
	}

	public Integer getAngle() {
		return angle;
	}

	public String getCode() {
		return code;
	}

	public Coordonnees getTranslationUnite1() {
		return translationUnite1;
	}

	public Orientation tourner(Integer angleDeRotation) {
		Integer nouvelAngle = (angle + angleDeRotation + 360) % 360;
		return Orientation.valueOfByAngle(nouvelAngle);
	}

}

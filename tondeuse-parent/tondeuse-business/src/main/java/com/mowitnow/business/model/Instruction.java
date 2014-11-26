package com.mowitnow.business.model;

public enum Instruction {
	AVANCER("A"), //
	PIVOTER_DROITE("D"), //
	PIVOTER_GAUCHE("G");

	public static Instruction valueOfByCode(String code) {
		for (Instruction uneEnum : Instruction.values()) {
			if (uneEnum.getCode().equals(code)) {
				return uneEnum;
			}
		}

		throw new IllegalArgumentException("Aucune " + Instruction.class.getSimpleName() + " pour le code " + code);
	}

	private String	code;

	private Instruction(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}

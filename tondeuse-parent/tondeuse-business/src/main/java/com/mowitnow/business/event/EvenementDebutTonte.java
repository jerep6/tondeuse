package com.mowitnow.business.event;

import java.util.Date;

import com.mowitnow.business.model.Tondeuse;

/**
 * Evênement signifiant le début de la tonte
 *
 * @author jpinsolle
 */
public class EvenementDebutTonte {
	private Tondeuse	tondeuse;
	private Date		date;

	public EvenementDebutTonte(Tondeuse tondeuse, Date date) {
		super();
		this.tondeuse = tondeuse;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public Tondeuse getTondeuse() {
		return tondeuse;
	}

}

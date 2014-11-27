package com.mowitnow.business.event;

import java.util.Date;

import com.mowitnow.business.model.Tondeuse;

/**
 * Evênement signifiant la fin de la tonte
 *
 * @author jpinsolle
 */
public class EvenementFinTonte {
	private Tondeuse	tondeuse;
	private Date		date;

	public EvenementFinTonte(Tondeuse tondeuse, Date date) {
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

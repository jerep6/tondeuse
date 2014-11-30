package com.mowitnow.business.exception;

import com.mowitnow.business.model.Terrain;

/**
 * Exception métier indiquant que la tondeuse ne peut pas être positionnée sur le terrain car elle est déjà sur
 * un autre
 * Pour changer la tondeuse de terrain, il faut la supprimer du terrain avec la méthode
 * {@link Terrain#supprimerTondeuse(com.mowitnow.business.model.Tondeuse)}
 *
 * @author jerep6
 *
 */
public class TondeuseDejaSurTerrainException extends BusinessException {
	private static final long	serialVersionUID	= -4200045260339001675L;

}

/*
 * This file is part of EverSigns.
 *
 * EverSigns is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EverSigns is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EverSigns.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.evercraft.eversigns.service;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import fr.evercraft.everapi.EAMessage.EAMessages;
import fr.evercraft.everapi.server.player.EPlayer;
import fr.evercraft.everapi.services.signs.SignSubject;
import fr.evercraft.eversigns.ESPermissions;

public class ESign {
	
	private final SignSubject sign;

	public ESign(final SignSubject sign) {	
		this.sign = sign;
	}
	
	public String getTitle() {
		return  "[" + this.sign.getName() + "]";
	}
	
	public Text getTitleEnable() {
		return Text.builder(this.sign.getName())
					.color(SignSubject.COLOR_ENABLE)
					.build();
	}

	public Text getTitleDisable() {
		return Text.builder(this.sign.getName())
					.color(SignSubject.COLOR_DISABLE)
					.build();
	}

	public boolean createSign(EPlayer player, Sign sign) {
		if(player.hasPermission(ESPermissions.SIGN_CREATE.get() + "." + this.sign.getName())) {
			sign.getSignData().addElement(0, this.getTitleEnable());
			
			return this.sign.create(player, sign);
		} else {
			player.sendMessage(EAMessages.NO_PERMISSION.get());
		}
		return false;
	}
	
	public boolean useSign(final EPlayer player, final Sign sign) {
		if(this.sign.valide(sign)) {
			if(!isEnable(sign)) {
				sign.getSignData().addElement(0, this.getTitleEnable());
			}
			
			if(player.hasPermission(ESPermissions.SIGN_USE.get() + "." + this.sign.getName())) {
				return this.sign.useEnable(player, sign);
			} else {
				player.sendMessage(EAMessages.NO_PERMISSION.get());
			}
		} else {
			if(isEnable(sign)) {
				sign.getSignData().addElement(0, this.getTitleDisable());
			}
			
			if(player.hasPermission(ESPermissions.SIGN_USE.get() + "." + this.sign.getName())) {
				this.sign.useDisable(player, sign);
			} else {
				player.sendMessage(EAMessages.NO_PERMISSION.get());
			}
		}
		return false;
	}
	
	public boolean breakSign(EPlayer player, Location<World> location, final List<Text> sign) {
		if(player.hasPermission(ESPermissions.SIGN_BREAK.get() + "." + this.sign.getName())) {			
			return this.sign.remove(player, location, sign);
		} else {
			player.sendMessage(EAMessages.NO_PERMISSION.get());
		}
		return false;
	}
	
	public boolean isEnable(Sign sign) {
		Optional<Text> title = sign.getSignData().get(0);
		return title.isPresent() && this.getTitleEnable().equals(title.get());
	}

	public SignSubject getSubject() {
		return this.sign;
	}
}

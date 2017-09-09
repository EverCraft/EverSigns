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
package fr.evercraft.eversigns;

import org.spongepowered.api.command.CommandSource;

import com.google.common.base.Preconditions;

import fr.evercraft.everapi.plugin.EnumPermission;

public enum ESPermissions implements EnumPermission {
	EVERSIGNS("commands.execute"),
	HELP("commands.help"),
	RELOAD("commands.reload"),
	
	REPLACE_COLOR("replaces.color"),
	REPLACE_FORMAT("replaces.format"),
	REPLACE_MAGIC("replaces.magic"),
	
	SIGN_CREATE("signs.create"),
	SIGN_USE("signs.use"),
	SIGN_BREAK("signs.break");
	
	private final static String prefix = "eversigns";
	
	private final String permission;
    
    private ESPermissions(final String permission) {   	
    	Preconditions.checkNotNull(permission, "La permission '" + this.name() + "' n'est pas définit");
    	
    	this.permission = permission;
    }

    public String get() {
		return ESPermissions.prefix + "." + this.permission;
	}
    
    public boolean has(CommandSource player) {
    	return player.hasPermission(this.get());
    }
}

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

import fr.evercraft.everapi.plugin.EnumPermission;
import fr.evercraft.everapi.plugin.file.EnumMessage;
import fr.evercraft.eversigns.ESMessage.ESMessages;

public enum ESPermissions implements EnumPermission {
	EVERSIGNS("commands.execute", ESMessages.PERMISSIONS_COMMANDS_EXECUTE),
	HELP("commands.help", ESMessages.PERMISSIONS_COMMANDS_HELP),
	RELOAD("commands.reload", ESMessages.PERMISSIONS_COMMANDS_RELOAD),
	
	REPLACE_COLOR("replaces.color", ESMessages.PERMISSIONS_REPLACES_COLOR),
	REPLACE_FORMAT("replaces.format", ESMessages.PERMISSIONS_REPLACES_FORMAT),
	REPLACE_MAGIC("replaces.magic", ESMessages.PERMISSIONS_REPLACES_MAGIC),
	
	SIGN_CREATE("signs.create", ESMessages.PERMISSIONS_SIGNS_CREATE),
	SIGN_USE("signs.use", ESMessages.PERMISSIONS_SIGNS_USE),
	SIGN_BREAK("signs.break", ESMessages.PERMISSIONS_SIGNS_BREAK);
	
	private static final String PREFIX = "eversigns";
	
	private final String permission;
	private final EnumMessage message;
	private final boolean value;
    
    private ESPermissions(final String permission, final EnumMessage message) {
    	this(permission, message, false);
    }
    
    private ESPermissions(final String permission, final EnumMessage message, final boolean value) {   	    	
    	this.permission = PREFIX + "." + permission;
    	this.message = message;
    	this.value = value;
    }

    @Override
    public String get() {
		return this.permission;
	}

	@Override
	public boolean getDefault() {
		return this.value;
	}

	@Override
	public EnumMessage getMessage() {
		return this.message;
	}
}

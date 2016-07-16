package fr.evercraft.eversigns;

import org.spongepowered.api.command.CommandSource;

import com.google.common.base.Preconditions;

import fr.evercraft.everapi.plugin.EnumPermission;

public enum ESPermissions implements EnumPermission {
	EVERSIGNS("command"),
	
	HELP("help"),
	RELOAD("reload"),
	COLOR("color"),
	FORMAT("format"),
	MAGIC("magic");
	
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

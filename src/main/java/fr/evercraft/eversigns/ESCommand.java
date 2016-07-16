package fr.evercraft.eversigns;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import fr.evercraft.everapi.plugin.command.EParentCommand;
import fr.evercraft.eversigns.ESMessage.EKMessages;

public class ESCommand extends EParentCommand<EverSigns> {
	
	public ESCommand(final EverSigns plugin) {
        super(plugin, "eversigns");
    }
	
	@Override
	public boolean testPermission(final CommandSource source) {
		return source.hasPermission(ESPermissions.EVERSIGNS.get());
	}

	@Override
	public Text description(final CommandSource source) {
		return EKMessages.DESCRIPTION.getText();
	}

	@Override
	public boolean testPermissionHelp(final CommandSource source) {
		return source.hasPermission(ESPermissions.HELP.get());
	}
}

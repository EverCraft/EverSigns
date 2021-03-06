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

import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import fr.evercraft.everapi.EverAPI;
import fr.evercraft.everapi.exception.PluginDisableException;
import fr.evercraft.everapi.exception.ServerDisableException;
import fr.evercraft.everapi.plugin.EPlugin;
import fr.evercraft.everapi.services.sign.SignService;
import fr.evercraft.eversigns.command.sub.ESReload;
import fr.evercraft.eversigns.service.ESignService;

@Plugin(id = "eversigns", 
		name = "EverSigns", 
		version = EverAPI.VERSION, 
		description = "Manage Signs",
		url = "http://evercraft.fr/",
		authors = {"rexbut"},
		dependencies = {
		    @Dependency(id = "everapi", version = EverAPI.VERSION),
		    @Dependency(id = "spongeapi", version = EverAPI.SPONGEAPI_VERSION)
		})
public class EverSigns extends EPlugin<EverSigns> {
	private ESConfig configs;
	private ESMessage messages;
	
	private ESignService service;
	
	@Override
	protected void onPreEnable() {		
		this.configs = new ESConfig(this);
		this.messages = new ESMessage(this);
		
		this.service = new ESignService(this);
		this.getGame().getServiceManager().setProvider(this, SignService.class, this.service);
		
		this.getGame().getEventManager().registerListeners(this, new ESListener(this));
	}
	
	@Override
	protected void onCompleteEnable() {
		ESCommand command = new ESCommand(this);
		
		command.add(new ESReload(this, command));
	}

	protected void onReload() throws PluginDisableException, ServerDisableException {
		super.onReload();
	}
	
	protected void onDisable() {
	}

	/*
	 * Accesseurs
	 */
	
	public ESMessage getMessages(){
		return this.messages;
	}
	
	public ESConfig getConfigs() {
		return this.configs;
	}
	
	public ESPermissions[] getPermissions() {
		return ESPermissions.values();
	}
	
	public ESignService getService() {
		return this.service;
	}
}

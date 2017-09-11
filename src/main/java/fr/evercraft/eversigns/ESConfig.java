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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.evercraft.everapi.plugin.file.EConfig;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;

public class ESConfig extends EConfig<EverSigns> {

	public ESConfig(final EverSigns plugin) {
		super(plugin);
	}
	
	public void reload() {
		super.reload();
		this.plugin.getELogger().setDebug(this.isDebug());
	}
	
	@Override
	public void loadDefault() {
		this.configDefault();
		
		
		// Icons
		addDefault("icons-enable", true);
		
		
		// Replaces
		ConfigurationNode replaces = this.get("replaces");
		if (replaces.getValue() == null) {
			replaces.getNode("[<3]").setValue("\u2764");
			replaces.getNode("[check]").setValue("\u2714");
			replaces.getNode("[*]").setValue("\u2716");
			replaces.getNode("[ARROW]").setValue("\u279c");
			replaces.getNode("[X]").setValue("\u2588");
			replaces.getNode("[RT]").setValue("\n");
		}
		
		// Format
		addDefault("format.enable", true);
		addDefault("format.default", "<DISPLAYNAME_FORMAT> &7:&f <MESSAGE>");
		ConfigurationNode formats = this.get("format.groups");
		if (formats.isVirtual()) {
			formats.getNode("Admin").setValue("&f[&4Admin&f] <DISPLAYNAME_FORMAT> &7:&f <MESSAGE>");
			formats.getNode("Moderator").setValue("&f[&5Moderator&f] <DISPLAYNAME_FORMAT> &7:&f <MESSAGE>");
		}
	}
	
	public Map<String, String> getReplaces() {
		Map<String, String> replaces = new HashMap<String, String>();
		for (Entry<Object, ? extends CommentedConfigurationNode> node : this.get("replaces").getChildrenMap().entrySet()) {
			if (node.getKey() instanceof String) {
				replaces.put((String) node.getKey(), node.getValue().getString(""));
			}
		}
		return replaces;
	}
	
	public Map<String, String> getFormatGroups() {
		Map<String, String> replaces = new HashMap<String, String>();
		for (Entry<Object, ? extends CommentedConfigurationNode> node : this.get("format.groups").getChildrenMap().entrySet()) {
			String value = node.getValue().getString(null);
			if (node.getKey() instanceof String && value != null) {
				replaces.put((String) node.getKey(), value);
			}
		}
		return replaces;
	}
	
	public String getFormatDefault() {
		return this.get("format.default").getString("<<NAME>> <MESSAGE>");
	}
	
	public boolean enableFormat() {
		return this.get("format.enable").getBoolean(true);
	}
	
	public boolean enableIcons() {
		return this.get("enable-icons").getBoolean(true);
	}
}

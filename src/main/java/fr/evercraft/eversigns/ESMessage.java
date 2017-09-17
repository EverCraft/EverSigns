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

import com.google.common.base.Preconditions;

import fr.evercraft.everapi.message.EMessageBuilder;
import fr.evercraft.everapi.message.EMessageFormat;
import fr.evercraft.everapi.message.format.EFormatString;
import fr.evercraft.everapi.plugin.file.EMessage;
import fr.evercraft.everapi.plugin.file.EnumMessage;

public class ESMessage extends EMessage<EverSigns> {

	public ESMessage(final EverSigns plugin) {
		super(plugin, ESMessages.values());
	}
	
	public enum ESMessages implements EnumMessage {
		PREFIX("PREFIX", 				"[&4Ever&6&lKits&f] "),
		DESCRIPTION("DESCRIPTION",		"Gestionnaire des panneaux"),
				
		SIGN_CREATE("signCreate",		"&7Panneau crée avec succès."),
		SIGN_DISABLE("signDisable",    "&cSe panneau est désactivé."),
				
		BALANCE_PLAYER("balancePlayer", "&7Vous avez actuellement &6{solde} {symbol}&7.", "&7Balance : &6{solde} {symbol}"),
		
		PERMISSIONS_COMMANDS_EXECUTE("permissionsCommandsExecute", ""),
		PERMISSIONS_COMMANDS_HELP("permissionsCommandsHelp", ""),
		PERMISSIONS_COMMANDS_RELOAD("permissionsCommandsReload", ""),
		PERMISSIONS_REPLACES_COLOR("permissionsReplacesColor", ""),
		PERMISSIONS_REPLACES_FORMAT("permissionsReplacesFormat", ""),
		PERMISSIONS_REPLACES_MAGIC("permissionsReplacesMagic", ""),
		PERMISSIONS_SIGNS_CREATE("permissionsSignsCreate", ""),
		PERMISSIONS_SIGNS_USE("permissionsSignsUse", ""),
		PERMISSIONS_SIGNS_BREAK("permissionsSignsBreak", "");
		
		private final String path;
	    private final EMessageBuilder french;
	    private final EMessageBuilder english;
	    private EMessageFormat message;
	    private EMessageBuilder builder;
	    
	    private ESMessages(final String path, final String french) {   	
	    	this(path, EMessageFormat.builder().chat(new EFormatString(french), true));
	    }
	    
	    private ESMessages(final String path, final String french, final String english) {   	
	    	this(path, 
	    		EMessageFormat.builder().chat(new EFormatString(french), true), 
	    		EMessageFormat.builder().chat(new EFormatString(english), true));
	    }
	    
	    private ESMessages(final String path, final EMessageBuilder french) {   	
	    	this(path, french, french);
	    }
	    
	    private ESMessages(final String path, final EMessageBuilder french, final EMessageBuilder english) {
	    	Preconditions.checkNotNull(french, "Le message '" + this.name() + "' n'est pas définit");
	    	
	    	this.path = path;	    	
	    	this.french = french;
	    	this.english = english;
	    	this.message = french.build();
	    }

	    public String getName() {
			return this.name();
		}
	    
		public String getPath() {
			return this.path;
		}

		public EMessageBuilder getFrench() {
			return this.french;
		}

		public EMessageBuilder getEnglish() {
			return this.english;
		}
		
		public EMessageFormat getMessage() {
			return this.message;
		}
		
		public EMessageBuilder getBuilder() {
			return this.builder;
		}
		
		public void set(EMessageBuilder message) {
			this.message = message.build();
			this.builder = message;
		}
	}
}

package fr.evercraft.eversigns;

import java.util.Optional;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.tileentity.SignData;
import org.spongepowered.api.data.value.mutable.ListValue;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.tileentity.ChangeSignEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;

import fr.evercraft.everapi.plugin.EChat;

public class ESListener {
	private EverSigns plugin;
	
	public ESListener(final EverSigns plugin) {
		this.plugin = plugin;
	}
	
	@Listener
	public void onSignChange(ChangeSignEvent event, @First Player player) {
		SignData signData = event.getText();
		Optional<ListValue<Text>> value = signData.getValue(Keys.SIGN_LINES);
		if (value.isPresent()) {
			String line_0 = value.get().get(0).toPlain();
			String line_1 = value.get().get(1).toPlain();
			String line_2 = value.get().get(2).toPlain();
			String line_3 = value.get().get(3).toPlain();
			
			if(!player.hasPermission(ESPermissions.COLOR.get())) {
				line_0 = line_0.replaceAll(EChat.REGEX_COLOR, "");
				line_1 = line_1.replaceAll(EChat.REGEX_COLOR, "");
				line_2 = line_2.replaceAll(EChat.REGEX_COLOR, "");
				line_3 = line_3.replaceAll(EChat.REGEX_COLOR, "");
			}
			if(!player.hasPermission(ESPermissions.FORMAT.get())) {
				line_0 = line_0.replaceAll(EChat.REGEX_FORMAT, "");
				line_1 = line_1.replaceAll(EChat.REGEX_FORMAT, "");
				line_2 = line_2.replaceAll(EChat.REGEX_FORMAT, "");
				line_3 = line_3.replaceAll(EChat.REGEX_FORMAT, "");
			}
			if(!player.hasPermission(ESPermissions.MAGIC.get())) {
				line_0 = line_0.replaceAll(EChat.REGEX_MAGIC, "");
				line_1 = line_1.replaceAll(EChat.REGEX_MAGIC, "");
				line_2 = line_2.replaceAll(EChat.REGEX_MAGIC, "");
				line_3 = line_3.replaceAll(EChat.REGEX_MAGIC, "");
			}
			
			signData = signData.set(value.get().set(0, EChat.of(line_0)));
			signData = signData.set(value.get().set(1, EChat.of(line_1)));
			signData = signData.set(value.get().set(2, EChat.of(line_2)));
			signData = signData.set(value.get().set(3, EChat.of(line_3)));
		}
	}
}

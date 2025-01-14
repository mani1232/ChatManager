package me.h1dd3nxn1nja.chatmanager.managers;

import me.clip.placeholderapi.PlaceholderAPI;
import me.h1dd3nxn1nja.chatmanager.ChatManager;
import me.h1dd3nxn1nja.chatmanager.Methods;
import me.h1dd3nxn1nja.chatmanager.SettingsManager;
import me.h1dd3nxn1nja.chatmanager.support.EssentialsSupport;
import me.h1dd3nxn1nja.chatmanager.support.PluginSupport;
import me.h1dd3nxn1nja.chatmanager.support.misc.VaultSupport;
import org.bukkit.entity.Player;
import java.text.DecimalFormat;
import java.util.UUID;

public class PlaceholderManager {

	private final ChatManager plugin = ChatManager.getPlugin();

	private final SettingsManager settingsManager = plugin.getSettingsManager();

	private final VaultSupport vaultSupport = plugin.getPluginManager().getVaultSupport();

	private final EssentialsSupport essentialsSupport = plugin.getPluginManager().getEssentialsSupport();

	public String setPlaceholders(Player player, String placeholders) {
		DecimalFormat df = new DecimalFormat("#,###");
		
		UUID uuid = player.getUniqueId();

		placeholders = Methods.color(uuid, placeholders.replace("{player}", player.getName()));
		placeholders = Methods.color(uuid, placeholders.replace("{Prefix}", settingsManager.getMessages().getString("Message.Prefix")));
		placeholders = Methods.color(uuid, placeholders.replace("{prefix}", settingsManager.getMessages().getString("Message.Prefix")));
		placeholders = Methods.color(uuid, placeholders.replace("{display_name}", player.getDisplayName()));
		placeholders = Methods.color(uuid, placeholders.replace("{displayname}", player.getDisplayName()));
		placeholders = Methods.color(uuid, placeholders.replace("{world}", player.getLocation().getWorld().getName()));
		placeholders = Methods.color(uuid, placeholders.replace("{online}", df.format(plugin.getServer().getOnlinePlayers().size())));
		placeholders = Methods.color(uuid, placeholders.replace("{server_online}", df.format(plugin.getServer().getOnlinePlayers().size())));
		placeholders = Methods.color(uuid, placeholders.replace("{server_max_players}", df.format(plugin.getServer().getMaxPlayers())));
		placeholders = Methods.color(uuid, placeholders.replace("{server_name}", settingsManager.getConfig().getString("Server_Name")));

		if (PluginSupport.VAULT.isPluginEnabled()) {
			placeholders = Methods.color(uuid, placeholders.replace("{vault_prefix}", vaultSupport.getPlayerPrefix(player)));
			placeholders = Methods.color(uuid, placeholders.replace("{vault_suffix}", vaultSupport.getPlayerSuffix(player)));
		}

		if (PluginSupport.ESSENTIALS.isPluginEnabled()) {
			placeholders = Methods.color(uuid, placeholders.replace("{ess_player_balance}", essentialsSupport.getPlayerBalance(player)));
			placeholders = Methods.color(uuid, placeholders.replace("{ess_player_nickname}", essentialsSupport.getPlayerNickname(player)));
		}

		if (PluginSupport.PLACEHOLDERAPI.isPluginEnabled()) placeholders = PlaceholderAPI.setPlaceholders(player, placeholders);

		return placeholders;
	}
}
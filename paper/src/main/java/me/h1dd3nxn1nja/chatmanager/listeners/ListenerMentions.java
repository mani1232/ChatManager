package me.h1dd3nxn1nja.chatmanager.listeners;

import com.ryderbelserion.chatmanager.api.Universal;
import me.h1dd3nxn1nja.chatmanager.support.PluginSupport;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.h1dd3nxn1nja.chatmanager.Methods;

public class ListenerMentions implements Listener, Universal {

	@EventHandler(ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		String tagSymbol = config.getString("Mentions.Tag_Symbol");
		String mentionColor = config.getString("Mentions.Mention_Color");

		if (!config.getBoolean("Mentions.Enable")) return;

		event.setMessage(Methods.color(event.getMessage()));

		plugin.getServer().getOnlinePlayers().forEach(target -> {
			if (!player.hasPermission("chatmanager.mention") || !target.hasPermission("chatmanager.mention.receive")) return;

			if (!event.getMessage().contains(tagSymbol + target.getName())) return;

			if (plugin.api().getToggleMentionsData().containsUser(target.getUniqueId())) return;

			if (PluginSupport.ESSENTIALS.isPluginEnabled()) {
				if (essentialsSupport.isIgnored(target, player) || essentialsSupport.isMuted(player)) return;
			}

			if (plugin.api().getToggleChatData().containsUser(target.getUniqueId())) return;

			if (config.getBoolean("Chat_Radius.Enable")) {
				if ((!Methods.inRange(target.getUniqueId(), player.getUniqueId(), config.getInt("Chat_Radius.Block_Distance"))) || (!Methods.inWorld(target.getUniqueId(), player.getUniqueId()))) return;
			}

			if (plugin.api().getToggleMentionsData().containsUser(target.getUniqueId())) {
				try {
					target.playSound(target.getLocation(), Sound.valueOf(config.getString("Mentions.Sound")), 10, 1);
				} catch (IllegalArgumentException ignored) {}
			}

			if (config.getBoolean("Mentions.Title.Enable")) {
				String header = placeholderManager.setPlaceholders(player, config.getString("Mentions.Title.Header"));
				String footer = placeholderManager.setPlaceholders(player, config.getString("Mentions.Title.Footer"));

				target.sendTitle(header, footer, 40, 20, 40);
			}

			if (!config.getString("Mentions.Mention_Color").equals("")) {
				String before = event.getMessage();
				String lastColor = ChatColor.getLastColors(before).equals("") ? ChatColor.WHITE.toString() : ChatColor.getLastColors(before);
				event.setMessage(event.getMessage().replace(tagSymbol + ChatColor.stripColor(target.getName()), Methods.color(mentionColor + tagSymbol + ChatColor.stripColor(target.getName())) + lastColor));
			}
		});

		if (event.getMessage().toLowerCase().contains(tagSymbol + "everyone")) {
			plugin.getServer().getOnlinePlayers().forEach(target -> {
				if (player.hasPermission("chatmanager.mention.everyone") && target.hasPermission("chatmanager.mentions.receive")) {
					if (plugin.api().getToggleMentionsData().containsUser(target.getUniqueId())) {
						try {
							target.playSound(target.getLocation(), Sound.valueOf(config.getString("Mentions.Sound")), 10, 1);
						} catch (IllegalArgumentException ignored) {}
					}

					if (config.getBoolean("Mentions.Title.Enable")) {
						String header = placeholderManager.setPlaceholders(player, config.getString("Mentions.Title.Header"));
						String footer = placeholderManager.setPlaceholders(player, config.getString("Mentions.Title.Footer"));

						target.sendTitle(header, footer, 40, 20, 40);
					}

					if (!config.getString("Mentions.Mention_Color").equals("")) {
						String before = event.getMessage();
						String lastColor = ChatColor.getLastColors(before).equals("") ? ChatColor.WHITE.toString() : ChatColor.getLastColors(before);
						event.setMessage(event.getMessage().replace(tagSymbol + ChatColor.stripColor("everyone"), Methods.color(mentionColor + tagSymbol + ChatColor.stripColor("everyone")) + lastColor));
					}
				}
			});
		}
	}
}
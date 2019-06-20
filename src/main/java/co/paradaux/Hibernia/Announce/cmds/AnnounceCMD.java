package co.paradaux.Hibernia.Announce.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.clip.placeholderapi.PlaceholderAPI;

public class AnnounceCMD implements CommandExecutor {

	/*
	 * @author: ParadauxDev (Rían Errity)
	 * @license: MIT
	 * @comments: I made this for any aspiring or intermediate developers
	 * to decompile this, take it and improve on it. This entire plugin was made in the space of
	 * 3 hours to test my own ability and to fulfill a request.
	 */

	Plugin plugin;
	FileConfiguration Configuration;

	public AnnounceCMD (FileConfiguration Configuration, Plugin plugin) {
		this.Configuration = Configuration;
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


		final String announcement = String.join(" ", args);

		if (!sender.hasPermission("hiberniaannounce.announce"))
			sender.sendMessage("You do not have sufficient permissions to announce in chat.");

		if(Configuration.getBoolean("broadcast.enabled")) {

			String broadcast = Configuration.getString("broadcast.format");
			broadcast = broadcast.replace("%message-prefix%", Configuration.getString("placeholders.message-prefix")).replace("%announcement%", announcement);
			if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
				broadcast = PlaceholderAPI.setPlaceholders((Player) sender, broadcast);

			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', broadcast));

		}

		if (Configuration.getBoolean("title.enabled")) {


			String title = Configuration.getString("title.title-format");
			title = title.replace("%message-prefix%", Configuration.getString("placeholders.message-prefix")).replace("%announcement%", announcement);
			String subtitle = Configuration.getString("title.subtitle-format");;
			subtitle = subtitle.replace("%message-prefix%", Configuration.getString("placeholders.message-prefix")).replace("%announcement%", announcement);

			for(final Player p : Bukkit.getOnlinePlayers()) {
				if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
					title = PlaceholderAPI.setPlaceholders(p, title);
					subtitle = PlaceholderAPI.setPlaceholders(p, subtitle);
				}

				p.sendMessage(announcement);
				p.sendTitle(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), Configuration.getInt("title.fadein"), Configuration.getInt("title.stay"), Configuration.getInt("title.fadeout"));
			}
		}


		return true;
	}

}


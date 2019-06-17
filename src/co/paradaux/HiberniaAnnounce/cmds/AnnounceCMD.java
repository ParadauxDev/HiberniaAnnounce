package co.paradaux.HiberniaAnnounce.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class AnnounceCMD implements CommandExecutor {

	/*
	 * @author: ParadauxDev (Rían Errity)
	 * @license: MIT
	 * @comments: I made this for any aspiring or intermediate developers
	 * to decompile this, take it and improve on it. This entire plugin was made in the space of
	 * 3 hours to test my own ability and to fulfill a request.
	 */

	FileConfiguration Configuration;

	public AnnounceCMD (FileConfiguration Configuration) {
		this.Configuration = Configuration;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final String announcement = String.join(" ", args);

		if (!sender.hasPermission("hiberniaannounce.announce"))
			sender.sendMessage("You do not have sufficient permissions to announce in chat.");

		if(Configuration.getBoolean("broadcast.enabled")) {

			String broadcast = Configuration.getString("broadcast.format");
			broadcast = broadcast.replace("%message-prefix%", Configuration.getString("placeholders.message-prefix")).replace("%announcement%", announcement);
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', broadcast));

		}

		System.out.println("Hi there 1");
		if (Configuration.getBoolean("title.enabled")) {
			System.out.println("Hi there 2");
			String title = Configuration.getString("title.title-format");
			title = title.replace("%message-prefix%", Configuration.getString("placeholders.message-prefix")).replace("%announcement%", announcement);
			String subtitle = Configuration.getString("title.subtitle-format");;
			subtitle = subtitle.replace("%message-prefix%", Configuration.getString("placeholders.message-prefix")).replace("%announcement%", announcement);

			System.out.println(title);
			System.out.println(subtitle);
			System.out.println();
			System.out.println();
			System.out.println();
			for(final Player p : Bukkit.getOnlinePlayers()) {
				System.out.println("Hi there 3");
				p.sendMessage(announcement);
				p.sendTitle(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), Configuration.getInt("title.fadein"), Configuration.getInt("title.stay"), Configuration.getInt("title.fadeout"));
			}
		}


		return true;
	}

}

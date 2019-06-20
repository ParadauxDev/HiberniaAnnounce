package co.paradaux.Hibernia.Announce;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import co.paradaux.Hibernia.Announce.cmds.AnnounceCMD;

public class HiberniaAnnounce extends JavaPlugin {

	/*
	 * @author: ParadauxDev (Rían Errity)
	 * @license: MIT
	 * @comments: I made this for any aspiring or intermediate developers
	 * to decompile this, take it and improve on it. This entire plugin was made in the space of
	 * 3 hours to test my own ability and to fulfill a request.
	 */

	@Override
	public void onEnable() {

		this.getConfig().options().copyDefaults();
		saveDefaultConfig();


		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
			getLogger().info("[HiberniaAnnounce] PlaceholderAPI found. Enabling support.");

		if (Bukkit.getPluginManager().getPlugin("HiberniaDiscord") != null)
			getLogger().info("[HiberniaAnnounce] HiberniaDiscord found. Enabling support.");

		// Get the config file

		final FileConfiguration Configuration = getConfig();

		// Load the command.
		getCommand("announce").setExecutor(new AnnounceCMD(Configuration, this));

		// Print to console that the plugin actually loaded.
		getLogger().info("[HiberniaAnnounce] HiberniaAnnounce has loaded.");
	}




}


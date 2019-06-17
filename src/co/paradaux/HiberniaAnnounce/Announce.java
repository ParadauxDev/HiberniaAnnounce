package co.paradaux.HiberniaAnnounce;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import co.paradaux.HiberniaAnnounce.cmds.AnnounceCMD;


public class Announce extends JavaPlugin {

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

		// Get the config file

		final FileConfiguration Configuration = getConfig();

		// Load the command.
		getCommand("announce").setExecutor(new AnnounceCMD(Configuration));

		// Print to console that the plugin actually loaded.
		getLogger().info("[HiberniaAnnounce] HiberniaAnnounce has loaded.");
	}




}

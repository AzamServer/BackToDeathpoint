
/*
File Name: Variables.java
Part of package: com.azamserver.backtodeathpoint
Description: This file stores all the variables needed across multiple files
*/

// Declare package name
package com.azamserver.backtodeathpoint;

// Import all needed libraries
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import java.util.ArrayList;

// Start java class
public class Variables
{
    // Create an ArrayList variable to store player's death coordinates
    public static final ArrayList<Location> locations = new ArrayList<Location>();

    // Create an ArrayList variable to store player's death world
    public static final ArrayList<World> worlds = new ArrayList<World>();

    // Create an ArrayList variable to store player's IGN
    public static final ArrayList<String> playerList = new ArrayList<String>();

    // Create a String variable to store every user-read message's start to avoid code repetition
    public static final String messageStart = "" + ChatColor.BOLD + "" + ChatColor.GREEN + "[" + ChatColor.RED + "BackToDeathpoint" + ChatColor.GREEN + "]: ";
}


/*
File Name: Events.java
Part of package: com.azamserver.backtodeathpoint
Description: This file alerts the plugin what to do on a player's death and respawn
*/

// Declare package name
package com.azamserver.backtodeathpoint;

// Import all needed libraries
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

// Start java class
public class Events implements Listener
{

    // Run code on a player's death
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        // Check if player has died before and if their death location and IGN is stored
        if(Variables.playerList.contains(event.getEntity().getName()))
        {
            // If player has died before and if their death location is stored, delete old death location and IGN
            Variables.locations.remove(Variables.playerList.indexOf(event.getEntity().getName()));
            Variables.worlds.remove(Variables.playerList.indexOf(event.getEntity().getName()));
            Variables.playerList.remove(event.getEntity().getName());

            // Save new death location and IGN
            saveDeathLocation(event.getEntity());
        }
        else
        {
            // If player has died before and if their death location is stored, save new death location and IGN
            saveDeathLocation(event.getEntity());
        }
    }

    // Run code on a player's respawn
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        // Check if player has permission to use "/back" command
        if(event.getPlayer().isOp() || event.getPlayer().hasPermission("back.back"))
        {
            // If player has permission to use "/back" command, alert player that they can use the "/back" command
            event.getPlayer().sendMessage(Variables.messageStart + "We see that you have died :(");
            event.getPlayer().sendMessage(Variables.messageStart + "Oh whats that? You want to go back to where you died?");
            event.getPlayer().sendMessage(Variables.messageStart + "Just use the command \"/back\" to go back to where you previously died");
        }
    }

    // This method will save a player's death location and their IGN
    private void saveDeathLocation(Player player)
    {
        // Save a specified player's death location and their IGN
        Variables.locations.add(player.getLocation());
        Variables.worlds.add(player.getWorld());
        Variables.playerList.add(player.getName());
    }
}

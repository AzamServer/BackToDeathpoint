
/*
File Name: Back.java
Part of package: com.azamserver.backtodeathpoint
Description: This file alerts the plugin on what to do when a player issues the command "/back"
*/

// Declare package name
package com.azamserver.backtodeathpoint.Commands;

// Import all needed libraries
import com.azamserver.backtodeathpoint.Main;
import com.azamserver.backtodeathpoint.Variables;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import java.io.File;
import static org.bukkit.Bukkit.getServer;

// Start java class
public class Back implements CommandExecutor
{
    // Declare all needed variables
    private final Main main;

    // This constructor allows Main.java to access the command "/back"
    public Back(Main main)
    {
        // Set all needed variables
        this.main = main;
    }

    // This method alerts the plugins on what to do when the command "/back" is run
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        // Check if "/back" command has arguments
        if(args.length == 0)
        {
            // If "/back" command does not have arguments, check who sent command
            if(sender instanceof ConsoleCommandSender)
            {
                // If command was sent by console, alert console that command cannot be executed
                sender.sendMessage(Variables.messageStart + "Console cannot execute this command");
                return true;
            }
            else if(sender.isOp() || sender.hasPermission("back.back"))
            {
                // If "/xptransfer" command was sent by an entity that has correct perms, check if command sender has stored death location
                if(Variables.playerList.contains(sender.getName()))
                {
                    // If command sender has stored death location, if command sender's death world still exists
                    if((new File(getServer().getWorldContainer(), Variables.worlds.get(Variables.playerList.indexOf(sender.getName())).getName())).exists())
                    {
                        // If command sender's death world still exists, load command sender's death world
                        new WorldCreator(Variables.worlds.get(Variables.playerList.indexOf(sender.getName())).getName()).createWorld();

                        // Teleport command sender to death world
                        ((Player)sender).teleport(new Location(Variables.worlds.get(Variables.playerList.indexOf(sender.getName())), Variables.locations.get(Variables.playerList.indexOf(sender.getName())).getX(), Variables.locations.get(Variables.playerList.indexOf(sender.getName())).getY(), Variables.locations.get(Variables.playerList.indexOf(sender.getName())).getZ()));

                        // Alert command sender that they have been teleported to death location
                        sender.sendMessage(Variables.messageStart + "You have successfully been teleported back to your death location");

                        // Remove command sender's death location from database
                        Variables.locations.remove(Variables.playerList.indexOf(sender.getName()));
                        Variables.worlds.remove(Variables.playerList.indexOf(sender.getName()));
                        Variables.playerList.remove(sender.getName());
                        return true;
                    }
                    else
                    {
                        // If command sender's death world no longer exists, alert command sender that their death world no longer exists
                        sender.sendMessage(Variables.messageStart + "The world in which you died in no longer exists");
                        sender.sendMessage(Variables.messageStart + "You cannot teleport to your previous death location anymore");
                        return true;
                    }
                }
                else
                {
                    // If command sender does not have a stored death location, alert them that they do not have a stored death location
                    sender.sendMessage(Variables.messageStart + "You do not have a saved death location");
                    sender.sendMessage(Variables.messageStart + "Please make sure you have died before running this command");
                    return true;
                }
            }
            else
            {
                // If "/xptransfer" command was sent by an entity that does not have the correct perms, alert command sender that they do not have the correct perms
                sender.sendMessage(Variables.messageStart + "You do not have the permission to run this command");
                return true;
            }
        }
        else
        {
            // If "/back" command has arguments, check who sent command
            if(sender instanceof ConsoleCommandSender || sender.isOp() || sender.hasPermission("back.helpMenu"))
            {
                // If command sender does have perms to run command, display a help menu
                sender.sendMessage(Variables.messageStart + "Help Menu");
                sender.sendMessage(Variables.messageStart + "/back : Teleport back to where you previously died");
                sender.sendMessage(Variables.messageStart + "/back help : Displays current help menu");
                return true;
            }
            else
            {
                // If command sender does not have perms to run command, alert the command sender that they do not have the correct perms to execute the command
                sender.sendMessage(Variables.messageStart + "You do not have the permission to run this command");
                return true;
            }
        }
    }
}

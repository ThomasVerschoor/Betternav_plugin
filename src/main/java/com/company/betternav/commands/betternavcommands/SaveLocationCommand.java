package com.company.betternav.commands.betternavcommands;

import com.company.betternav.commands.BetterNavCommand;
import com.company.betternav.util.FileHandler;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import static java.lang.Double.parseDouble;

public class SaveLocationCommand extends BetterNavCommand {

    private final FileHandler fileHandler;

    public SaveLocationCommand(FileHandler fileHandler)
    {
        this.fileHandler = fileHandler;
    }

    @Override
    public boolean execute(Player player, Command cmd, String s, String[] args) {
        // if location provided
        if (args.length == 1) {
            try {
                String location = args[0];

                fileHandler.writeFile(location,player);


            } catch (IllegalArgumentException e) {
                player.sendMessage("§c§l(!) §cThat is not a valid entity!");
            }
        } else if (args.length > 1) {
            try {

                // get the location name
                String location = args[0];

                String X = args[1];
                String Y = args[2];
                String Z = args[3];

                Location saveloc = new Location(player.getWorld(),parseDouble(X),parseDouble(Y),parseDouble(Z));



                player.sendMessage("§c§l(!) §c Location " + location + " saved on: " +X+ " "+Y+" "+Z);
                fileHandler.writeLocationFile(location,player,saveloc);


            } catch (IllegalArgumentException e) {
                player.sendMessage("§c§l(!) §cThat is not a valid entity!");
            }

        } else {
            player.sendMessage("§c§l(!) §cusage ");
            player.sendMessage("§c§l(!) §c/savelocation <name of your location> ");
            player.sendMessage("§c§l(!) §c/savelocation <name of your location> <X coordinate> <Z coordinate> ");
        }
        return true;
    }
}

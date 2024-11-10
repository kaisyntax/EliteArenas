package com.kaisyntax.elitearenas.commands;

import com.kaisyntax.elitearenas.ArenaCommand;
import com.kaisyntax.elitearenas.EliteArenas;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmCommand extends ArenaCommand {

    public static Map<CommandSender, Runnable> confirmTasks = new HashMap<>();
    public static Map<Integer, Runnable> confirmTasksID = new HashMap<>();

    public ConfirmCommand() {
        super("confirm", "Confirm an action", "/arena confirm", new String[0]);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() == 0) {
            if (!confirmTasks.containsKey(sender)) {
                sender.sendMessage(EliteArenas.PREFIX + ChatColor.RED + " There is nothing to confirm!");
                return;
            }

            Runnable runnable = confirmTasks.get(sender);
            runnable.run();
            confirmTasks.remove(sender);
        } else {
            try {
                int id = Integer.parseInt(args.get(0), 16);
                if (confirmTasksID.containsKey(id)) {
                    Runnable runnable = confirmTasksID.get(id);
                    runnable.run();
                    confirmTasksID.remove(id);
                } else {
                    sender.sendMessage(EliteArenas.PREFIX + ChatColor.RED + " You must run the command again and run it faster!");
                    return;
                }
            } catch (Exception e) {
                sender.sendMessage(EliteArenas.PREFIX + ChatColor.RED + " There is nothing to confirm!");
                return;
            }
        }
    }

    @Override
    public boolean isHidden() {
        return true;
    }
}
package com.elitearenas.commands;

import com.elitearenas.Arena;
import com.elitearenas.ArenaCommand;
import com.elitearenas.EliteArenas;
import android.util.Log;
import java.util.List;

public class DeleteCommand extends ArenaCommand {

    public DeleteCommand() {
        super("remove", "Delete an arena", "/arena remove <arena>");
    }

    @Override
    public void execute(String sender, List<String> args) {
        if (args.isEmpty()) {
            Log.d("EliteArenas", "You must provide an arena name! Usage is /arena remove <arena>");
            return;
        }

        String arenaName = args.get(0);
        Arena arena = EliteArenas.getArenaByName(arenaName);
        if (arena == null) {
            Log.d("EliteArenas", "Arena not found! Use /arena list");
            return;
        }

        Log.d("EliteArenas", "Are you sure you want to delete this arena? This action cannot be undone!");
        // Note: Implement a confirmation mechanism in your UI to handle this.
    }
}
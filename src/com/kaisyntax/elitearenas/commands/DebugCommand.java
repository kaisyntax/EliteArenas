package com.elitearenas.commands;

import com.elitearenas.Arena;
import com.elitearenas.ArenaCommand;
import com.elitearenas.EliteArenas;
import com.elitearenas.Section;
import com.elitearenas.blockentity.Wrapper;
import android.util.Log;
import java.util.List;

public class DebugCommand extends ArenaCommand {

    public DebugCommand() {
        super("debug", "Debug things in the plugin", "/arena debug <arena>");
    }

    @Override
    public void execute(String sender, List<String> args) {
        if (!EliteArenas.INSTANCE.isReady()) {
            Log.d("EliteArenas", "Arenas have not finished loading yet!");
            return;
        }

        if (args.isEmpty()) {
            Log.d("EliteArenas", "Use /arena debug <...>");
            return;
        }

        String arenaName = args.get(0);
        Arena arena = EliteArenas.getArenaByName(arenaName);
        if (arena == null) {
            Log.d("EliteArenas", "Arena not found! Use /arena list");
            return;
        }

        if (args.size() > 1 && args.get(1).equalsIgnoreCase("dump")) {
            List<String> arenaDetails = getArenaDetails(arena);
            for (String detail : arenaDetails) {
                Log.d("EliteArenas", detail);
            }
        } else {
            Log.d("EliteArenas", "Debug for arena: " + arena.getName());
        }
    }

    private List<String> getArenaDetails(Arena arena) {
        // Simplified details about the arena, useful for debugging.
        return List.of(
            "Arena Name: " + arena.getName(),
            "Dimensions: " + arena.getWidth() + "x" + arena.getHeight() + "x" + arena.getLength(),
            "Total Blocks: " + arena.getTotalBlocks()
        );
    }
}
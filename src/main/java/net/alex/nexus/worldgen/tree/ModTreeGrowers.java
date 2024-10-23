package net.alex.nexus.worldgen.tree;

import net.alex.nexus.NexusMod;
import net.alex.nexus.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;
import java.util.Optional;
public class ModTreeGrowers {
    public static final TreeGrower EBONY = new TreeGrower(NexusMod.MOD_ID + ":ebony",
            Optional.empty(), Optional.of(ModConfiguredFeatures.EBONY_KEY), Optional.empty());
}

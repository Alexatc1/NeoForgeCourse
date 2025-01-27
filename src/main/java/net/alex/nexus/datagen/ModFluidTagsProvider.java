package net.alex.nexus.datagen;

import net.alex.nexus.NexusMod;
import net.alex.nexus.fluid.ModFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModFluidTagsProvider extends FluidTagsProvider {
    public ModFluidTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, NexusMod.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(FluidTags.WATER)
                .add(ModFluids.SOURCE_BLACK_OPAL_WATER.get())
                .add(ModFluids.FLOWING_BLACK_OPAL_WATER.get());
    }
}

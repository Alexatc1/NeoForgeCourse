package net.alex.nexus.datagen;

import net.alex.nexus.NexusMod;
import net.alex.nexus.block.ModBlocks;
import net.alex.nexus.item.ModItems;
import net.alex.nexus.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                              CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, NexusMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.BLACK_OPAL.get())
                .add(Items.COAL)
                .add(Items.DANDELION)
                .add(Items.COMPASS);

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.BLACK_OPAL_HELMET.get())
                .add(ModItems.BLACK_OPAL_CHESTPLATE.get())
                .add(ModItems.BLACK_OPAL_LEGGINGS.get())
                .add(ModItems.BLACK_OPAL_BOOTS.get());

        this.tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.BLACK_OPAL.get());
        this.tag(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.KAUPEN_SMITHING_TEMPLATE.get());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.EBONY_LOG.get().asItem())
                .add(ModBlocks.EBONY_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_EBONY_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_EBONY_WOOD.get().asItem());
        tag(ItemTags.PLANKS)
                .add(ModBlocks.EBONY_PLANKS.get().asItem());
    }
}

package net.alex.nexus.datagen;

import net.alex.nexus.NexusMod;
import net.alex.nexus.block.ModBlocks;
import net.alex.nexus.block.custom.BlackOpalLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, NexusMod.MOD_ID, exFileHelper);
    }
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BLACK_OPAL_BLOCK);
        blockWithItem(ModBlocks.RAW_BLACK_OPAL_BLOCK);

        blockWithItem(ModBlocks.BLACK_OPAL_ORE);
        blockWithItem(ModBlocks.BLACK_OPAL_DEEPSLATE_ORE );
        blockWithItem(ModBlocks.BLACK_OPAL_END_ORE);
        blockWithItem(ModBlocks.BLACK_OPAL_NETHER_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);

        customLamp();

    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.BLACK_OPAL_LAMP.get()).forAllStates(state -> {
            if(state.getValue(BlackOpalLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("black_opal_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(NexusMod.MOD_ID, "block/" + "black_opal_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("black_opal_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(NexusMod.MOD_ID, "block/" + "black_opal_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.BLACK_OPAL_LAMP.get(), models().cubeAll("black_opal_lamp_on",
                ResourceLocation.fromNamespaceAndPath(NexusMod.MOD_ID, "block/" + "black_opal_lamp_on")));
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}

package net.alex.nexus.datagen;

import net.alex.nexus.NexusMod;
import net.alex.nexus.block.ModBlocks;
import net.alex.nexus.fluid.ModFluids;
import net.alex.nexus.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NexusMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BLACK_OPAL.get());
        basicItem(ModItems.RAW_BLACK_OPAL.get());
        basicItem(ModItems.CHAINSAW.get());
        basicItem(ModItems.TOMATO.get());
        basicItem(ModItems.FROSTFIRE_ICE.get());

        handheldItem(ModItems.BLACK_OPAL_SWORD);
        handheldItem(ModItems.BLACK_OPAL_PICKAXE);
        handheldItem(ModItems.BLACK_OPAL_SHOVEL);
        handheldItem(ModItems.BLACK_OPAL_AXE);
        handheldItem(ModItems.BLACK_OPAL_HOE);

        handheldItem(ModItems.BLACK_OPAL_PAXEL);
        handheldItem(ModItems.BLACK_OPAL_HAMMER);

        trimmedArmorItem(ModItems.BLACK_OPAL_HELMET);
        trimmedArmorItem(ModItems.BLACK_OPAL_CHESTPLATE);
        trimmedArmorItem(ModItems.BLACK_OPAL_LEGGINGS);
        trimmedArmorItem(ModItems.BLACK_OPAL_BOOTS);

        basicItem(ModItems.BLACK_OPAL_HORSE_ARMOR.get());
        basicItem(ModItems.KAUPEN_SMITHING_TEMPLATE.get());

        basicItem(ModItems.METAL_DETECTOR.get());
        //basicItem(ModItems.DATA_TABLET.get());
        basicItem(ModItems.TOMATO_SEEDS.get());

        flowerItem(ModBlocks.PETUNIA);

        basicItem(ModFluids.BLACK_OPAL_WATER_BUCKET.get());

        horizontalBlockItem(ModBlocks.CRYSTALLIZER);

        saplingItem(ModBlocks.EBONY_SAPLING);

    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(NexusMod.MOD_ID,"block/" + item.getId().getPath()));
    }
    private ItemModelBuilder horizontalBlockItem(DeferredBlock<Block> block) {
        return getBuilder(block.getId().getPath()).parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(NexusMod.MOD_ID,
                "block/" + block.getId().getPath())));
    }

    private void trimmedArmorItem(DeferredItem<Item> itemDeferredItem) {
        final String MOD_ID = NexusMod.MOD_ID; // Change this to your mod id
        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;
                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };
                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);
                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");
                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);
                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }

    public void flowerItem(DeferredBlock<Block> block) {
        this.withExistingParent(block.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0",  ResourceLocation.fromNamespaceAndPath(NexusMod.MOD_ID,
                        "block/" + block.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(NexusMod.MOD_ID,"item/" + item.getId().getPath()));
    }

}

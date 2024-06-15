/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package lacrus.fuck.terriblechest.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import lacrus.fuck.terriblechest.item.DiamondSphereItem;
import lacrus.fuck.terriblechest.TerriblechestMod;

public class TerriblechestModItems {
	public static Item DIAMOND_SPHERE;
	public static Item TERRIBLE_CHEST;

	public static void load() {
		DIAMOND_SPHERE = register("diamond_sphere", new DiamondSphereItem());
		TERRIBLE_CHEST = register("terrible_chest", new BlockItem(TerriblechestModBlocks.TERRIBLE_CHEST, new Item.Properties()));
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> content.accept(TERRIBLE_CHEST));
	}

	public static void clientLoad() {
	}

	private static Item register(String registryName, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(TerriblechestMod.MODID, registryName), item);
	}

	private static void registerBlockingProperty(Item item) {
		ItemProperties.register(item, new ResourceLocation("blocking"), (ClampedItemPropertyFunction) ItemProperties.getProperty(Items.SHIELD, new ResourceLocation("blocking")));
	}
}

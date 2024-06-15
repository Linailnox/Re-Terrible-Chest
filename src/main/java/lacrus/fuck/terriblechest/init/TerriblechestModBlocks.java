
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package lacrus.fuck.terriblechest.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import lacrus.fuck.terriblechest.block.TerribleChestBlock;
import lacrus.fuck.terriblechest.TerriblechestMod;

public class TerriblechestModBlocks {
	public static Block TERRIBLE_CHEST;

	public static void load() {
		TERRIBLE_CHEST = register("terrible_chest", new TerribleChestBlock());
	}

	public static void clientLoad() {
		TerribleChestBlock.clientInit();
	}

	private static Block register(String registryName, Block block) {
		return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(TerriblechestMod.MODID, registryName), block);
	}
}

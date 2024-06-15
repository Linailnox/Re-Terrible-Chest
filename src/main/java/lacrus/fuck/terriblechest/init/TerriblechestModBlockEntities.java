
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package lacrus.fuck.terriblechest.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import lacrus.fuck.terriblechest.block.entity.TerribleChestBlockEntity;
import lacrus.fuck.terriblechest.TerriblechestMod;

public class TerriblechestModBlockEntities {
	public static BlockEntityType<?> TERRIBLE_CHEST;

	public static void load() {
		TERRIBLE_CHEST = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(TerriblechestMod.MODID, "terrible_chest"),
				FabricBlockEntityTypeBuilder.create(TerribleChestBlockEntity::new, TerriblechestModBlocks.TERRIBLE_CHEST).build(null));
	}
}

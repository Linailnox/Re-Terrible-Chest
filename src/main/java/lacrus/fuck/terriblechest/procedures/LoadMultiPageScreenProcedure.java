package lacrus.fuck.terriblechest.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;
import java.util.Map;

public class LoadMultiPageScreenProcedure {
	public static void execute(Entity  entity) {
		if (entity == null)
			return;
		double i = 0;
		i = 0;
		for (int index0 = 0; index0 < 27; index0++) {
			i = i + 1;
			if (entity instanceof ServerPlayer _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(((entity.getTags().add("tagName"))).toLowerCase(java.util.Locale.ENGLISH))));
				_setstack.setCount(6);
				((Slot) _slots.get((int) i)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
	}
}


/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package lacrus.fuck.terriblechest.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;

import lacrus.fuck.terriblechest.world.inventory.MultiPageScreenMenu;
import lacrus.fuck.terriblechest.TerriblechestMod;

public class TerriblechestModMenus {
	public static MenuType<MultiPageScreenMenu> MULTI_PAGE_SCREEN;

	public static void load() {
		MULTI_PAGE_SCREEN = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(TerriblechestMod.MODID, "multi_page_screen"), new ExtendedScreenHandlerType<>(MultiPageScreenMenu::new));
		MultiPageScreenMenu.screenInit();
	}
}

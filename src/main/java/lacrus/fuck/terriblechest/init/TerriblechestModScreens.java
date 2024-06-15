
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package lacrus.fuck.terriblechest.init;

import net.minecraft.client.gui.screens.MenuScreens;

import lacrus.fuck.terriblechest.client.gui.MultiPageScreenScreen;

public class TerriblechestModScreens {
	public static void load() {
		MenuScreens.register(TerriblechestModMenus.MULTI_PAGE_SCREEN, MultiPageScreenScreen::new);
	}
}

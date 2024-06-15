
package lacrus.fuck.terriblechest.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import lacrus.fuck.terriblechest.world.inventory.MultiPageScreenMenu;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class MultiPageScreenScreen extends AbstractContainerScreen<MultiPageScreenMenu> {
	private final static HashMap<String, Object> guistate = MultiPageScreenMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_lock;
	ImageButton imagebutton_quick_next_page;
	ImageButton imagebutton_next_page;
	ImageButton imagebutton_quick_previous_page;
	ImageButton imagebutton_previous_page;

	public MultiPageScreenScreen(MultiPageScreenMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("terriblechest:textures/screens/multi_page_screen.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("terriblechest:textures/screens/put_sphere.png"), this.leftPos + 176, this.topPos + -13, 0, 0, 33, 56, 33, 56);

		guiGraphics.blit(new ResourceLocation("terriblechest:textures/screens/background.png"), this.leftPos + 0, this.topPos + -16, 0, 0, 176, 20, 176, 20);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.terriblechest.multi_page_screen.label_terrible_chest"), 7, -9, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.terriblechest.multi_page_screen.label_11"), 153, 5, -12829636, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		imagebutton_lock = new ImageButton(this.leftPos + 181, this.topPos + -8, 20, 21, 0, 0, 21, new ResourceLocation("terriblechest:textures/screens/atlas/imagebutton_lock.png"), 20, 42, e -> {
		});
		guistate.put("button:imagebutton_lock", imagebutton_lock);
		this.addRenderableWidget(imagebutton_lock);
		imagebutton_quick_next_page = new ImageButton(this.leftPos + 158, this.topPos + -9, 11, 11, 0, 0, 11, new ResourceLocation("terriblechest:textures/screens/atlas/imagebutton_quick_next_page.png"), 11, 22, e -> {
		});
		guistate.put("button:imagebutton_quick_next_page", imagebutton_quick_next_page);
		this.addRenderableWidget(imagebutton_quick_next_page);
		imagebutton_next_page = new ImageButton(this.leftPos + 149, this.topPos + -9, 7, 11, 0, 0, 11, new ResourceLocation("terriblechest:textures/screens/atlas/imagebutton_next_page.png"), 7, 22, e -> {
		});
		guistate.put("button:imagebutton_next_page", imagebutton_next_page);
		this.addRenderableWidget(imagebutton_next_page);
		imagebutton_quick_previous_page = new ImageButton(this.leftPos + 121, this.topPos + -9, 11, 11, 0, 0, 11, new ResourceLocation("terriblechest:textures/screens/atlas/imagebutton_quick_previous_page.png"), 11, 22, e -> {
		});
		guistate.put("button:imagebutton_quick_previous_page", imagebutton_quick_previous_page);
		this.addRenderableWidget(imagebutton_quick_previous_page);
		imagebutton_previous_page = new ImageButton(this.leftPos + 134, this.topPos + -9, 7, 11, 0, 0, 11, new ResourceLocation("terriblechest:textures/screens/atlas/imagebutton_previous_page.png"), 7, 22, e -> {
		});
		guistate.put("button:imagebutton_previous_page", imagebutton_previous_page);
		this.addRenderableWidget(imagebutton_previous_page);
	}
}

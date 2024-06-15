
package lacrus.fuck.terriblechest.world.inventory;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.Container;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import lacrus.fuck.terriblechest.procedures.LoadMultiPageScreenProcedure;
import lacrus.fuck.terriblechest.init.TerriblechestModMenus;
import lacrus.fuck.terriblechest.init.TerriblechestModItems;

import java.util.HashMap;

public class MultiPageScreenMenu extends AbstractContainerMenu {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private BlockPos pos;
	private final Container inventory;
	private boolean bound = false;

	public MultiPageScreenMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		this(id, inv, new SimpleContainer(28));
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
	}

	public MultiPageScreenMenu(int id, Inventory inv, Container container) {
		super(TerriblechestModMenus.MULTI_PAGE_SCREEN, id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.inventory = container;
		this.addSlot(new Slot(inventory, 27, 152, 55) {
			private final int slot = 27;
		});
		this.addSlot(new Slot(inventory, 1, 8, 19) {
			private final int slot = 1;
		});
		this.addSlot(new Slot(inventory, 2, 26, 19) {
			private final int slot = 2;
		});
		this.addSlot(new Slot(inventory, 3, 44, 19) {
			private final int slot = 3;
		});
		this.addSlot(new Slot(inventory, 4, 62, 19) {
			private final int slot = 4;
		});
		this.addSlot(new Slot(inventory, 5, 80, 19) {
			private final int slot = 5;
		});
		this.addSlot(new Slot(inventory, 6, 98, 19) {
			private final int slot = 6;
		});
		this.addSlot(new Slot(inventory, 7, 116, 19) {
			private final int slot = 7;
		});
		this.addSlot(new Slot(inventory, 8, 134, 19) {
			private final int slot = 8;
		});
		this.addSlot(new Slot(inventory, 9, 152, 19) {
			private final int slot = 9;
		});
		this.addSlot(new Slot(inventory, 10, 8, 37) {
			private final int slot = 10;
		});
		this.addSlot(new Slot(inventory, 11, 26, 37) {
			private final int slot = 11;
		});
		this.addSlot(new Slot(inventory, 12, 44, 37) {
			private final int slot = 12;
		});
		this.addSlot(new Slot(inventory, 13, 62, 37) {
			private final int slot = 13;
		});
		this.addSlot(new Slot(inventory, 14, 80, 37) {
			private final int slot = 14;
		});
		this.addSlot(new Slot(inventory, 15, 98, 37) {
			private final int slot = 15;
		});
		this.addSlot(new Slot(inventory, 16, 116, 37) {
			private final int slot = 16;
		});
		this.addSlot(new Slot(inventory, 17, 134, 37) {
			private final int slot = 17;
		});
		this.addSlot(new Slot(inventory, 18, 152, 37) {
			private final int slot = 18;
		});
		this.addSlot(new Slot(inventory, 19, 8, 55) {
			private final int slot = 19;
		});
		this.addSlot(new Slot(inventory, 20, 26, 55) {
			private final int slot = 20;
		});
		this.addSlot(new Slot(inventory, 21, 44, 55) {
			private final int slot = 21;
		});
		this.addSlot(new Slot(inventory, 22, 62, 55) {
			private final int slot = 22;
		});
		this.addSlot(new Slot(inventory, 23, 80, 55) {
			private final int slot = 23;
		});
		this.addSlot(new Slot(inventory, 24, 98, 55) {
			private final int slot = 24;
		});
		this.addSlot(new Slot(inventory, 25, 116, 55) {
			private final int slot = 25;
		});
		this.addSlot(new Slot(inventory, 26, 134, 55) {
			private final int slot = 26;
		});
		this.addSlot(new Slot(inventory, 0, 183, 19) {
			private final int slot = 0;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return TerriblechestModItems.DIAMOND_SPHERE == stack.getItem();
			}
		});
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));

		LoadMultiPageScreenProcedure.execute(entity);
	}

	@Override
	public boolean stillValid(Player player) {
		return this.inventory.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 28) {
				if (!this.moveItemStackTo(itemstack1, 28, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 28, false)) {
				if (index < 28 + 27) {
					if (!this.moveItemStackTo(itemstack1, 28 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, 28, 28 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty())
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
	}

	public static void screenInit() {
	}
}

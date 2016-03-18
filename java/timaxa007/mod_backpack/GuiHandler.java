package timaxa007.mod_backpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		ItemStack current = player.getCurrentEquippedItem();
		if (current != null) {
			if (ID == 0) return new ItemStorageContainer(player, new InventoryItemStorage(current));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		ItemStack current = player.getCurrentEquippedItem();
		if (current != null) {
			if (ID == 0) return new ItemStorageGui(player, new InventoryItemStorage(current));
		}
		return null;
	}

}

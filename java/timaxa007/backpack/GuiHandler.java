package timaxa007.backpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuiHandler implements cpw.mods.fml.common.network.IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		ItemStack current = player.getCurrentEquippedItem();
		if (current != null) {
			if (ID == GuiID.BACKPACK.ordinal())
				return new timaxa007.backpack.inventory.ContainerItemStorage(player,
						new timaxa007.backpack.inventory.InventoryItemStorage(current));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		ItemStack current = player.getCurrentEquippedItem();
		if (current != null) {
			if (ID == GuiID.BACKPACK.ordinal())
				return new timaxa007.backpack.client.GuiItemStorage(player,
						new timaxa007.backpack.inventory.InventoryItemStorage(current));
		}
		return null;
	}

	public static void openGui(GuiID ID, EntityPlayer player, int x, int y, int z) {
		player.openGui(ModBackpack.instance, ID.ordinal(), player.worldObj, x, y, z);
	}

	public static void openGui(GuiID ID, EntityPlayer player) {
		player.openGui(ModBackpack.instance, ID.ordinal(), player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
	}

	public static enum GuiID {
		BACKPACK;
	}

}

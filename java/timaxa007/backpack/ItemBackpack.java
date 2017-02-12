package timaxa007.backpack;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBackpack extends Item {

	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (player.isSneaking()) {

		} else {
			GuiHandler.openGui(GuiHandler.GuiID.BACKPACK, player);
		}
		return super.onItemRightClick(is, world, player);
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs table, List list) {
		//list.add(new ItemStack(id, 1, 0));
		list.add(addNBT(new ItemStack(id, 1, 0), 1));
		list.add(addNBT(new ItemStack(id, 1, 0), 3));
		list.add(addNBT(id, SizeStorage.SIZE1));
		list.add(addNBT(id, SizeStorage.SIZE2));
		list.add(addNBT(id, SizeStorage.SIZE3));
		list.add(addNBT(id, SizeStorage.SIZE4));
		list.add(addNBT(id, SizeStorage.SIZE5));
		list.add(addNBT(id, SizeStorage.SIZE6));
		list.add(addNBT(id, SizeStorage.SIZE7));
		list.add(addNBT(id, SizeStorage.SIZE8));
		list.add(addNBT(id, SizeStorage.SIZE9));
	}

	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
		NBTTagCompound nbt = is.getTagCompound();
		if (nbt != null && nbt.hasKey("CustomSize")) list.add("Slots: " + (int)(nbt.getByte("CustomSize") & 255) + ".");
	}

	public static ItemStack addNBT(Item item, SizeStorage size) {
		return addNBT(new ItemStack(item, 1, 0), size);
	}

	public static ItemStack addNBT(ItemStack is, SizeStorage size) {
		return addNBT(is, size.getSize());
	}

	public static ItemStack addNBT(ItemStack is, int size) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setByte("CustomSize", (byte)size);
		is.setTagCompound(nbt);
		return is;
	}

	public static enum SizeStorage {

		SIZE1(9),//(9 * 1)
		SIZE2(18),//(9 * 2)
		SIZE3(27),//(9 * 3)//размеры как у одинарного сундука
		SIZE4(36),//(9 * 4)
		SIZE5(45),//(9 * 5)
		SIZE6(54),//(9 * 6)//размеры как у двойного сундука
		SIZE7(63),//(9 * 7)
		SIZE8(72),//(9 * 8)
		SIZE9(81);//(9 * 9)

		private final int size;

		SizeStorage(int size) {
			this.size = size;
		}

		public int getSize() {
			return size;
		}

	}

}

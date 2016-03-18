package timaxa007.mod_backpack;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemStorage extends Item implements IItemStorage {

	public ItemStorage() {
		super();
		setNoRepair();
		setMaxStackSize(1);
		setUnlocalizedName("storage");
		setTextureName("modid:storage");
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int tick, boolean flag) {
		if (!world.isRemote && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			ItemStack current = player.getCurrentEquippedItem();
			Container con = player.openContainer;
			if (con != null) {
				//-----------------------------------------------------------------------------------
				if (con instanceof ItemStorageContainer) {
					ItemStorageContainer bc = (ItemStorageContainer)con;
					ItemStack new_is = bc.update(player);
					if (new_is != null) current = new_is;
					//Закрытия окна, в случаи если предмета нет нужного нам предмета.
					if (new_is == null || !(new_is != null && new_is.getItem() instanceof IItemStorage))
						player.closeScreen();
				}
				//------------------------------------------------------------------------------------
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (player.isSneaking()) {

		} else {
			player.openGui(ModBackpack.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
			//Где "id" - это ид вашего окна.
		}
		return super.onItemRightClick(is, world, player);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item id, CreativeTabs table, List list) {
		//list.add(new ItemStack(id, 1, 0));
		list.add(addNBT(id, size_storage.SIZE1));
		list.add(addNBT(id, size_storage.SIZE2));
		list.add(addNBT(id, size_storage.SIZE3));
		list.add(addNBT(id, size_storage.SIZE4));
		list.add(addNBT(id, size_storage.SIZE5));
		list.add(addNBT(id, size_storage.SIZE6));
		list.add(addNBT(id, size_storage.SIZE7));
		list.add(addNBT(id, size_storage.SIZE8));
		list.add(addNBT(id, size_storage.SIZE9));
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
		NBTTagCompound nbt = is.getTagCompound();
		if (nbt != null && nbt.hasKey("CustomSize")) list.add("Slots: " + (int)(nbt.getByte("CustomSize") & 255) + ".");
	}

	public static ItemStack addNBT(Item item, size_storage size) {
		return addNBT(new ItemStack(item, 1, 0), size);
	}

	public static ItemStack addNBT(ItemStack is, size_storage size) {
		return addNBT(is, size.getSize());
	}

	public static ItemStack addNBT(ItemStack is, int size) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setByte("CustomSize", (byte)size);
		is.setTagCompound(nbt);
		return is;
	}

	public static enum size_storage {

		SIZE1(9),//(9 * 1)
		SIZE2(18),//(9 * 2)
		SIZE3(27),//(9 * 3)//размеры как у одинарного сундука
		SIZE4(36),//(9 * 4)
		SIZE5(45),//(9 * 5)
		SIZE6(54),//(9 * 6)//размеры как у двойного сундука
		SIZE7(63),//(9 * 7)
		SIZE8(72),//(9 * 8)
		SIZE9(81);//(9 * 9)

		private int size;

		size_storage(int size) {
			this.size = size;
		}

		public int getSize() {
			return size;
		}

	}

}

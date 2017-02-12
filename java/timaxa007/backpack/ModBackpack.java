package timaxa007.backpack;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModBackpack.MODID, name = ModBackpack.NAME, version = ModBackpack.VERSION)
public class ModBackpack {

	public static final String
	MODID = "backpack",
	NAME = "Backpack",
	VERSION = "0.003";

	@Mod.Instance(MODID) public static ModBackpack instance;

	public static Item backpack;

	@Mod.EventHandler
	public void preInit(cpw.mods.fml.common.event.FMLPreInitializationEvent event) {
		backpack = new ItemBackpack().setUnlocalizedName("backpack").setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(1).setTextureName(MODID + ":backpack");
		GameRegistry.registerItem(backpack, "backpack");
	}

	@Mod.EventHandler
	public void init(cpw.mods.fml.common.event.FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new EventInit());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}

	@Mod.EventHandler
	public void postInit(cpw.mods.fml.common.event.FMLPostInitializationEvent event) {

		GameRegistry.addRecipe(ItemBackpack.addNBT(backpack, ItemBackpack.SizeStorage.SIZE1), new Object[]{
			"SLS", "LSL", 'L', Items.leather, 'S', Items.string});

		GameRegistry.addRecipe(ItemBackpack.addNBT(backpack, ItemBackpack.SizeStorage.SIZE2), new Object[]{
			"SSS", "L L", "LCL", 'L', Items.leather, 'S', Items.string, 'C', Blocks.carpet});

		GameRegistry.addRecipe(ItemBackpack.addNBT(backpack, ItemBackpack.SizeStorage.SIZE3), new Object[]{
			"LSL", "SWS", "LLL", 'L', Items.leather, 'W', Blocks.wool, 'S', Items.string});

		GameRegistry.addRecipe(ItemBackpack.addNBT(backpack, ItemBackpack.SizeStorage.SIZE4), new Object[]{
			"LLL", "WCW", "LLL", 'L', Items.leather, 'W', Blocks.carpet, 'C', Blocks.chest});

	}

}

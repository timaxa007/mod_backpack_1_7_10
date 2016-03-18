package timaxa007.mod_backpack;

import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModBackpack.MODID, name = ModBackpack.NAME, version = ModBackpack.VERSION)
public class ModBackpack {

	public static final String
	MODID = "mod_backpack",
	NAME = "Backpack's Mod",
	VERSION = "0.002a"
	;

	@Mod.Instance(value = ModBackpack.MODID)
	public static ModBackpack instance;

	@SidedProxy(modId = ModBackpack.MODID, serverSide = "timaxa007.mod_backpack.ProxyCommon", clientSide = "timaxa007.mod_backpack.ProxyClient")
	public static ProxyCommon proxy;

	public static Item item_storage;

	@Mod.EventHandler
	public void preInit(cpw.mods.fml.common.event.FMLPreInitializationEvent event) {

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

		item_storage = new ItemStorage();
		GameRegistry.registerItem(item_storage, "item_storage");

		proxy.preInit();

	}

}

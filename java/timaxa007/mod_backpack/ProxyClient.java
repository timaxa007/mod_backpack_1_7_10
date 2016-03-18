package timaxa007.mod_backpack;

import cpw.mods.fml.common.FMLCommonHandler;


public class ProxyClient extends ProxyCommon {

	public void preInit() {
		super.preInit();
		FMLCommonHandler.instance().bus().register(new EventItemStorageClient());
	}

}

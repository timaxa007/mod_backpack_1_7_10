package timaxa007.backpack;

import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import timaxa007.backpack.inventory.ContainerItemStorage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class EventInit {

	@SubscribeEvent
	public void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
		switch(event.phase) {
		case END:
			Container con = event.player.openContainer;
			if (con != null && con instanceof ContainerItemStorage) {
				ContainerItemStorage cis = (ContainerItemStorage)con;
				ItemStack current = event.player.getCurrentEquippedItem();
				//update
				if (event.side.isServer() && current != null) cis.update(event.player);
				//Закрытия окна, в случаи если предмета нет нужного нам предмета.
				if (current == null || !(current != null && current.getItem() instanceof ItemBackpack))
					event.player.closeScreen();
			}
			break;
		default:break;
		}
	}

}

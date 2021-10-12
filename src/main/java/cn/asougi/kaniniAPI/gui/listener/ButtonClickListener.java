package cn.asougi.kaniniAPI.gui.listener;

import cn.asougi.kaniniAPI.gui.Button;
import cn.asougi.kaniniAPI.gui.InventoryGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class ButtonClickListener implements Listener {
   @EventHandler
   public void onPlayerClickInventory(InventoryClickEvent evt) {
      Inventory inv = evt.getClickedInventory();

      InventoryView v = evt.getView();
      if(InventoryGUI.guiContains(v.getBottomInventory())||InventoryGUI.guiContains(v.getTopInventory())){
         evt.setCancelled(true);
      }
      if (InventoryGUI.guiContains(inv)) {
         evt.setCancelled(true);
         InventoryGUI gui = InventoryGUI.getGUI(inv);
         Button button = gui.getButton(evt.getRawSlot());
         if(button!=null){
            button.getButtonClick().onButtonClicked(evt.getClick());
            gui.refreshInventory();
         }
      }
   }

//   @EventHandler
//   public void onPlayerChatTest(AsyncPlayerChatEvent evt) {
//      if(evt.getMessage().equals("测试")){
//         Player p = evt.getPlayer();
//         mainGui mainGui = new mainGui(p);
//         mainGui.openInventory();
//         evt.setCancelled(false);
//      }
//      if(evt.getMessage().equals("pwned")){
//         Player p = evt.getPlayer();
//         SampleGUI gui = new SampleGUI(p);
//         gui.openInventory();
//      }
//   }

}

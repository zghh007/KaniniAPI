package cn.asougi.kaniniAPI.gui;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryGUI {
   int size = 9;
   String name = "箱子界面";
   private Button[] buttons = new Button[54];
   private Player owner;
   private Inventory inv;
   private static final HashMap<Inventory, InventoryGUI> guiMap = Maps.newHashMap();

   public InventoryGUI(String name, Player player, int row) {
      this.name = name;
      this.size = row * 9;
      this.owner = player;
      this.inv = Bukkit.createInventory(this.owner, this.size, name);
      guiMap.put(this.inv, this);
   }

   public void setButton(int index, Button b) {
      this.buttons[index] = b;
   }

   public Button getButton(int index) {
      return this.buttons[index];
   }

   public void openInventory() {
      this.refreshInventory();
      this.owner.openInventory(this.inv);
   }

   public void refreshInventory() {
      this.inv.clear();
      for(int i = 0; i < this.buttons.length; ++i) {
         Button b = this.buttons[i];
         if (b != null) {
            this.inv.setItem(i, b.getItemStack());
         }
      }
   }

   public static void refreshGuiSet() {
      Set<Inventory> keys = Sets.newHashSet();
      keys.addAll(guiMap.keySet());

      for (Inventory inv : keys) {
         if (inv.getHolder() != null && inv.getHolder() instanceof Player && !((Player) inv.getHolder()).isOnline()) {
            guiMap.remove(inv);
         }
      }

   }

   public static boolean guiContains(Inventory inv) {
      return guiMap.containsKey(inv);
   }

   public static InventoryGUI getGUI(Inventory inv) {
      return guiMap.get(inv);
   }
}

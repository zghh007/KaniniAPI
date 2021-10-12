package cn.asougi.kaniniAPI.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Button extends ItemStack {
   private ItemStack icon;
   private ButtonClick buttonClick;

   public Button(ItemStack ic) {
      this.icon = new ItemStack(Material.SKULL_ITEM);
      this.buttonClick = (type) -> {
      };
      this.icon = ic;
   }

   public Button(ItemStack ic, ButtonClick bc) {
      this.icon = new ItemStack(Material.SKULL_ITEM);
      this.buttonClick = (type) -> {
      };
      this.icon = ic;
      this.buttonClick = bc;
   }

   public boolean equalsIcon(ItemStack object) {
      return this.icon.equals(object);
   }

   public ItemStack getItemStack() {
      return this.icon.clone();
   }

   public ButtonClick getButtonClick() {
      return this.buttonClick;
   }
}

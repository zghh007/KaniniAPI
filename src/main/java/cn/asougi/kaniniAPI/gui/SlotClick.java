package cn.asougi.kaniniAPI.gui;

import org.bukkit.inventory.ItemStack;

public interface SlotClick {
    public void onSlotChange(ItemStack before,ItemStack after);
}

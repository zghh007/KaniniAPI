package cn.asougi.kaniniAPI.gui;

import org.bukkit.inventory.ItemStack;

public class Slot{
    private SlotClick slotClick;
    private ItemStack itemStack = null;


    public Slot(ItemStack item,SlotClick slot){
        itemStack = item;
        slotClick = slot;
    }
    public void setItemStack(ItemStack item){
        itemStack = item;
    }
    public ItemStack getItemStack(){
        return itemStack;
    }
    public SlotClick getSlotClick(){
        return slotClick;
    }
}

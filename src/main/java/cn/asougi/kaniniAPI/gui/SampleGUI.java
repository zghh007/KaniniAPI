package cn.asougi.kaniniAPI.gui;

import cn.asougi.kaniniAPI.inventory.ItemFactoryAPI;
import cn.asougi.kaniniAPI.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SampleGUI extends InventoryGUI{



    public SampleGUI(Player player) {//为指定玩家创建箱子页面

        super("样板GUI页面", player, 4);//row:行数为4

//        创建一个ItemStack
        ItemStack item1 = ItemFactoryAPI.getItemStackWithDurability(Material.WOOL,(short)3,"一个苹果按钮","§r点击后可以触发一个按钮事件","触发后会判断玩家点击的左右键");
//        ItemStack item2 = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/c494a30a2799ebfbc8ad776b9387f3e9d1799bba8900013ac992db1fbad41ce7","§rhello","§r114514","1919810!");
        ItemStack item2 = SkullAPI.getPlayerSkull(player,"xxx","xxxxx");
        Button button1 = new Button(item1,type -> {
            if(type.isShiftClick()){
                player.sendMessage("你按下了SHIFT");
            }
            if(type.isLeftClick()){
                player.sendMessage("你使用了左键");
                this.setButton(0,new Button(ItemFactoryAPI.getItemStack(Material.COAL)));
            }
            if(type.isRightClick()){
                player.sendMessage("你使用了右键");
            }
        });

        Button button2 = new Button(item2,type -> {});
//        在第一格的位置显示苹果按钮
        this.setButton(0, button1);
        this.setButton(1,button1);
        this.setButton(2,button2);
        for(int i =10;i<20;i++){
            ItemStack iss = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ed885f2393a3c11a1c6a53182a2a00f9dddc5312e71b8fd192e815f9ca984020",i+"","fork you");
            setButton(i,new Button(iss));
        }
    }
}

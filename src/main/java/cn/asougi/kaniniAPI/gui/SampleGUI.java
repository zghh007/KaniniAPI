package cn.asougi.kaniniAPI.gui;

import cn.asougi.kaniniAPI.App;
import cn.asougi.kaniniAPI.inventory.ItemFactoryAPI;
import cn.asougi.kaniniAPI.inventory.SkullAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SampleGUI extends InventoryGUI{



    public SampleGUI(Player player) {//为指定玩家创建箱子页面

        super("样板GUI页面", player, 4);//row:行数为4

//        创建一个ItemStack
//        ItemStack item1 = ItemFactoryAPI.getItemStackWithDurability(Material.WOOL,(short)3,"一个苹果按钮","§r点击后可以触发一个按钮事件","触发后会判断玩家点击的左右键");

        for(int i = 0 ; i < 27 ; i++){
            ItemStack iss = SkullAPI.getSkullItem("http://textures.minecraft.net/texture/ed885f2393a3c11a1c6a53182a2a00f9dddc5312e71b8fd192e815f9ca984020",i+"","fork you");
            setButton(i,new Button(iss,(type)->{System.out.println("hello world");}));
        }

        ItemStack saveItem = App.getInstance().getConfig().getItemStack("test");
        setSlot(13,new Slot(saveItem,(before,after) -> {
            App.getInstance().getConfig().set("test",after);
            App.getInstance().saveConfig();
        }));

    }
}

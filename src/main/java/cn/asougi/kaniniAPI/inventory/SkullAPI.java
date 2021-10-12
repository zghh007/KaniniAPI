package cn.asougi.kaniniAPI.inventory;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullAPI {
   private static Field profile_field;
   private static Method getProfile;
   private static Base64.Encoder encoder = Base64.getEncoder();

   static{
      try {
         Class<? extends ItemMeta> class_to_nms = (new ItemStack(Material.SKULL_ITEM)).getItemMeta().getClass();
         profile_field = class_to_nms.getDeclaredField("profile");
         profile_field.setAccessible(true);
      } catch (SecurityException | NoSuchFieldException var5) {
         var5.printStackTrace();
      }
   }

   public static ItemStack getSkullItem(String url,String name,String... lores) {
      ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
      SkullMeta meta = (SkullMeta)is.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(Arrays.asList(lores));
      GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
      String before = "{\"textures\":{\"SKIN\":{\"url\":\"";
      String after = "\"}}}";
      url = before + url + after;
      profile.getProperties().put("textures", new Property("textures", encoder.encodeToString(url.getBytes())));
      try {
         profile_field.set(meta, profile);
      } catch (IllegalAccessException | IllegalArgumentException var4) {
         var4.printStackTrace();
      }
      is.setItemMeta(meta);
      return is;
   }

   public static ItemStack getPlayerSkull(OfflinePlayer p, String name, String... lores){
      ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
      SkullMeta meta = (SkullMeta)is.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(Arrays.asList(lores));
      meta.setOwner(p.getName());
      is.setItemMeta(meta);
      return is;
   }
}

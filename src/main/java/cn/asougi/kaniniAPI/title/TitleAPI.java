package cn.asougi.kaniniAPI.title;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class TitleAPI {





   @Deprecated
   public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) { sendTitle(player, fadeIn, stay, fadeOut, message, null); }



   @Deprecated
   public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) { sendTitle(player, fadeIn, stay, fadeOut, null, message); }



   @Deprecated
   public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) { sendTitle(player, fadeIn, stay, fadeOut, title, subtitle); }


   public static void sendPacket(Player player, Object packet) {
      try {
         Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
         Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
         playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static Class<?> getNMSClass(String name) {
      String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
      try {
         return Class.forName("net.minecraft.server." + version + "." + name);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {

      try {
         if (title != null) {
            title = ChatColor.translateAlternateColorCodes('&', title);
            title = title.replaceAll("%player%", player.getName());

            Object e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
            Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
            Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class });
            Object titlePacket = subtitleConstructor.newInstance(new Object[] { e, chatTitle, fadeIn, stay, fadeOut });
            sendPacket(player, titlePacket);

            e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
            subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent") });
            titlePacket = subtitleConstructor.newInstance(new Object[] { e, chatTitle });
            sendPacket(player, titlePacket);
         }

         if (subtitle != null) {
            subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
            subtitle = subtitle.replaceAll("%player%", player.getDisplayName());

            Object e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
            Object chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
            Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class });
            Object subtitlePacket = subtitleConstructor.newInstance(new Object[] { e, chatSubtitle, fadeIn, stay, fadeOut });
            sendPacket(player, subtitlePacket);

            e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
            chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
            subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class });
            subtitlePacket = subtitleConstructor.newInstance(new Object[] { e, chatSubtitle, fadeIn, stay, fadeOut });
            sendPacket(player, subtitlePacket);
         }
      } catch (Exception var11) {
         var11.printStackTrace();
      }
   }


   public static void clearTitle(Player player) { sendTitle(player, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", ""); }


   public static void sendTabTitle(Player player, String header, String footer) {
      if (header == null) header = "";
      header = ChatColor.translateAlternateColorCodes('&', header);

      if (footer == null) footer = "";
      footer = ChatColor.translateAlternateColorCodes('&', footer);

      header = header.replaceAll("%player%", player.getDisplayName());
      footer = footer.replaceAll("%player%", player.getDisplayName());

      try {
         Object tabHeader = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + header + "\"}" });
         Object tabFooter = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + footer + "\"}" });
         Constructor<?> titleConstructor = getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[0]);
         Object packet = titleConstructor.newInstance(new Object[0]);
         try {
            Field aField = packet.getClass().getDeclaredField("a");
            aField.setAccessible(true);
            aField.set(packet, tabHeader);
            Field bField = packet.getClass().getDeclaredField("b");
            bField.setAccessible(true);
            bField.set(packet, tabFooter);
         } catch (Exception e) {
            Field aField = packet.getClass().getDeclaredField("header");
            aField.setAccessible(true);
            aField.set(packet, tabHeader);
            Field bField = packet.getClass().getDeclaredField("footer");
            bField.setAccessible(true);
            bField.set(packet, tabFooter);
         }
         sendPacket(player, packet);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}

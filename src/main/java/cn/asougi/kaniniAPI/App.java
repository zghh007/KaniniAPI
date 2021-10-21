package cn.asougi.kaniniAPI;

import cn.asougi.kaniniAPI.gui.InventoryGUI;
import cn.asougi.kaniniAPI.gui.SampleGUI;
import cn.asougi.kaniniAPI.gui.listener.ButtonClickListener;
import cn.asougi.kaniniAPI.scoreboard.ScoreBoardAPI;
import cn.asougi.kaniniAPI.scoreboard.board.TestInfoBoard;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class App extends JavaPlugin implements Listener {
   private static App instance;
   private static ScoreBoardAPI scoreBoardAPI;
   public void onEnable() {
      instance = this;
      scoreBoardAPI = new ScoreBoardAPI();
      saveConfig();
      this.getServer().getPluginManager().registerEvents(this, this);
      this.getServer().getPluginManager().registerEvents(new ButtonClickListener(), this);
      loadGuiCleanTimer();
//      ProtocolManager pm = ProtocolLibrary.getProtocolManager();
//      pm.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.BLOCK_CHANGE) {
//         @Override
//         public void onPacketSending(PacketEvent event) {
//            StructureModifier<BlockPosition> l = event.getPacket().getBlockPositionModifier();
//            BlockPosition bp = l.read(0);
//            System.out.println(Arrays.toString(new int[]{bp.getX(),bp.getY(),bp.getZ()}));
//         }
//   });
      }

   public static App getInstance() {
      return instance;
   }
   public static ScoreBoardAPI getScoreBoardAPI(){return scoreBoardAPI;}

   public void loadGuiCleanTimer() {
      Bukkit.getScheduler().runTaskTimer(this, () -> {
         InventoryGUI.refreshGuiSet();
      }, 0L, 1200L);
   }

   @EventHandler
   public void onPlayerChat(PlayerChatEvent evt){
//      TestInfoBoard test = new TestInfoBoard(evt.getPlayer());
//      getScoreBoardAPI().register(evt.getPlayer(),test);
      SampleGUI gui = new SampleGUI(evt.getPlayer());
      gui.openInventory();
   }
}

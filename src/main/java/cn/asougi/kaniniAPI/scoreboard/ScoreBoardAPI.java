package cn.asougi.kaniniAPI.scoreboard;

import cn.asougi.kaniniAPI.App;
import cn.asougi.kaniniAPI.scoreboard.board.ImplKaniniBoard;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ScoreBoardAPI {

    HashMap<Player, ImplKaniniBoard> map = Maps.newHashMap();

    public ScoreBoardAPI(){
        Bukkit.getScheduler().runTaskTimer(App.getInstance(),()->refreshAll(),5,20);
    }

    public void register(Player p,ImplKaniniBoard board){
        map.put(p,board);
    }

    public void unregister(Player p){
        map.remove(p);
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public void refreshAll(){
        Iterator<Map.Entry<Player,ImplKaniniBoard>> iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<Player,ImplKaniniBoard> entry = iter.next();
            if(!entry.getKey().isOnline()){
                iter.remove();
            }
        }
        for(ImplKaniniBoard board:map.values()){
            board.refreshScoreBoard();
        }
    }
}

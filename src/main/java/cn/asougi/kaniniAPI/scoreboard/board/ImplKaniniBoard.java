package cn.asougi.kaniniAPI.scoreboard.board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public abstract class ImplKaniniBoard {

    Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
    private Player player;

    public ImplKaniniBoard(Player p){
        player = p;
    }
    public abstract int size();
    public abstract String indexAt(int i);
    public void refreshScoreBoard(){
        player.setScoreboard(sc);
    }
}

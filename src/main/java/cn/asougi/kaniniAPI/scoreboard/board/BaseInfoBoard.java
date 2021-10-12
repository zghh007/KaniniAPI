package cn.asougi.kaniniAPI.scoreboard.board;

import cn.asougi.kaniniAPI.scoreboard.message.ImplMessage;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class BaseInfoBoard extends ImplKaniniBoard {

    private static int count = 0;
    private static final List<ChatColor> COLORS = Arrays.asList(ChatColor.values());
    private List<Team> lines = Lists.newArrayList();
    private List<ImplMessage> message = Lists.newArrayList();

    private Objective objective;

    public BaseInfoBoard(Player p){
        super(p);
        objective = sc.registerNewObjective("baseInfoBoard"+count++,"dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for(int i=0;i<COLORS.size();i++){
            lines.add(sc.registerNewTeam(COLORS.get(i).name()));
        }
    }

    public void setDisplayName(String displayName){
        objective.setDisplayName(displayName);
    }

    public void addText(ImplMessage m){
        message.add(m);
    }


    public void removeText(int index){
        message.remove(index);
    }

    public void clear(){
        message.clear();
    }

    @Override
    public int size() {
        return message!=null?message.size():0;
    }

    @Override
    public String indexAt(int i) {
        return message.get(i).getMessage();
    }

    @Override
    public void refreshScoreBoard(){
        for(int i=0;i<message.size();i++){
            Team t = lines.get(i);
            for(String entry:t.getEntries()) {
                t.removeEntry(entry);
                sc.resetScores(entry);
            }
            String m = message.get(i).getMessage();
            String prefix = "";
            String suffix = "";
            if(m.length()>16){
                prefix = m.substring(0,16);
                suffix = m.substring(16);
                if(suffix.length()>16){
                    suffix = suffix.substring(0,16);
                }
            }
            else{
                prefix = m;
            }
            ChatColor c = COLORS.get(i);
            String name = "§"+c.getChar()+"§r";
            objective.getScore(name).setScore(message.size()-i);
            t.addEntry(name);
            t.setPrefix(prefix);
            t.setSuffix(suffix);

        }
        super.refreshScoreBoard();
    }
}

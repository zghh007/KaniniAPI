package cn.asougi.kaniniAPI.scoreboard.board;

import org.bukkit.entity.Player;

import java.util.Date;

public class TestInfoBoard extends BaseInfoBoard{

    public TestInfoBoard(Player p){
        super(p);

        this.setDisplayName("测试面板");
        this.addText(()->"名称:"+p.getName());
        this.addText(()->"等级:"+String.valueOf(p.getLevel()));
    }
}

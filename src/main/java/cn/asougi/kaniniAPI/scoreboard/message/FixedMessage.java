package cn.asougi.kaniniAPI.scoreboard.message;

public class FixedMessage implements ImplMessage{


    private String msg;

    public FixedMessage(String message){
        msg = message;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}

package Handler;

import AppKickstarter.AppKickstarter;
import AppKickstarter.misc.Msg;
import Hardware.Gate;

public class GateHandler extends Handler {
    Gate gate;

    public GateHandler(String ID,String neighbor, AppKickstarter appKickstarter,boolean Entrance_Gate_Judge){
        super(ID,neighbor, appKickstarter);
        this.gate=new Gate(Entrance_Gate_Judge);
    }

    public GateHandler(String ID,AppKickstarter appKickstarter,boolean Entrance_Gate_Judge){
        super(ID,appKickstarter);
        this.gate=new Gate(Entrance_Gate_Judge);
    }

    public void run(){
        String cardID="Gate Handler ("+gate.getEntrance_Gate_Judge()+" "+ id + ")";
        log.info(cardID+ ": starting...");
        String sent="";
        for(boolean quit=false;!quit;){
            Msg receivedMsg=mbox.receive();
            String details=receivedMsg.getDetails();
            log.info(gate.toString());
            log.info(cardID+" Receive "+details);
            if(details.toLowerCase().equals("open")) {
                gate.open();
                sent="successfully open";
                neighborMBox.send(new Msg(id,mbox, Msg.Type.Gate,sent));
                log.info(sent);
            }
            else if(details.toLowerCase().equals("close")) {
                gate.close();
                sent="successfully close";
                neighborMBox.send(new Msg(id,mbox, Msg.Type.Gate,sent));
                log.info(sent);
            }
            else if (receivedMsg.getType()== Msg.Type.Terminate) {
                quit = true;
                log.info("Quit!");
            }
            else
                log.warning(cardID+" : Receive Invalid Commands: "+details);
            log.info("Now "+gate.toString());
        }
    }
}

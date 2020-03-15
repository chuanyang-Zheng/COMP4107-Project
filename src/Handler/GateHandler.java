package Handler;

import AppKickstarter.AppKickstarter;
import AppKickstarter.misc.Msg;
import Hardware.Gate;

public class GateHandler extends Handler {
    Gate gate;

    public GateHandler(String neighbor, AppKickstarter appKickstarter,boolean Entrance_Gate_Judge){
        super(neighbor, appKickstarter);
        this.gate=new Gate(Entrance_Gate_Judge);
    }

    public void run(){
        String cardID="Gate Handler ("+gate.getEntrance_Gate_Judge()+" "+ id + ")";
        log.info(cardID+ ": starting...");
        for(boolean quit=false;!quit;){
            Msg receivedMsg=mbox.receive();
            String details=receivedMsg.getDetails();
            if(details.toLowerCase().equals("open"))
                gate.open();
            else if(details.toLowerCase().equals("close"))
                gate.close();
            else if (receivedMsg.getType()== Msg.Type.Terminate)
                        quit=true;
            else
                log.warning(cardID+" : Receive Invalid Commands: "+details);
        }
    }
}

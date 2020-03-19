package PCS;

import AppKickstarter.AppKickstarter;
import AppKickstarter.misc.AppThread;
import AppKickstarter.misc.MBox;
import AppKickstarter.misc.Msg;
import Handler.GateHandler;
import Handler.VacancyHandler;
import Hardware.Gate;
import Hardware.Vacancy;

public class PCS extends AppThread {
    protected GateHandler gateEntrancHandler;
    protected GateHandler gateExitHandler;
    protected VacancyHandler vacancyHandler;

    public PCS(String ID,AppKickstarter appKickstarter){
        super(ID,appKickstarter);
    }

    public void run(){

        log.info("PCS : starting...");
        String sent="";
        for(boolean quit=false;!quit;){
            Msg receivedMsg=mbox.receive();
            String details=receivedMsg.getDetails();
            MBox tepBox= appKickstarter.getThread(receivedMsg.getSender()).getMBox();//get Sender Box

            switch (receivedMsg.getType()){
                case Terminate:
                    quit=true;
                    break;
                case Gate://First Think Which Message May Be Received From Gate? Then, how to deal with them.
                    if(details.equals("successfully open")){
                        //do something
                    }
                    else if(details.equals("successfully close")){
                        //do something
                    }
                    else{
                        log.warning("Invalid Message : "+details);//invalid message. Use waring
                    }
                    break;
                case Dispatcher://dispatcher...

                    break;

                case PayMachine:

                    break;

                case Collector:

                    break;

                case MotionSensor:
                    vacancyHandler.getMBox().send(new Msg(this.id,this.mbox, Msg.Type.PCS," 1 1"));//“ 1 1” is an example. It is determined by motion sensor
                    break;

                case Vacancy://receive successfully increase or decrease


                    break;
            }
        }
    }

}

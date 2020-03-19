package Handler;

import AppKickstarter.AppKickstarter;
import AppKickstarter.misc.Msg;
import Hardware.Vacancy;

public class VacancyHandler extends Handler {
    Vacancy vacancy;
    public VacancyHandler(String ID,String neighbor, AppKickstarter appKickstarter,int[] availableFloorCount){
        super(ID,appKickstarter);
        this.neighbor = neighbor;
        neighborMBox = appKickstarter.getThread(this.neighbor).getMBox();
        this.vacancy=new Vacancy(availableFloorCount);
    }
    public VacancyHandler(String ID,AppKickstarter appKickstarter){
        super(ID,appKickstarter);
        IDCount++;
    }

    public void run(){
        String cardID="Vacancy Handler ( "+ id + ")";
        log.info(cardID+ ": starting...");
        String sent="";

        for(boolean quit=false;!quit;){
            Msg receivedMsg=mbox.receive();//receive message
            String details=receivedMsg.getDetails();//get detail
            log.info("Original "+vacancy.toString());//Report initial State
            log.info("Receive Information "+details);

            if(receivedMsg.getType()== Msg.Type.Terminate){
                log.warning(cardID+" STOP!");
                quit=true;//quit
            }
            else{
                String[] splitDetail=details.split("\\s+");//detail protocol:  floorNumber1 floorNumber2. For example,  1 3 means from floor 1 go to floor 3.  3 1 means from floor 3 go to floor1.
                int floorNumber1=-1;
                int floorNumber2=-1;
                try {
                    floorNumber1 = Integer.parseInt(splitDetail[0]);//parse to get int floor number
                    floorNumber2 = Integer.parseInt(splitDetail[1]);//parse to get int floor number
                }
                catch (Exception e){
                    log.warning(cardID+" Parse Int Fail.");//can not change it to ine number
                    e.printStackTrace();//print stack trace
                }

                if(floorNumber1>=0&&floorNumber1<vacancy.getFloorNumber()){//check floor is not out of range
                    vacancy.increateAvailableParkingCount(floorNumber1);//increase
                }
                else if(floorNumber1==-1){
                    log.fine("The Car Go To The Park");
                }
                else{
                    log.warning("Receive Invalid Floor Number "+floorNumber1);
                }

                if(floorNumber2>=0&&floorNumber2<vacancy.getFloorNumber()){//check floor is not out of range
                    if(vacancy.checkAvailableFloor(floorNumber2))
                        vacancy.decreaseAvailableParkingCount(floorNumber2);
                    else {// available space is smaller than 1
                        log.warning("No More Available Parking Spaces in "+floorNumber2);
                    }
                }
                else if (floorNumber2==-1){//Go out
                    log.fine("The car Go Out The Park");
                }
                else {
                    log.warning("Receive Invalid Floor Number "+floorNumber1);
                }

                log.info("Now "+ vacancy.toString());//Report State
            }

        }
    }
}

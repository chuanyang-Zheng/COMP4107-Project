package Handler;

import AppKickstarter.AppKickstarter;
import AppKickstarter.misc.AppThread;
import AppKickstarter.misc.MBox;

public class Handler extends AppThread {
    protected static int IDCount;
    protected  String neighbor;
    protected  MBox neighborMBox;
    public Handler(String ID,String neighbor, AppKickstarter appKickstarter){
        super(ID,appKickstarter);
        IDCount++;
        this.neighbor = neighbor;
        neighborMBox = appKickstarter.getThread(this.neighbor).getMBox();
    }
    public Handler(String ID,AppKickstarter appKickstarter){
        super(ID,appKickstarter);
        IDCount++;
    }
    public void setNeighbor(String neighbor){
        this.neighbor = neighbor;
        neighborMBox = appKickstarter.getThread(this.neighbor).getMBox();
    }

    public void run(){

    }
}

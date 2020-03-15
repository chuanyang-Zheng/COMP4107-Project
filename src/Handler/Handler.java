package Handler;

import AppKickstarter.AppKickstarter;
import AppKickstarter.misc.AppThread;
import AppKickstarter.misc.MBox;

public class Handler extends AppThread {
    protected static int IDCount;
    protected final String neighbor;
    protected final MBox neighborMBox;
    public Handler(String neighbor, AppKickstarter appKickstarter){
        super(""+IDCount,appKickstarter);
        IDCount++;
        this.neighbor = neighbor;
        neighborMBox = appKickstarter.getThread(this.neighbor).getMBox();
    }

    public void run(){

    }
}

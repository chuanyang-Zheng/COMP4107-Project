package Hardware;

public class Gate extends Hardware {
    protected final boolean Entrance_Gate_Judge;//if it is true ,it is a extrance gate. Else, it is an exit Gate.
    protected int waitTime=3;

    public Gate(boolean Entrance_Gate_Judge){
        super("close");
        this.Entrance_Gate_Judge=Entrance_Gate_Judge;
    }

    public void open(){
        super.setState("opening");
        super.waiteTime(waitTime);
        super.setState("open");
    }

    public void close(){
        super.setState("closing");
        super.waiteTime(waitTime);
        super.setState("close");
    }

    public boolean getEntrance_Gate_Judge() {
        return Entrance_Gate_Judge;
    }
}

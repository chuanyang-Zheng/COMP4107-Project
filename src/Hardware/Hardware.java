package Hardware;

public class Hardware {
    protected int ID;
    protected static int IDCount=0;
    protected String state;
    public static void main(String[] args) throws Exception {
        Hardware myHardware=new Hardware();
        System.out.println();
    }

    public Hardware() throws Exception {
        this.ID=IDCount;//give ID
        IDCount++;//IDCount + 1
        if(state==null){
            throw new Exception("Please define Initial State");
        }
    }

    public Hardware(String state){
        this.ID=IDCount;//give ID
        IDCount++;//IDCount + 1
        this.state=state;//define State
    }

    public int getID() {
        return ID;
    }

    public void waiteTime(int waitTime){
        long begin=System.currentTimeMillis();
        while(System.currentTimeMillis()-begin<waitTime*1000){

        }
    }

    public String getState(){
        return state;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setState(String state) {
        this.state = state;
    }
}

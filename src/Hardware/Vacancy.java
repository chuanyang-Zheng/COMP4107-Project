package Hardware;

import java.util.Arrays;

public class Vacancy extends Hardware {
    int[] availabelParkingCount;//the available parking places of each floor

    public Vacancy(int[] availabelParkingCount){
        super("ok");//two states of Vacancy: ok And fail
        this.availabelParkingCount=availabelParkingCount;
    }

    //Increase Correspond floor by one
    public void increateAvailableParkingCount(int floorNumber){
        availabelParkingCount[floorNumber]=availabelParkingCount[floorNumber]+1;

    }

    public void decreaseAvailableParkingCount(int floorNumber){
        availabelParkingCount[floorNumber]=availabelParkingCount[floorNumber]-1;
    }

    public int getFloorNumber(){
        return availabelParkingCount.length;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "availabelParkingCount=" + Arrays.toString(availabelParkingCount) +
                ", state='" + state + '\'' +
                '}';
    }

    public boolean checkAvailableFloor(int floorNumber){
        if(availabelParkingCount[floorNumber]>=1)
            return true;

        return false;
    }
}

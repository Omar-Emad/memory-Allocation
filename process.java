package sample;

public class process {

     int size;
     int startAddress;
    String Name ;
    public process() {
        startAddress=-1;
    }

    public process(int size, int startAddress) {
        this.size = size;
        this.startAddress = startAddress;


    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public process(int size, int startAddress, String name) {
        this.size = size;
        this.startAddress = startAddress;
        Name = name;
    }

    public int getSize() {
        return size;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }



}

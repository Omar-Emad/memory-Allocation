package sample;

public class hole {

    private int startAddress;

    private int size;


    public hole() {

    }

    public hole(int startAddress, int size) {
        this.startAddress = startAddress;
        this.size = size;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public int getEndAddress() {
        return startAddress+size-1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
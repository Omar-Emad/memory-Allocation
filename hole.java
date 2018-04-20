package sample;

public class hole {

    private int startAddress;

    private int size;
    private int endAddress=startAddress+size;

    public hole() {

    }

    public hole(int startAddress, int size) {
        this.startAddress = startAddress;
        this.size = size;
        this.endAddress = startAddress+size;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public int getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(int endAddress) {
        this.endAddress = endAddress;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
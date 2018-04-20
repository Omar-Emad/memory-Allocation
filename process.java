package sample;

public class process {

    private int size;
    private int startAddress;

    public process(int size, int startAddress) {
        this.size = size;
        this.startAddress = startAddress;
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

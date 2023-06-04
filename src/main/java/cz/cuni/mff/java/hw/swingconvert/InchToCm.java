package cz.cuni.mff.java.hw.swingconvert;

public class InchToCm implements Converter{
    Double num;
    public InchToCm(Double num) {
        this.num = num;
    }
    @Override
    public double convert(Double num) {
        return num*2.54;
    }
}

package cz.cuni.mff.java.hw.swingconvert;

public class ConverterFromFootToCm implements Converter {
    public Double num;
    public String fromUnit;
    public String toUnit;

    public ConverterFromFootToCm(Double num, String fromUnit, String toUnit) {
        this.num = num;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }
    @Override
    public double convert(Double num){
        return num * 30.48;
    }

}

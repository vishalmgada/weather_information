package ui.utilities;

public class TemperatureConversionUtils {

    public static float convertTempToCelsius(float kelvinTemp){
        return (float) (kelvinTemp-273.15);
    }

    public static float convertTempToFahrenheit(float kelvinTemp){
        return ((convertTempToCelsius(kelvinTemp)*9/5)+32);
    }

}

package common;

public class Comparator {

    public static float convertTempToCelsius(float kelvinTemp){
        return (float) (kelvinTemp-273.15);
    }

    public static float convertTempToFahrenheit(float kelvinTemp){
        return ((convertTempToCelsius(kelvinTemp)*9/5)+32);
    }


    public static boolean compareTempCelsius(float uiTemperature, float apiTemperature, float variance){
        return (Math.abs(uiTemperature-convertTempToCelsius(apiTemperature))<variance);
    }

    public static boolean compareTempFahrenheit(float uiTemperature, float apiTemperature, float variance){
        return (Math.abs(uiTemperature-convertTempToFahrenheit(apiTemperature))<variance);
    }
}

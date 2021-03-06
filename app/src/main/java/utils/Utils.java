package utils;


public class Utils {

    /**
     * Izračuna povprečno hitrost
     * @param distance v metrih
     * @param time v sekundah
     * @return povprečno hitrost v km/h
     */
    public static double getAverageSpeed(double distance, long time) {
        if(distance == 0 || time == 0)
            return 0;

        double speedMs = distance / time;
        return speedMs * 3.6;
    }

    public static boolean isNumeric(String s) {
        return s.matches("\\d+");
    }

    public static String getFileExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }

        return extension;
    }
}

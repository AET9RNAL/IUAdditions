package com.aeternal.iuadditions;

public class Utils {
    public static String getWholeNumberString(long value) {
        final String[] units = new String[] { "", "K", "M", "G", "T", "P", "E" };
        int unitIndex = 0;

        while (value >= 1000 && unitIndex < units.length - 1) {
            value /= 1000;
            unitIndex++;
        }

        return value + units[unitIndex];
    }


}

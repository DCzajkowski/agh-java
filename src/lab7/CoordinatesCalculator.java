package lab7;

public class CoordinatesCalculator {
    /**
     * Returns distance between two points in metres
     */
    protected static double distanceBetween(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.pow(Math.sin(latDistance / 2), 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.pow(Math.sin(lonDistance / 2), 2);

        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)) * 1000;
    }
}

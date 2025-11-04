public class WeatherData {
    private final long timestamp;
    private final String attribute;
    private final int x;
    private final int y;
    private final double value;

    public WeatherData(long timestamp, String attribute, int x, int y, double value) {
        this.timestamp = timestamp;
        this.attribute = attribute;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public static WeatherData fromString(String line) {
        String[] parts = line.split(" ");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid weather data format");
        }
        return new WeatherData(
            Long.parseLong(parts[0]),
            parts[1],
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]),
            Double.parseDouble(parts[4])
        );
    }

    public long getTimestamp() { return timestamp; }
    public String getAttribute() { return attribute; }
    public int getX() { return x; }
    public int getY() { return y; }
    public double getValue() { return value; }

    @Override
    public String toString() {
        return String.format("WeatherData{timestamp=%d, attribute='%s', x=%d, y=%d, value=%.2f}",
            timestamp, attribute, x, y, value);
    }
}
package edu.tdtu.iot.iotsystem.Constant;

import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final int MAX_AGE_COOKIE = 7 * 24 * 60 * 60;

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String USER_ROLE = "USER";

    public static final String ENERGY_CONSUMPTION = "energy_consumption";
    public static final String TEMPERATURE = "temperature";
    public static final String HUMIDITY = "humidity";
    public static final String DUST = "dust";

    public static List<String> getTypes() {
        return Arrays.asList(ENERGY_CONSUMPTION, TEMPERATURE, HUMIDITY, DUST);
    }
}
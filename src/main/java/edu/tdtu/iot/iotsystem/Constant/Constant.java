package edu.tdtu.iot.iotsystem.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final int MAX_AGE_COOKIE = 7 * 24 * 60 * 60;

    public static final int ORDER_STATUS = 1;
    public static final int DELIVERY_STATUS = 2;
    public static final int COMPLETE_STATUS = 3;
    public static final int RETURNED_STATUS = 4;
    public static final int CANCELED_STATUS = 5;

    public static final int NUM_LIMIT_OF_OUTSTANDING_PRODUCTS = 5;

    public static final int FROM_0K_TO_200K = 1;
    public static final int FROM_200K_TO_500K= 2;
    public static final int FROM_500K_TO_1000K = 3;
    public static final int FROM_1000K_TO_MORE = 4;

    public static final List<Integer> LIST_PRICES = new ArrayList<>(Arrays
            .asList(FROM_0K_TO_200K,
                    FROM_200K_TO_500K,
                    FROM_500K_TO_1000K,
                    FROM_1000K_TO_MORE
            )
    );
}
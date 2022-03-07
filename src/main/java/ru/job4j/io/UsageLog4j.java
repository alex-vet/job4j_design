package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String maxValue = "Max value primitive type";
        byte a = Byte.MAX_VALUE;
        short b = Short.MAX_VALUE;
        int c = Integer.MAX_VALUE;
        long d = Long.MAX_VALUE;
        char e = 'E';
        float f = Float.MAX_VALUE;
        double g = Double.MAX_VALUE;
        boolean h = true;

        LOG.debug("{} is - byte: {}, short: {}, int: {}, long: {}, float: {}, double: {}.\n Symbol e: {}, Boolean value: {}",
                maxValue, a, b, c, d, f, g, e, h);
    }
}
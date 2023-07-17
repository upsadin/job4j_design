package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
/*        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");*/
        String name = "Pavel";
        char lastN = 'U';
        byte age = 36;
        int ageDays = age * 365;
        short height = 189;
        float weight = 97.7f;
        boolean work = true;
        double family = 2.5;
        LOG.debug("User info: name {}, lastName {}, age {}, days {}, height {}, " +
                "weight {}, members of family {}, is working ? - {}",
                name, lastN, age, ageDays, height, weight, family, work);

    }
}

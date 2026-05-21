package net.qiu.advancedbodyparam.config;

import eu.midnightdust.lib.config.MidnightConfig;

/*public class ModConfig extends MidnightConfig {

    public static final String STATUS = "status";

    @Entry(category = STATUS) public static boolean disable_status_in_peaceful = true;

    @Comment(category = STATUS) public static Comment spacer1;

    @Comment(category = STATUS, centered = true) public static Comment fractured_comment;

    @Comment(category = STATUS) public static Comment spacer2;

    @Comment(category = STATUS, centered = true) public static Comment fracture_fall_comment;
    @Entry(category = STATUS, min = 0) public static int mild_fall_threshold = 3;
    @Entry(category = STATUS, min = 0) public static int mid_fall_threshold = 6;
    @Entry(category = STATUS, min = 0) public static int severe_fall_threshold = 8;
}

 */

public class ModConfig {

    public boolean disable_status_in_peaceful = false;

    public int mild_fall_threshold = 3;
    public int mid_fall_threshold = 6;
    public int severe_fall_threshold = 8;
}
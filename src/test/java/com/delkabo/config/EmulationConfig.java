package com.delkabo.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:mobileProperty/emulation.properties",
        "classpath:wordpress.properties"
})
public interface EmulationConfig extends Config {

    String loginWP();
    String passwordWP();
    String deviceName();
    String platformName();
    String platformVersion();
    String url();
    String app();

}

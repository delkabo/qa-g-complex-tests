package com.delkabo.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:mobileProperty/browserstack.properties",
        "classpath:wordpress:properties"
})
public interface BrowserstackConfig extends Config {

    String loginWP();
    String passwordWP();
    String deviceName();
    String platformName();
    String platformVersion();
    String loginBS();
    String passwordBS();
    String identificateapp();
    String url();
    String app();

}

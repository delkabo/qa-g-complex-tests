package com.delkabo.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:mobileProperty/browserstack.properties",
        "classpath:wordpress:properties"
})
public interface BrowserstackConfig extends Config {

    @Key("loginWP")
    String loginWP();

    @Key("passwordWP")
    String passwordWP();

    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("loginBS")
    String loginBS();

    @Key("passwordBS")
    String passwordBS();

    @Key("identificateapp")
    String identificateapp();

    @Key("url")
    String url();

    @Key("app")
    String app();

}

package com.delkabo.tests.mobile;

import com.delkabo.drivers.mobile.EmulatorMobileDriver;

public class TestBase {

    public static String loginWP = EmulatorMobileDriver.configEmul.loginWP(),
                         passwordWP = EmulatorMobileDriver.configEmul.passwordWP();

}

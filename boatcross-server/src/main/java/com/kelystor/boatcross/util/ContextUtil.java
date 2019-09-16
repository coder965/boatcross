package com.kelystor.boatcross.util;

import com.kelystor.boatcross.enums.DeployEnvironment;

public class ContextUtil {
    public static DeployEnvironment currentDeployEnvironment() {
        return DeployEnvironment.QA;
    }
}

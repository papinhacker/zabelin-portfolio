//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.severotek.core.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int counter = 0;
    int retryLimit = 0;

    public RetryAnalyzer() {
    }

    public boolean retry(ITestResult result) {
        this.retryLimit = Integer.parseInt(System.getProperty("retryOnFailCount", "0"));
        if (this.counter < this.retryLimit) {
            ++this.counter;
            return true;
        } else {
            return false;
        }
    }
}

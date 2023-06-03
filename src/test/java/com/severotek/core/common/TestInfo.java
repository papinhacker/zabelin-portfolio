//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.severotek.core.common;

import java.util.Objects;

public class TestInfo {
    private String className;
    private String methodName;
    private String methodDescription;
    private String status;
    private boolean isConfiguration;
    private long duration;
    private long ts;

    public long getTs() {
        return this.ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public TestInfo() {
    }

    public TestInfo(String className, String methodName, String methodDescription, String status, boolean isConfiguration, long duration, long ts) {
        this.className = className;
        this.methodName = methodName;
        this.methodDescription = methodDescription;
        this.status = status;
        this.isConfiguration = isConfiguration;
        this.duration = duration;
        this.ts = ts;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDescription() {
        return this.methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isConfiguration() {
        return this.isConfiguration;
    }

    public void setConfiguration(boolean configuration) {
        this.isConfiguration = configuration;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String toString() {
        return "TestInfo{className='" + this.className + '\'' + ", methodName='" + this.methodName + '\'' + ", methodDescription='" + this.methodDescription + '\'' + ", status='" + this.status + '\'' + ", isConfiguration=" + this.isConfiguration + ", duration=" + this.duration + ", ts=" + this.ts + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof TestInfo)) {
            return false;
        } else {
            TestInfo info = (TestInfo)o;
            return this.isConfiguration == info.isConfiguration && this.duration == info.duration && this.ts == info.ts && Objects.equals(this.className, info.className) && Objects.equals(this.methodName, info.methodName) && Objects.equals(this.methodDescription, info.methodDescription) && Objects.equals(this.status, info.status);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.className, this.methodName, this.methodDescription, this.status, this.isConfiguration, this.duration, this.ts});
    }
}

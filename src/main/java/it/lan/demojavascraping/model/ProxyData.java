package it.lan.demojavascraping.model;

public class ProxyData {
    private String proxyIp;
    private String proxyPort;
    private String lastCheck;
    private String proxySpeed;
    private String uptime;
    private String proxyCountry;
    private String anonymity;

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(String lastCheck) {
        this.lastCheck = lastCheck;
    }

    public String getProxySpeed() {
        return proxySpeed;
    }

    public void setProxySpeed(String proxySpeed) {
        this.proxySpeed = proxySpeed;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getProxyCountry() {
        return proxyCountry;
    }

    public void setProxyCountry(String proxyCountry) {
        this.proxyCountry = proxyCountry;
    }

    public String getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(String anonymity) {
        this.anonymity = anonymity;
    }
}

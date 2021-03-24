package it.lan.demojavascraping.model;

public class CovidData {
    private String country;
    private String cases;
    private String death;
    private String recoveries;

    public CovidData(String country, String cases, String death, String recoveries) {
        this.country = country;
        this.cases = cases;
        this.death = death;
        this.recoveries = recoveries;
    }

    @Override
    public String toString() {
        return "CovidData{" +
                "country='" + country + '\'' +
                ", cases='" + cases + '\'' +
                ", death='" + death + '\'' +
                ", recoveries='" + recoveries + '\'' +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getRecoveries() {
        return recoveries;
    }

    public void setRecoveries(String recoveries) {
        this.recoveries = recoveries;
    }
}

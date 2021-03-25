package it.lan.demojavascraping.model;

import java.util.Map;

public class Bike {
    private String name;
    private String color;
    private String size;
    private String disp;
    private Map<String, String> attributes;

    public Bike() {
    }

    public Bike(String name, String color, String size, String disp, Map<String, String> attributes) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.disp = disp;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", disp='" + disp + '\'' +
                ", attributes=" + attributes.toString() +
                '}';
    }
}

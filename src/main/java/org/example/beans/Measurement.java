package org.example.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Measurement {
    private double value;
    private long timestamp;

    public Measurement() {}

    public Measurement(double value, long timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

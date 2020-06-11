package org.example.beans;

import org.example.exceptions.NonUniqueNodeIdException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class MeasurementList {
    private List<Measurement> list;
    private static MeasurementList instance;

    private MeasurementList() {
        list = new ArrayList<Measurement>();
    }

    public synchronized static MeasurementList getInstance() {
        if (instance == null) instance = new MeasurementList();
        return instance;
    }

    public synchronized List<Measurement> getList() {
        return new ArrayList<Measurement>(list);
    }

    public synchronized void addMeasurement(Measurement newMeasurement) {
        list.add(0, newMeasurement);
    }
}

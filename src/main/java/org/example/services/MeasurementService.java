package org.example.services;

import org.example.beans.Measurement;
import org.example.beans.MeasurementList;
import org.example.utils.MyMath;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/measurements")
public class MeasurementService {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMeasurement(Measurement m) {
        MeasurementList.getInstance().addMeasurement(m);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMeasurements(@QueryParam("n") int n) {
        return Response.ok(getLastMeasurements(n)).build();
    }

    @GET @Path("/deviation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStandardDeviationAndAverage(@QueryParam("n") int n) {
        List<Measurement> measurementList = getLastMeasurements(n);
        List<Double> valueList = new ArrayList<Double>();
        for (Measurement m : measurementList) { valueList.add(m.getValue()); }

        double standardDeviation = MyMath.calculateStandardDeviation(valueList);
        double average = MyMath.calculateAverage(valueList);

        HashMap<String, Double> standardDeviationAndAverage = new HashMap<String, Double>();
        standardDeviationAndAverage.put("standardDeviation", standardDeviation);
        standardDeviationAndAverage.put("average", average);

        return Response.ok(standardDeviationAndAverage).build();
    }

    private List<Measurement> getLastMeasurements(int n) {
        List<Measurement> measurements = MeasurementList.getInstance().getList();
        if (n <= 0 || n > measurements.size()) n = measurements.size();
        return measurements.subList(0, n);
    }
}

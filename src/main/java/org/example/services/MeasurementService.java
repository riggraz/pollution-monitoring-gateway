package org.example.services;

import org.example.beans.Measurement;
import org.example.beans.MeasurementList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        List<Measurement> measurements = MeasurementList.getInstance().getList();

        if (n <= 0 || n > measurements.size()) n = measurements.size();

        List<Measurement> measurementsToReturn = measurements.subList(0, n);
        return Response.ok(measurementsToReturn).build();
    }
}

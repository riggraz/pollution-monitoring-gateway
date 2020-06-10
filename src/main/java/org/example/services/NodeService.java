package org.example.services;

import org.example.beans.Node;
import org.example.beans.NodeList;
import org.example.exceptions.NonUniqueNodeIdException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.UUID;

@Path("/nodes")
public class NodeService {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNode(Node n) {
        try {
            NodeList.getInstance().addNode(n);
            ArrayList<Node> nodeList = NodeList.getInstance().getList();
            return Response.ok(nodeList).build();
        } catch (NonUniqueNodeIdException e) {
            e.printStackTrace();
            return Response.status(400, e.getErrorMessage()).build();
        }
    }

    @DELETE @Path("/{id}")
    public Response deleteNode(@PathParam("id") UUID nodeId) {
        NodeList.getInstance().deleteNode(nodeId);
        return Response.noContent().build();
    }

    @GET @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countNodes() {
        int count = NodeList.getInstance().getList().size();
        return Response.ok(count).build();
    }
}

package io.swagger.v3.jaxrs2.it.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/files")
public class OctetStreamResource {
    @GET
    @Path("/attachment")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFileById(@FormParam("fileId") final String fileId) {
        return Response.status(200).entity("File  " + fileId + " has been returned").build();
    }
}

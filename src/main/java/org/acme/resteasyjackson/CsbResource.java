package org.acme.resteasyjackson;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/csb")
public class CsbResource {

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dish> getItems() throws IOException {
        return Dish.listAll();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Dish getDish() throws IOException{
         UncScraper uncScraper = new UncScraper();
         List<Dish> dishes = uncScraper.getCsbDishes();
         for (Dish dish : dishes){
             dish.persist();
         }
         return dishes.get(0);
    }

    public CsbResource(){}

}

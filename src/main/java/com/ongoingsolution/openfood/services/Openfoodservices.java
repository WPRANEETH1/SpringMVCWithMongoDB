/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ongoingsolution.openfood.services;

import com.ongoingsolution.openfood.model.OpenFoodProduct;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author xxxxxxx
 */
@Path("/openfoodservices")
@WebService(name = "psrServices", targetNamespace = "")
public interface Openfoodservices {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getallproducts")
    public Response getAllProducts();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getallorderbyquantiyt")
    public Response getAllProductsOrderByQuentity();

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getproductbyname/{productName}")
    public Response getProductsByName(@PathParam("productName") String productName);

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getnameandquentity/{productName}/{quantity}")
    public Response getProductsByNameAndQuantity(@PathParam("productName") String productName, @PathParam("quantity") int quantity);

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/createproduct")
    public Response createProduct(OpenFoodProduct createproduct);

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/updateproduct")
    public Response updateProduct(OpenFoodProduct updateproduct);

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/deleteproduct/{Id}")
    public Response deleteProduct(@PathParam("Id") String Id);
}

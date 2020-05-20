package io.helidon.examples.sockshop.payment;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public interface PaymentApi {
    @GET
    @Path("{orderId}")
    @Produces(APPLICATION_JSON)
    @Operation(summary = "Return the payment authorization for the specified order")
    Response getOrderAuthorizations(@Parameter(description = "Order identifier")
                                    @PathParam("orderId") String orderId);

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "Authorize a payment request")
    Authorization authorize(@Parameter(description = "Payment request") PaymentRequest paymentRequest);
}

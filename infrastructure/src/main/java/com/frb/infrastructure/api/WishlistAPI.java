package com.frb.infrastructure.api;

import com.frb.infrastructure.wishlist.models.AddProductRequest;
import com.frb.infrastructure.wishlist.models.AddProductResponse;
import com.frb.infrastructure.wishlist.models.ProductResponse;
import com.frb.infrastructure.wishlist.models.WishlistResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "wishlists")
public interface WishlistAPI {

    @PostMapping(
            value = "add-product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Add a new product to customer's wishlist", description = "it is necessary to add an Authorization JWT token header with the customer id equal: 'customerId'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> addProduct(@RequestBody AddProductRequest input);

    @GetMapping(
            value = "products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get all products from customer's wishlist", description = "it is necessary to add an Authorization JWT token header with the customer id equal: 'customerId'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Wishlist was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    WishlistResponse retriveWishlistWithProducts();

    @GetMapping(
            value = "products/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a product from customer's wishlist by productId", description = "it is necessary to add an Authorization JWT token header with the customer id equal: 'customerId'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Product was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ProductResponse retriveWishlistProduct(@PathVariable(name = "id") String id);

    @DeleteMapping(value = "products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a product by it's identifier from customer's wishlist", description = "it is necessary to add an Authorization JWT token header with the customer id equal: 'customerId'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void deleteProductFromWishlist(@PathVariable(name = "id") String id);
}

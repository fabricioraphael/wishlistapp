package com.frb.infrastructure.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.frb.application.wishlist.addProduct.AddProductOutput;
import com.frb.application.wishlist.addProduct.AddProductUseCase;
import com.frb.domain.exceptions.DomainException;
import com.frb.domain.exceptions.NotFoundException;
import com.frb.domain.validation.Error;
import com.frb.domain.validation.handler.Notification;
import com.frb.infrastructure.ControllerTest;
import com.frb.infrastructure.wishlist.models.AddProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Right;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest(controllers = WishlistAPI.class)
public class WishlistControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AddProductUseCase addProductUseCase;
}

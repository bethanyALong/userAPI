package com.example.demo.api;

import builders.RequestBuilder;
import builders.ResponseBuilder;
import com.example.demo.api.CardApiApplicationController;
import com.example.demo.models.ResponseModel;
import com.example.demo.models.UserDetails;
import com.example.demo.services.DatabaseFacade;
import com.example.demo.services.VendorFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardApiApplicationController.class)
public class CardAPIApplicationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatabaseFacade databaseFacade;

    @MockBean
    private VendorFacade vendorFacade;

    RequestBuilder requestBuilder = new RequestBuilder();
    ResponseBuilder responseBuilder = new ResponseBuilder();
    ResponseEntity<ResponseModel> successResponse = responseBuilder.getSuccessResponseRegister();
    UserDetails userDetails = requestBuilder.getUserDetails();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void registerUserSuccess() throws Exception {
        String request = objectMapper.writeValueAsString(userDetails);
        Mockito.when(databaseFacade.registerUser(Mockito.any(UserDetails.class))).thenReturn(successResponse);
        this.mockMvc
                .perform(post("/register-user")
                .content(request)
                .header("x-auth-token", "vD08cTfRi7"))
                .andExpect(status().isOk());
    }

    @Test
    public void invalidHeader() throws Exception {
        String request = objectMapper.writeValueAsString(userDetails);
        Mockito.when(databaseFacade.registerUser(Mockito.any(UserDetails.class))).thenReturn(successResponse);
        this.mockMvc
                .perform(post("/register-user")
                        .content(request))
                .andExpect(status().is4xxClientError());
    }

}
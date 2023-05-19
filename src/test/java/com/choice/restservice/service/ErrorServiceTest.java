package com.choice.restservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.choice.restservice.model.Hotel;
import com.choice.wsdl.ServiceStatus;

public class ErrorServiceTest {
    ErrorService errorService = new ErrorService();

    @Test
    public void shouldThrowExceptionByEmptyHotelBody() {
        Hotel hotel = new Hotel(null, null, 0);
        try{
            errorService.checkHotelInfo(hotel);
            fail();
        }catch(ResponseStatusException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Empty request body!", e.getReason());
        }
    }

    @Test
    public void shouldThrowExceptionByNoHotelName() {
        Hotel hotel = new Hotel(null, "Av. Principal 50", 4);
        try{
            errorService.checkHotelInfo(hotel);
            fail();
        }catch(ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Empty hotel name!", e.getReason());
        }
    }

    @Test
    public void shouldThrowExceptionByNoHotelAddress() {
        Hotel hotel = new Hotel("Rest Hotel II", null, 4);
        try{
            errorService.checkHotelInfo(hotel);
            fail();
        }catch(ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Empty hotel address!", e.getReason());
        }
    }

    @Test
    public void shouldThrowExceptionByNoHotelRating() {
        Hotel hotel = new Hotel("Rest Hotel II", "Av. Principal 50", 0);
        try{
            errorService.checkHotelInfo(hotel);
            fail();
        }catch(ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Rating must be in a range from 1 to 5!", e.getReason());
        }
    }

    @Test
    public void shouldThrowNotFoundExceptionByStatus() {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("NOT_FOUND");
        serviceStatus.setMessage("Testing Not Found");
        try{
            errorService.checkServiceStatus(serviceStatus);
            fail();
        }catch(ResponseStatusException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
            assertEquals("Testing Not Found", e.getReason());
        }
    }

    @Test
    public void shouldThrowBadRequestExceptionByStatus() {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("BAD_REQUEST");
        serviceStatus.setMessage("Testing Bad Request");
        try{
            errorService.checkServiceStatus(serviceStatus);
            fail();
        }catch(ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Testing Bad Request", e.getReason());
        }
    }

    @Test
    public void shouldThrowInternalServerErrprExceptionByStatus() {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("INTERNAL_SERVER_ERROR");
        serviceStatus.setMessage("Testing Internal Server Error");
        try{
            errorService.checkServiceStatus(serviceStatus);
            fail();
        }catch(ResponseStatusException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getStatus());
            assertEquals("Testing Internal Server Error", e.getReason());
        }
    }

}

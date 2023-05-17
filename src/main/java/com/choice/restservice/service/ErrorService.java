package com.choice.restservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.choice.restservice.model.Hotel;
import com.choice.wsdl.ServiceStatus;

@Service
public class ErrorService {
    public void checkHotelInfo(Hotel hotel) {
        if((hotel.getName() == null || hotel.getName().length() < 1) && (hotel.getAddress() == null || hotel.getAddress().length() < 1) && (hotel.getRating() == 0)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty request body!");
        }

        if(hotel.getName() == null || hotel.getName().length() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty hotel name!");
        }

        if(hotel.getAddress() == null || hotel.getAddress().length() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty hotel address!");
        }

        if(hotel.getRating() < 1 || hotel.getRating() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rating must be in a range from 1 to 5!");
        }
    }

    public void checkServiceStatus(ServiceStatus serviceStatus) {
        if(!serviceStatus.getStatusCode().equals("SUCCESS")) {
            if(serviceStatus.getStatusCode().equals("NOT_FOUND")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, serviceStatus.getMessage());
            }

            if(serviceStatus.getStatusCode().equals("BAD_REQUEST")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, serviceStatus.getMessage());
            }

            if(serviceStatus.getStatusCode().equals("INTERNAL_SERVER_ERROR")) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, serviceStatus.getMessage());
            }
        }
    }
}

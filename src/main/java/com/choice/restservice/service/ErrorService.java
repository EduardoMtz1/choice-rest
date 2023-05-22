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

        String message = "Body is missing the next parts: ";
        Boolean isCorrect = true;

        if(hotel.getName() == null || hotel.getName().length() < 1) {
            isCorrect = false;
            message = message + "- Hotel name ";
        }

        if(hotel.getAddress() == null || hotel.getAddress().length() < 1) {
            isCorrect = false;
            message = message + "- Hotel address ";
        }

        if(hotel.getRating() < 1 || hotel.getRating() > 5) {
            isCorrect = false;
            message = message + "- Rating must be in a range from 1 to 5";
        }

        if(!isCorrect) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
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

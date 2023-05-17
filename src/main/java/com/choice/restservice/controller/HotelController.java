package com.choice.restservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choice.restservice.model.Hotel;
import com.choice.restservice.service.ErrorService;
import com.choice.restservice.service.HotelService;
import com.choice.wsdl.AddAmenityResponse;
import com.choice.wsdl.CreateHotelResponse;
import com.choice.wsdl.DeleteAmenityResponse;
import com.choice.wsdl.DeleteHotelResponse;
import com.choice.wsdl.GetAllHotelsByNameResponse;
import com.choice.wsdl.GetHotelByIdResponse;
import com.choice.wsdl.HotelInfo;
import com.choice.wsdl.ServiceStatus;
import com.choice.wsdl.UpdateHotelResponse;

@RestController
@RequestMapping("/api")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @Autowired
    ErrorService errorService;

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelInfo> getHotelById(@PathVariable Long id) {
        GetHotelByIdResponse response = hotelService.getHotelById(id);
        errorService.checkServiceStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getHotelInfo());
    }

    @PostMapping("/hotels/new")
    public ResponseEntity<HotelInfo> createHotel(@RequestBody Hotel hotel) {
        errorService.checkHotelInfo(hotel);
        CreateHotelResponse response = hotelService.createHotel(hotel.getName(), hotel.getAddress(), hotel.getRating());
        errorService.checkServiceStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getHotelInfo());
    }

    @PutMapping("/hotels/{id}")
    public ResponseEntity<HotelInfo> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        errorService.checkHotelInfo(hotel);
        UpdateHotelResponse response = hotelService.updateHotel(id, hotel);
        errorService.checkServiceStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getHotelInfo());
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<ServiceStatus> deleteHotel(@PathVariable Long id) {
        DeleteHotelResponse response = hotelService.deleteHotel(id);
        errorService.checkServiceStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getServiceStatus());
    }

    @GetMapping("/hotels/{name}")
    public ResponseEntity<List<HotelInfo>> findAllHotelsByName(
            @PathVariable String name, 
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        GetAllHotelsByNameResponse response = hotelService.getAllHotelsByName(name, pageSize, pageNumber);
        errorService.checkServiceStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getHotelInfo());
    }

    @PostMapping("/hotels/{hotelId}/amenities/{amenityId}")
    public ResponseEntity<ServiceStatus> addAmenity(@PathVariable Long hotelId, @PathVariable Long amenityId) {
        AddAmenityResponse response = hotelService.addAmenity(hotelId, amenityId);
        errorService.checkServiceStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getServiceStatus());
    }

    @DeleteMapping("/hotels/{hotelId}/amenities/{amenityId}")
    public ResponseEntity<ServiceStatus> deleteAmenity(@PathVariable Long hotelId, @PathVariable Long amenityId) {
        DeleteAmenityResponse response = hotelService.deleteAmenity(hotelId, amenityId);
        errorService.checkServiceStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getServiceStatus());
    }
}

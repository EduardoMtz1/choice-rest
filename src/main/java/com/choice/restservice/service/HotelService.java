package com.choice.restservice.service;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.choice.restservice.model.Hotel;
import com.choice.wsdl.AddAmenityRequest;
import com.choice.wsdl.AddAmenityResponse;
import com.choice.wsdl.CreateHotelRequest;
import com.choice.wsdl.CreateHotelResponse;
import com.choice.wsdl.DeleteAmenityRequest;
import com.choice.wsdl.DeleteAmenityResponse;
import com.choice.wsdl.DeleteHotelRequest;
import com.choice.wsdl.DeleteHotelResponse;
import com.choice.wsdl.GetAllHotelsByNameRequest;
import com.choice.wsdl.GetAllHotelsByNameResponse;
import com.choice.wsdl.GetHotelByIdRequest;
import com.choice.wsdl.GetHotelByIdResponse;
import com.choice.wsdl.HotelInfo;
import com.choice.wsdl.UpdateHotelRequest;
import com.choice.wsdl.UpdateHotelResponse;

public class HotelService extends WebServiceGatewaySupport{
    public GetHotelByIdResponse getHotelById(Long id) {
        GetHotelByIdRequest request = new GetHotelByIdRequest();
        request.setHotelId(id);

        return (GetHotelByIdResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:9090/ws/getHotelByIdRequest"));
    }

    public CreateHotelResponse createHotel(String name, String address, Integer rating) {
        CreateHotelRequest request = new CreateHotelRequest();
        request.setName(name);
        request.setAddress(address);
        request.setRating(rating);

        return (CreateHotelResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:9090/ws/createHotelRequest"));
    }

    public UpdateHotelResponse updateHotel(Long id, Hotel hotel) {
        UpdateHotelRequest request = new UpdateHotelRequest();
        HotelInfo hotelInfo = new HotelInfo();
        hotelInfo.setHotelId(id);
        hotelInfo.setName(hotel.getName());
        hotelInfo.setAddress(hotel.getAddress());
        hotelInfo.setRating(hotel.getRating());
        request.setHotelInfo(hotelInfo);

        return (UpdateHotelResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:9090/ws/updateHotelRequest"));
    }

    public DeleteHotelResponse deleteHotel(Long id) {
        DeleteHotelRequest request = new DeleteHotelRequest();
        request.setHotelId(id);

        return (DeleteHotelResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:9090/ws/deleteHotelRequest"));
    }

    public GetAllHotelsByNameResponse getAllHotelsByName(String name, Integer pageSize, Integer pageNumber) {
        GetAllHotelsByNameRequest request = new GetAllHotelsByNameRequest();
        request.setName(name);
        request.setPageSize(pageSize);
        request.setPageNumber(pageNumber);

        return (GetAllHotelsByNameResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:9090/ws/getAllHotelsByNameRequest"));
    }

    public AddAmenityResponse addAmenity(Long hotelId, Long amenityId){
        AddAmenityRequest request = new AddAmenityRequest();
        request.setHotelId(hotelId);
        request.setAmenityId(amenityId);

        return (AddAmenityResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:9090/ws/addAmenityRequest"));
    }

    public DeleteAmenityResponse deleteAmenity(Long hotelId, Long amenityId) {
        DeleteAmenityRequest request = new DeleteAmenityRequest();
        request.setHotelId(hotelId);
        request.setAmenityId(amenityId);

        return (DeleteAmenityResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:9090/ws/deleteAmenityRequest"));
    }
}

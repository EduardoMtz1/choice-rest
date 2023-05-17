package com.choice.restservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.choice.restservice.service.HotelService;

@Configuration
public class WSConfigSoapClient {
    
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.choice.wsdl");
        return marshaller;
    }

    @Bean
    public HotelService hotelService(Jaxb2Marshaller marshaller) {
        HotelService client = new HotelService();
        client.setDefaultUri("http://localhost:9090/ws/hotels.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }    
}

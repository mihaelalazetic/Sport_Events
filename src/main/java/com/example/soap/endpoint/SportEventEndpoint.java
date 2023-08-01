package com.example.soap.endpoint;

import com.example.soap.*;
import com.example.soap.service.SoapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class SportEventEndpoint {

    private static final String NAMESPACE_URI = "http://www.webs_32_2017.com/soap";

    private final SoapServiceImpl service;
    @Autowired
    public SportEventEndpoint(SoapServiceImpl service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllSportEventsRequest")
    @ResponsePayload
    public GetAllSportEventsResponse getAllSportEvents(@RequestPayload GetAllSportEventsRequest request) {
        return service.getAllSportEvents();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSportEventsByStatusRequest")
    @ResponsePayload
    public GetSportEventsByStatusResponse getSportEventsByStatus(@RequestPayload GetSportEventsByStatusRequest request)
    {
        return service.getSportEventsByStatus(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSportEventsByNameRequest")
    @ResponsePayload
    public GetSportEventsByNameResponse getSportEventsByName(@RequestPayload GetSportEventsByNameRequest request)
    {
        return service.getSportEventsByName(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addSportEventRequest")
    @ResponsePayload
    public AddSportEventResponse addSportEvent(@RequestPayload AddSportEventRequest request)
    {
        return service.addSportEvent(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeSportEventByIdRequest")
    @ResponsePayload
    public RemoveSportEventByIdResponse removeSportEventById(@RequestPayload RemoveSportEventByIdRequest request)
    {
        return service.removeSportEvent(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getByLocationRequest")
    @ResponsePayload
    public GetByLocationResponse getByLocation(@RequestPayload GetByLocationRequest request)
    {
        return service.getByLocation(request);
    }
}
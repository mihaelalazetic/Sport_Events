package com.example.soap.service;

import com.example.soap.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SoapServiceImpl {
    private final SoapSportEventRepository sportEventRepository;

    public SoapServiceImpl(SoapSportEventRepository sportEventRepository)
    {
        this.sportEventRepository = sportEventRepository;
    }

    public GetAllSportEventsResponse getAllSportEvents()
    {
        GetAllSportEventsResponse response = new GetAllSportEventsResponse();
        response.setSportEvents(new SportEvents(sportEventRepository.getSportEvents()));
        return response;
    }

    public GetSportEventsByStatusResponse getSportEventsByStatus(GetSportEventsByStatusRequest request)
    {
        GetSportEventsByStatusResponse response = new GetSportEventsByStatusResponse();
        response.setSportEvents(new SportEvents(sportEventRepository.findByStatus(request.getStatus())));
        return response;
    }

    public GetSportEventsByNameResponse getSportEventsByName(GetSportEventsByNameRequest request)
    {
        GetSportEventsByNameResponse response = new GetSportEventsByNameResponse();
        response.setSportEvents(new SportEvents(sportEventRepository.findSportEventByName(request.getName())));
        Assert.notNull(sportEventRepository.findSportEventByName(request.getName()),"Sport event with name '" +  request.getName() +"' not found!");
        return response;
    }

    public AddSportEventResponse addSportEvent(AddSportEventRequest request)
    {
        AddSportEventResponse response = new AddSportEventResponse();
        response.setStatus(sportEventRepository.addSportEvent(request.getSportEventDTO()));
        return response;
    }

    public RemoveSportEventByIdResponse removeSportEvent(RemoveSportEventByIdRequest request)
    {
        RemoveSportEventByIdResponse response = new RemoveSportEventByIdResponse();
        sportEventRepository.removeSportEventById(request.getId());
        response.setStatus("Successful");
        return response;
    }

    public GetByLocationResponse getByLocation(GetByLocationRequest request)
    {
        GetByLocationResponse response = new GetByLocationResponse();
        response.setSportEvents(new SportEvents(sportEventRepository.getByLocation(request.getLocation())));
        Assert.notNull(sportEventRepository.getByLocation(request.getLocation()),"Sport event with location '" +  request.getLocation() +"' not found!");
        return response;
    }
}

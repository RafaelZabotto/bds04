package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> page = eventRepository.findAll(pageable);
        return page.map(EventDTO::new);
    }

    public EventDTO insert(EventDTO dto) {
        Event event = new Event();
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        event.setCity(new City(dto.getCityId(), null));
        event = eventRepository.save(event);
        return new EventDTO(event);
    }

    /*
    public Page<EmployeeDTO> findAll(Pageable pageable) {
		Page<Employee> page = repository.findAll(pageable);
		return page.map(x -> new EmployeeDTO(x));
	}
     */
}

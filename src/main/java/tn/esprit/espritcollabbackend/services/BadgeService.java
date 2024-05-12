package tn.esprit.espritcollabbackend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.espritcollabbackend.entities.Event;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.EventRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;

@Service
public class BadgeService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;




}

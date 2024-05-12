package tn.esprit.espritcollabbackend.services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.espritcollabbackend.entities.Badge;
import tn.esprit.espritcollabbackend.repositories.BadgeRepository;

@Component
public class BadgeInitializer {

    @Autowired
    private BadgeRepository badgeRepository;

    @PostConstruct
    public void initBadges() {
        if (badgeRepository.count() == 0) { // Check to prevent re-insertion on application restart
            badgeRepository.save(new Badge("Nerd", "http://localhost:8087/uploads/Nerd.png", 96));
            badgeRepository.save(new Badge("Performer", "http://localhost:8087/uploads/Preformer.png", 48));
            badgeRepository.save(new Badge("Acrobat", "http://localhost:8087/uploads/Acrobat.png", 24));
            badgeRepository.save(new Badge("Ice Breaker", "http://localhost:8087/uploads/icebreaker.png", 12));
            badgeRepository.save(new Badge("Amateur", "http://localhost:8087/uploads/Amateur.png", 4));
            badgeRepository.save(new Badge("Newbie", "http://localhost:8087/uploads/Newbie.png", 1));
            badgeRepository.save(new Badge("New", "http://localhost:8087/uploads/Nerd.png", 0));
        }
    }
}


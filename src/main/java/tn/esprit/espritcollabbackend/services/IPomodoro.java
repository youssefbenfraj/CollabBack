package tn.esprit.espritcollabbackend.services;


import tn.esprit.espritcollabbackend.entities.Pomodoro;

public interface IPomodoro {
    //public Pomodoro saveOrUpdatePomodoro(Pomodoro pomodoro);
    public Pomodoro saveOrUpdatePomodoro(Pomodoro pomodoro, Long userId);

}

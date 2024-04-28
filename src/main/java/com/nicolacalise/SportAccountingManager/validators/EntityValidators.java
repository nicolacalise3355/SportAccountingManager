package com.nicolacalise.SportAccountingManager.validators;

import com.nicolacalise.SportAccountingManager.models.entities.User;
import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import org.springframework.stereotype.Component;

@Component
public class EntityValidators {

    public boolean isWorkdayValid(Workday w){
        return (
            w.getDate() != null &&
            w.getCosts() > 0 &&
            w.getRevenue() > 0
        );
    }

    public boolean isUserValid(User u){
        return (
            u.getEmail() != null && u.getUsername() != null && u.getPassword() != null
        );
    }
}

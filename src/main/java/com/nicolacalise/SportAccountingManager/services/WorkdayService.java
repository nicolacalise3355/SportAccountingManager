package com.nicolacalise.SportAccountingManager.services;

import com.nicolacalise.SportAccountingManager.dao.WorkdayDAO;
import com.nicolacalise.SportAccountingManager.exceptions.WorkdayException;
import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkdayService {

    private WorkdayDAO workdayDao;

    @Autowired
    public WorkdayService(WorkdayDAO workdayDao){
        this.workdayDao = workdayDao;
    }

    /**
     * @return List of Workday
     */
    public List<Workday> getWorkdays(){
        return this.workdayDao.findAll();
    }

    public void insertWorkday(Workday w) throws WorkdayException {
        if(w == null) throw new WorkdayException("Workday not valid", 0);
        this.workdayDao.save(w);
    }

}

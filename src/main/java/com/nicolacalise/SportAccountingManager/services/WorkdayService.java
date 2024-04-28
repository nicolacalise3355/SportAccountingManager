package com.nicolacalise.SportAccountingManager.services;

import com.nicolacalise.SportAccountingManager.dao.WorkdayDAO;
import com.nicolacalise.SportAccountingManager.exceptions.types.WorkdayException;
import com.nicolacalise.SportAccountingManager.exceptions.types.WorkdayNotFoundException;
import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    /**
     *
     * @param id workday id
     * @return Workday object
     * @throws WorkdayException id not valid
     * @throws NoSuchElementException workday not found
     */
    public Workday getWorkday(int id) throws WorkdayException, WorkdayNotFoundException {
        if(id < 0) throw new WorkdayException("Id not valid", 0);
        return this.workdayDao.findById(id).orElseThrow(() -> new WorkdayNotFoundException("Workday not found!", 0));
    }
}

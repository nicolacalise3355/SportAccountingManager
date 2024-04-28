package com.nicolacalise.SportAccountingManager.services;

import com.nicolacalise.SportAccountingManager.dao.WorkdayDAO;
import com.nicolacalise.SportAccountingManager.exceptions.types.WorkdayException;
import com.nicolacalise.SportAccountingManager.exceptions.types.WorkdayNotFoundException;
import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import com.nicolacalise.SportAccountingManager.staticValues.WorkdayMessages;
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
        if(w == null) throw new WorkdayException(WorkdayMessages.WORKDAY_NOT_VALID, 0);
        this.workdayDao.save(w);
    }

    /**
     *
     * @param id workday id
     * @return Workday object
     * @throws WorkdayException id not valid
     * @throws NoSuchElementException workday not found
     */
    public Workday getWorkday(int id) throws WorkdayNotFoundException {
        return this.workdayDao.findById(id).orElseThrow(() -> new WorkdayNotFoundException(WorkdayMessages.WORKDAY_NOT_FOUND, 0));
    }

    /**
     *
     * @param id workday id
     * @param w Workday to set
     * @throws WorkdayNotFoundException workday not found
     */
    public void updateWorkday(int id, Workday w) throws WorkdayNotFoundException {
        Workday tmp = this.workdayDao.findById(id).orElseThrow(() -> new WorkdayNotFoundException(WorkdayMessages.WORKDAY_NOT_FOUND, 0));
        tmp.setDate(w.getDate());
        tmp.setCosts(w.getCosts());
        tmp.setRevenue(w.getRevenue());
        this.workdayDao.save(tmp);
    }

    /**
     *
     * @param id workday id
     * @throws WorkdayNotFoundException workday not found
     */
    public void deleteWorkday(int id) throws WorkdayNotFoundException {
        Workday tmp = this.workdayDao.findById(id).orElseThrow(() -> new WorkdayNotFoundException(WorkdayMessages.WORKDAY_NOT_FOUND, 0));
        this.workdayDao.delete(tmp);
    }
}

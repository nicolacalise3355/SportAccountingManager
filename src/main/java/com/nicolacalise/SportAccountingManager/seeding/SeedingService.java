package com.nicolacalise.SportAccountingManager.seeding;

import com.nicolacalise.SportAccountingManager.dao.MemberDAO;
import com.nicolacalise.SportAccountingManager.dao.WorkdayDAO;
import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeedingService {

    private SeedingProvider seedingProvider;

    private WorkdayDAO workdayDAO;
    private MemberDAO memberDAO;

    @Autowired
    public SeedingService(WorkdayDAO workdayDAO, MemberDAO memberDAO, SeedingProvider seedingProvider) {
        this.workdayDAO = workdayDAO;
        this.memberDAO = memberDAO;
        this.seedingProvider = seedingProvider;
    }

    public void initDatabase(){
        //Separa salvataggio di un member con gli attendance
        //mettili dopo
        //fai la save di un mmeber, ti ritorna member con id, setti il memner dentro l attendance e salvi attendance
        this.workdayDAO.saveAll(this.seedingProvider.generateWorkdays());
        this.memberDAO.saveAll(this.seedingProvider.generateMembersWithAttendance());
    }

    public void cleanDatabase() {
        this.workdayDAO.deleteAll();
        this.memberDAO.deleteAll();
    }

    public void seedWorkdays(){
        this.workdayDAO.saveAll(this.seedingProvider.generateWorkdays());
    }

    public void seedMembersWithAttendance(){
        this.memberDAO.saveAll(this.seedingProvider.generateMembersWithAttendance());
    }

    public void cleanWorkdays(){
        this.workdayDAO.deleteAll();
    }

    public void cleanMembers(){
        this.memberDAO.deleteAll();
    }
}

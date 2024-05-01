package com.nicolacalise.SportAccountingManager.seeding;

import com.nicolacalise.SportAccountingManager.dao.AttendanceDAO;
import com.nicolacalise.SportAccountingManager.dao.MemberDAO;
import com.nicolacalise.SportAccountingManager.dao.WorkdayDAO;
import com.nicolacalise.SportAccountingManager.models.entities.Member;
import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeedingService {

    private SeedingProvider seedingProvider;

    private WorkdayDAO workdayDAO;
    private MemberDAO memberDAO;
    private AttendanceDAO attendanceDAO;

    @Autowired
    public SeedingService(WorkdayDAO workdayDAO, MemberDAO memberDAO, AttendanceDAO attendanceDAO, SeedingProvider seedingProvider) {
        this.workdayDAO = workdayDAO;
        this.memberDAO = memberDAO;
        this.attendanceDAO = attendanceDAO;
        this.seedingProvider = seedingProvider;
    }

    public void initDatabase(){
        this.seedWorkdays();
        this.seedMember();
    }

    public void cleanDatabase() {
        this.workdayDAO.deleteAll();
        this.memberDAO.deleteAll();
    }

    public void seedWorkdays(){
        this.workdayDAO.saveAll(this.seedingProvider.generateWorkdays());
    }

    public void seedMember(){
        this.memberDAO.saveAll(this.seedingProvider.generateMembersWithoutAttendance());

        Member tmp1 = this.memberDAO.save(new Member("Nicola", "Calise", 6));
        this.seedAttendanceWithMember(tmp1);
    }

    public void seedAttendanceWithMember(Member m){
        this.attendanceDAO.saveAll(this.seedingProvider.generateAttendancesWithMember(m));
    }

    public void cleanWorkdays(){
        this.workdayDAO.deleteAll();
    }

    public void cleanMembers(){
        this.memberDAO.deleteAll();
        this.attendanceDAO.deleteAll();
    }
}

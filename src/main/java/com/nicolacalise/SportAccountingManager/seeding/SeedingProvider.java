package com.nicolacalise.SportAccountingManager.seeding;

import com.nicolacalise.SportAccountingManager.models.entities.Attendance;
import com.nicolacalise.SportAccountingManager.models.entities.Member;
import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedingProvider {

    public Iterable<Workday> generateWorkdays() {
        List<Workday> l = new ArrayList<>();
        l.add(new Workday("01/01/2024", 300, 70));
        l.add(new Workday("10/03/2024", 150, 40));
        l.add(new Workday("12/02/2024", 370, 40));
        l.add(new Workday("13/02/2024", 110, 50));
        l.add(new Workday("14/01/2024", 310, 40));
        return l;
    }

    public Iterable<Member> generateMembersWithoutAttendance() {
        List<Member> l = new ArrayList<>();
        l.add(new Member("Francesco", "Verdi", 6));
        l.add(new Member("Mario", "Bianchi", 5));
        l.add(new Member("Marco", "Rossi", 1));
        return l;
    }

    public Iterable<Attendance> generateAttendancesWithMember(Member m){
        List<Attendance> l = new ArrayList<>();
        l.add(new Attendance("01/01/2024", m));
        l.add(new Attendance("02/01/2024", m));
        return l;
    }
}

package com.nicolacalise.SportAccountingManager.services;

import com.nicolacalise.SportAccountingManager.dao.MemberDAO;
import com.nicolacalise.SportAccountingManager.exceptions.types.MemberException;
import com.nicolacalise.SportAccountingManager.exceptions.types.MemberNotFoundException;
import com.nicolacalise.SportAccountingManager.models.entities.Member;
import com.nicolacalise.SportAccountingManager.staticValues.MemberMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private MemberDAO memberDAO;

    @Autowired
    public MemberService(MemberDAO memberDAO){ this.memberDAO = memberDAO; }

    /**
     * @return List of Member
     */
    public List<Member> getMembers(){ return this.memberDAO.findAll(); }

    /**
     *
     * @param m Member
     * @throws MemberException Member not valid
     */
    public void insertMember(Member m) throws MemberException {
        if(m == null) throw new MemberException(MemberMessages.MEMBER_NOT_VALID);
        this.memberDAO.save(m);
    }

    /**
     *
     * @param id member id
     * @return m member object
     * @throws MemberNotFoundException member not found
     */
    public Member getMember(int id) throws MemberNotFoundException {
        return this.memberDAO.findById(id).orElseThrow(() -> new MemberNotFoundException(MemberMessages.MEMBER_NOT_FOUND, 0));
    }

    /**
     *
     * @param id member id
     * @param m member object
     * @throws MemberNotFoundException member not found
     */
    public void updateMember(int id, Member m) throws MemberNotFoundException {
        Member tmp = this.memberDAO.findById(id).orElseThrow(() -> new MemberNotFoundException(MemberMessages.MEMBER_NOT_FOUND, 0));
        tmp.setName(m.getName());
        tmp.setSurname(m.getSurname());
        tmp.setLevel(m.getLevel());
        this.memberDAO.save(tmp);
    }

    /**
     *
     * @param id member id
     * @throws MemberNotFoundException member not found
     */
    public void deleteMember(int id) throws MemberNotFoundException {
        Member tmp = this.memberDAO.findById(id).orElseThrow(() -> new MemberNotFoundException(MemberMessages.MEMBER_NOT_FOUND, 0));
        //delete all attendance?
        this.memberDAO.delete(tmp);
    }

    //update attendace of member
    //insert attendace of member
    //remove attendance of member

}

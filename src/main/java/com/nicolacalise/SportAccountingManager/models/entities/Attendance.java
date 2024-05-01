package com.nicolacalise.SportAccountingManager.models.entities;

import jakarta.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName = "id", nullable=false)
    private Member member;

    public Attendance(String date) {
        this.date = date;
    }

    public Attendance(String date, Member m){
        this.date = date;
        this.member = m;
    }

    public Attendance() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

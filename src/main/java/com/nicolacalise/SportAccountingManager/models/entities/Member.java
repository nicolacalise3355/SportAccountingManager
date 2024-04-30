package com.nicolacalise.SportAccountingManager.models.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String surname;

    @Column(nullable = true, length = 100)
    private int level;

    @OneToMany(mappedBy="member")
    Set<Attendance> attendances;

    public Member(String name, String surname, int level) {
        this.name = name;
        this.surname = surname;
        this.level = level;
    }

    public Member(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Member() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }
}

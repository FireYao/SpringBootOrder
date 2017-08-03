package com.simpletour.java8;

import java.time.LocalDate;

class Student {
    private int score;
    private String name;
    private LocalDate birthday;

    public Student() {
    }

    public Student(int score, String name, LocalDate birthday) {
        this.score = score;
        this.name = name;
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Student(int score, String name) {
        this.score = score;
        this.name = name;
        this.birthday = LocalDate.now();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

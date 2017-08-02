package com.simpletour.java8;

class Person {
    private int age;

    public Person() {
        this.age = (int) (Math.random() * 100 + 1);
    }

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}

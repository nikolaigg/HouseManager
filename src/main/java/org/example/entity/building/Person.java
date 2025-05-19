package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    private String name;
    private int age;

    public Person() {

    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

package com.localnews.localnews.models.PollModels;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class PollModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollOption> options;

    public PollModel() {
        this.options = new ArrayList<>();
    }

    public PollModel(Long id, String question, List<PollOption> options) {
        this.id = id;
        this.question = question;
        this.options = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<PollOption> getOptions() {
       // if (options != null) {
         //   options.sort(Comparator.comparingLong(PollOption::getId));
        //}
        return options;
    }

    public void setOptions(List<PollOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "PollModel{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", options=" + options +
                '}';
    }
}

package code.lilasz.mongo.m101j.homework31.model;

import org.jongo.marshall.jackson.oid.Id;

import java.util.List;

/**
 * Created by lilasz on 2014-06-16.
 */
public class Student {
    @Id
    private Integer index;

    private String name;
    private List<Score> scores;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}

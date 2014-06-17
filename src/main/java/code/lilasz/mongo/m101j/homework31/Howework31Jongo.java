package code.lilasz.mongo.m101j.homework31;

import code.lilasz.mongo.m101j.homework31.model.Score;
import code.lilasz.mongo.m101j.homework31.model.Student;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;
import java.util.Comparator;
import java.util.List;

public class Howework31Jongo {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        Jongo jongo = new Jongo(client.getDB("school"));
        MongoCollection students = jongo.getCollection("studentsjongo");
        students.find().as(Student.class)
                .forEach(student -> students.update("{_id: #}", student.getIndex()).with("{$pull: {scores: #}}", getLowestHomeWorkScore(student.getScores())));
    }

    static Score getLowestHomeWorkScore(final List<Score> scores) {
        return scores.stream()
                .filter(d -> d.getType().equals("homework"))
                .min(Comparator.comparing(Score::getScore)).get();
    }

}

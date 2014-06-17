package code.lilasz.mongo.m101j.homework31;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Homework31 {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        removeLowestHomeWorkScore(client.getDB("school").getCollection("students"));
    }

    static void removeLowestHomeWorkScore(DBCollection students) {
        DBCursor cursor = students.find();
        try {
            while (cursor.hasNext()) {
                DBObject student = cursor.next();
                List<BasicDBObject> scores = (List<BasicDBObject>) student.get("scores");
                Optional<BasicDBObject> lowestHomeWorkScore = getLowestHomeWorkScore(scores);
                if (lowestHomeWorkScore.isPresent()) {
                    BasicDBObject update = new BasicDBObject("scores", lowestHomeWorkScore.get());
                    students.update(student, new BasicDBObject("$pull", update));
                }
            }
        } finally {
            cursor.close();
        }
    }

    static Optional<BasicDBObject> getLowestHomeWorkScore(final List<BasicDBObject> scores) {
        return scores.stream()
                .filter(d -> d.getString("type").equals("homework"))
                .min(Comparator.comparing(d -> d.getDouble("score")));
    }
}

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.sql.Time;

public class Message {


    //final HttpResponse<String> MESSAGES = Unirest.get("http://zipcode.rocks:8085/messages").asString();

    private String sequence;
    private String timeStamp;
    private String fromId;
    private String toId;
    private String message;



    public Message() throws UnirestException {
    }

    public Message(String sequence, String timeStamp, String fromId, String toId, String message) {
        this.sequence = sequence;
        this.timeStamp = timeStamp;
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
    }

    public Message(String fromId, String toId, String message) {
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sequence:'" + sequence + '\'' +
                ", timeStamp:'" + timeStamp + '\'' +
                ", fromId:'" + fromId + '\'' +
                ", toId:'" + toId + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }
}

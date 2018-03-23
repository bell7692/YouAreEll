import com.mashape.unirest.http.exceptions.UnirestException;

public class Message {


    //final HttpResponse<String> MESSAGES = Unirest.get("http://zipcode.rocks:8085/messages").asString();

    private String sequence;
    private String timestamp;
    private String fromid;
    private String toid;
    private String message;



    public Message() throws UnirestException {
    }

    public Message(String sequence, String timestamp, String fromId, String toid, String message) {
        this.sequence = sequence;
        this.timestamp = timestamp;
        this.fromid = fromId;
        this.toid = toid;
        this.message = message;
    }

    public Message(String fromId, String toid, String message) {
        this.fromid = fromId;
        this.toid = toid;
        this.message = message;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
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
                ", timestamp:'" + timestamp + '\'' +
                ", fromid:'" + fromid + '\'' +
                ", toid:'" + toid + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }
}

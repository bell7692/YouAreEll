import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.io.IOException;
import java.util.List;

public class YouAreEll {

    YouAreEll() {
    }



    public static void main(String[] args) throws IOException {
        YouAreEll urlhandler = new YouAreEll();
     //   System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
      //  System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
     //   urlhandler.postId("Bo", "bell7692");
     //   urlhandler.postMessage("bell7692", "kfennimore", "The best is yet to come");
     // urlhandler.get_messages();
        urlhandler.messageParse();


    }
    public void postId (String name, String githubID) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Id myId = new Id(name, githubID);
        String payload = objectMapper.writeValueAsString(myId);
        MakeURLCall("/ids", "POST", payload);

    }

    public void postMessage(String fromid, String toid, String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Message myMessage = new Message(fromid, toid, message);
        String payload = objectMapper.writeValueAsString(myMessage);
        String url = "/ids/" +toid +"/messages";
        MakeURLCall(url, "POST", payload);
    }
    public String get_ids() {
        return MakeURLCall("/ids", "GET", "");
    }


    public String messageParse() throws IOException {
        String str = MakeURLCall("/messages", "GET", "");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Message> message = objectMapper.readValue(str, new TypeReference<List<Message>>(){});

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.size(); i++) {
            sb.append("Time: " +message.get(i).getTimestamp() + "\n");
            sb.append("From: " +message.get(i).getFromid() + "\n");
            sb.append("To: " +message.get(i).getToid() + "\n");
            sb.append("Message: " +message.get(i).getMessage() + "\n\n");
        }
        System.out.println(sb.toString());
        return sb.toString();

    }
    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        String url= "http://zipcode.rocks:8085" + mainurl;
        GetRequest request = Unirest.get(url);
        HttpRequestWithBody requestWithBody = Unirest.post(url);
        HttpRequestWithBody requestWithBody1 = Unirest.put(url);

        if(method.equals("GET")){
            try {
                return request.asJson().getBody().toString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }

        else if(method.equals("POST")){
            try {
                return requestWithBody.body(jpayload).asJson().getBody().toString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }

//        else if (method.equals("PUT")){
//            try {
//                return requestWithBody1.body(jpayload).asJson().getBody().toString();
//            } catch (UnirestException e) {
//                e.printStackTrace();
//            }
//        }

        return "nada";
    }
}

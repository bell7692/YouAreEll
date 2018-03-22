import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Id {

    private String userId;
    private String name;
    private String github;


    public Id() throws UnirestException {
    }

    public Id(String name, String github) {
        this.name = name;
        this.github = github;
    }

    public Id(String userId, String name, String github) {
        this.userId = userId;
        this.name = name;
        this.github = github;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return "Id{" +
                "userId:'" + userId + '\'' +
                ", name:'" + name + '\'' +
                ", github:'" + github + '\'' +
                '}';
    }
}

package apimocking.dto;


public class ServiceData {

    private String status;

    private String userId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String id;

    private String title;

    private String body;

    private Data[] data;

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

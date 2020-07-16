package sample.Class;

public class Feedback {
    private int idFeedback;
    private String title;
    private String content;
    private String date;
    private String status;
    private int note;
    private String platform;
    private int idAssociation;
    private int idAdmin;
    private int idUser;

    public Feedback(String content, String date, int note, String platform, int idUser) {
        this.content = content;
        this.date = date;
        this.note = note;
        this.platform = platform;
        this.idUser = idUser;
    }

    public Feedback(String content, String date, int note, int idAssociation, String platform) {
        this.content = content;
        this.date = date;
        this.note = note;
        this.idAssociation = idAssociation;
        this.platform = platform;
    }

    public Feedback(int idFeedback, String title, String content, String date, String status, String platform) {
        this.idFeedback = idFeedback;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.platform = platform;
    }

    public int getNote() {
        return note;
    }

    public String getPlatform() {
        return platform;
    }

    public int getIdfe() {
        return idFeedback;
    }
    
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public int getIdas() {
        return idAssociation;
    }

    public int getIdu() {
        return idUser;
    }

}

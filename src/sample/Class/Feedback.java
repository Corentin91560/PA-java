package sample.Class;

public class Feedback {
    private int idfeedback;
    private String title;
    private String content;
    private String date;
    private String status;
    private int note;
    private int idassociation;
    private int idadmin;
    private int iduser;
    private String platform;

    public Feedback(String content, String date, int note, String platform, int iduser) {
        this.content = content;
        this.date = date;
        this.note = note;
        this.platform = platform;
        this.iduser = iduser;
    }

    public Feedback(String content, String date, int note, int idassociation, String platform) {
        this.content = content;
        this.date = date;
        this.note = note;
        this.idassociation = idassociation;
        this.platform = platform;
    }

    public Feedback(int idfeedback, String title, String content, String date, String status, String platform) {
        this.idfeedback = idfeedback;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.platform = platform;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getPlateform() {
        return platform;
    }

    public void setPlateform(String platform) {
        this.platform = platform;
    }

    public int getIdfe() {
        return idfeedback;
    }

    public void setIdfe(int idfeedback) {
        this.idfeedback = idfeedback;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdas() {
        return idassociation;
    }

    public void setIdas(int idassociation) {
        this.idassociation = idassociation;
    }

    public int getIda() {
        return idadmin;
    }

    public void setIda(int idadmin) {
        this.idadmin = idadmin;
    }

    public int getIdu() {
        return iduser;
    }

    public void setIdu(int iduser) {
        this.iduser = iduser;
    }
}

package sample.Class;

public class Feedback {
    int idfeedback;
    String title;
    String content;
    String date;
    String status;
    int note;
    int idassociation;
    int idadmin;
    int iduser;
    String plateform;

    public Feedback(String content, String date, int note, String plateform,int iduser) {
        this.content = content;
        this.date = date;
        this.note = note;
        this.plateform = plateform;
        this.iduser = iduser;
    }

    public Feedback(String content, String date, int note, int idassociation, String plateform) {
        this.content = content;
        this.date = date;
        this.note = note;
        this.idassociation = idassociation;
        this.plateform = plateform;
    }

    public Feedback(int idfeedback, String title, String content, String date, String status, String plateform) {
        this.idfeedback = idfeedback;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.plateform = plateform;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getPlateform() {
        return plateform;
    }

    public void setPlateform(String plateform) {
        this.plateform = plateform;
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

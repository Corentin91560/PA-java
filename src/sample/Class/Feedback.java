package sample.Class;

public class Feedback {
    int idfe;
    String title;
    String content;
    String date;
    String status;
    int idas;
    int ida;
    int idu;
    String plateform;

    public Feedback(int idfe, String title, String content, String date, String status, int idas, int ida, int idu,String plateform) {
        this.idfe = idfe;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.idas = idas;
        this.ida = ida;
        this.idu = idu;
        this.plateform = plateform;
    }

    public Feedback(int idfe, String title, String content, String date, String status,String plateform) {
        this.idfe = idfe;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.plateform = plateform;
    }

    public String getPlateform() {
        return plateform;
    }

    public void setPlateform(String plateform) {
        this.plateform = plateform;
    }

    public int getIdfe() {
        return idfe;
    }

    public void setIdfe(int idfe) {
        this.idfe = idfe;
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

    public  String getDate() {
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
        return idas;
    }

    public void setIdas(int idas) {
        this.idas = idas;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }
}

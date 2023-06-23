package app.card.model;

public class Records {
    private String ID;
    private String title;
    private String titleMore;
    private String revision;
    private String data;
    public Records(){}

    public String getID() {
        return ID;
    }
    public String getTitle() {
        return title;
    }
    public String getTitleMore() {
        return titleMore;
    }
    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Records(String ID, String title, String titleMore, String revision, String data) {
        this.ID = ID;
        this.title = title;
        this.titleMore = titleMore;
        this.revision = revision;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Records{" +
                "ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", titleMore='" + titleMore + '\'' +
                ", revision='" + revision + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

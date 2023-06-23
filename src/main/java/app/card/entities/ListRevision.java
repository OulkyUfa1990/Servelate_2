package app.card.entities;

public class ListRevision {
    private String id;
    private String rev;
    public ListRevision(){}

    @Override
    public String toString() {
        return "ListRevision{" +
                "id='" + id + '\'' +
                ", rev='" + rev + '\'' +
                '}';
    }

    public ListRevision(String id, String rev) {
        this.id = id;
        this.rev = rev;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }
}

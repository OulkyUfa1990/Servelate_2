package app.card.model;

//Работает в паре с CreateID методом id_two()
public class CreatRevision {
    public static String newRevision(String ID){
        String[] words = ID.split("\\.");
        String rev = words[1];
        return rev;
    }
}

package app.card.model;

import app.card.entities.ListRevision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateID {

    //Присвоение нового ID
    public static String id_first(String k){
        String k3;
        String[] words = k.split("\\.");
        int k1 = Integer.parseInt(words[0]);
        k1++;
        String k2 = String.valueOf(k1);

        if (k2.length()==2){
            k3 = "00" + k2 + ".00";
        } else if (k2.length()==3) {
            k3 = "0" + k2 + ".00";
        } else if (k2.length()==4) {
            k3 = k2;
        } else k3 = "000" + k2 + ".00";
        return k3; //окончательный ID
    }

    //Берет List с ID. Перерводит в цифру и упорядочивает
    public static String id_sort(ArrayList<String> list){
        List<Float> listFlo = new ArrayList<>();
        for (String i : list){
            listFlo.add(Float.valueOf(i));
        }
        Collections.sort(listFlo);
        int l = listFlo.size();
        return String.valueOf(listFlo.get(l-1)); //Максимальное из отсортерованных
    }

    //Присвоение ID ревизии
    public static String id_two(ArrayList<ListRevision> ID){
        String k5;
        ArrayList<String> list = new ArrayList<>();
        for (ListRevision i : ID){
            list.add(i.getId());
        }
        Collections.sort(list);
        int l = list.size();
        String k1 = list.get(l-1);
        String[] words_2 = k1.split("\\.");
        String k2 = words_2[0];
        String k3 = words_2[1];
        int k4 = Integer.parseInt(k3);
        if (k4 >=9){
            k4++;
            k5 = k2 + "." + k4;
        } else {
            k4++;
            k5 = k2 + ".0" + k4;
        }
        return k5;//"Новый присвоенный ID для ревизии
    }
}
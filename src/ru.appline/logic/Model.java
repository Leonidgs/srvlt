package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;

public class Model implements Serializable {
    private static final Model instanse = new Model();
    private final HashMap<Integer, User> model;

    public static Model getInstance() {
        return instanse;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Anton", "Pushkin", 300));
        model.put(2, new User("Vasya", "Pupkin", 500));
        model.put(3, new User("Grisha", "Zabolotskiy", 100));

    }

    public void add(User user,int id) {
        model.put(id, user);
    }

    public HashMap<Integer, User> getFromList() {
        return model;
    }
    public void remove(int id) {
        model.remove(id);
    }
    public HashMap<Integer, User> get(int id) {
        HashMap map = new HashMap();
        map.put(id,model.get(id));
        return map;
    }

    public void change(User user,int id) {
        model.put(id, user);
    }
}

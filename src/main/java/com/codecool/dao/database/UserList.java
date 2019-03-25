package com.codecool.dao.database;

import com.codecool.model.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserList implements Serializable {
    private static UserList ourInstance = new UserList();
    private List<User> userList;

    public static UserList getInstance() {
        return ourInstance;
    }

    private UserList() {
        userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public List<User> getUsers() {
        return userList;
    }

    public void saveUserList(List<User> userList, String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(userList);
        out.close();
        fileOut.close();
    }

    @SuppressWarnings("unchecked")
    public void loadUserList(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        userList = (List<User>) in.readObject();
        in.close();
        fileIn.close();
    }
}

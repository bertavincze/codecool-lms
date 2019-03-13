package com.codecool.database;

import com.codecool.model.curriculum.Page;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PageList implements Serializable {

    private static PageList ourInstance = new PageList();
    private List<Page> pageList;

    private PageList() {
        this.pageList = new ArrayList<>();
    }

    public static PageList getInstance() {
        return ourInstance;
    }

    public void addPage(Page page) {
        pageList.add(page);
    }

    public List<Page> getPageList() {
        return pageList;
    }

    public void savePageList(List<Page> pageList, String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(pageList);
        out.close();
        fileOut.close();
    }

    @SuppressWarnings("unchecked")
    public void loadPageList(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        pageList = (List<Page>) in.readObject();
        in.close();
        fileIn.close();
    }
}

package com.codecool.model;

import java.io.*;

public class Image {
    private String image_id;
    private byte[] imageBytea;
    private String name;

    public Image(String id, String filepath, String name) throws FileNotFoundException, IOException{
        this.imageBytea = ImageToByte(filepath);
        this.name = name;
        this.image_id = id;
    }

    public Image(String id, byte[] imageBytea, String name){
        this.imageBytea = imageBytea;
        this.name = name;
        this.image_id = id;
    }


    public String getName() {
        return name;
    }

    public byte[] getImageBytea() {
        return imageBytea;
    }

    public String getImage_id() {
        return image_id;
    }


    private byte [] ImageToByte(String filepath) throws FileNotFoundException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try (FileInputStream fis = new FileInputStream(filepath)){
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        }

        imageBytea = bos.toByteArray();

        return imageBytea;
    }

}

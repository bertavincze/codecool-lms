package com.codecool.service;

import java.util.Random;

public class IDGeneratorService {

    private final String[] characters;
    private final Random random;

    public IDGeneratorService() {
        this.characters = new String []{"0123456789", "abcdefghijklmnopqrstuvwxyz", "@&#,.-+/*!"};
        this.random = new Random();
    }

    public String generateID() {
        StringBuilder id = new StringBuilder();
        for (String sequence: characters) {
            id.append(sequence.charAt(random.nextInt(sequence.length())));
            id.append(sequence.charAt(random.nextInt(sequence.length())));
        }
        return id.toString();
    }
}

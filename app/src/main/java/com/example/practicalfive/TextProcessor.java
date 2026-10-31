package com.example.practicalfive;

public class TextProcessor {
    public String processMunicipality(String municipality){
        if(municipality == null || municipality.trim().isEmpty()){
            return null;
        }
        String cleanInput = municipality.trim().toLowerCase();
        switch (cleanInput){
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "chongqing":
                return "Chongqing";
            case "tianjin":
                return "Tianjin";
            default:
                return null;
        }
    }
}

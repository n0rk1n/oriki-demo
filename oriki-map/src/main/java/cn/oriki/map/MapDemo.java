package cn.oriki.map;

import java.util.HashMap;

public class MapDemo {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        String get = map.get("nihao");
        System.out.println(get);// null

        String mapOrDefault = map.getOrDefault("nihao", "meiyou");
        System.out.println(mapOrDefault);// meiyou
    }
}

package com.songgj.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        ArrayList<Heroes> list = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(Paths.get("heroes.txt"), Charset.forName("UTF-8"));
            lines.forEach(line -> {
                String[] strings = line.split("\t");
                Heroes hero = new Heroes(Integer.parseInt(
                        strings[0]), strings[1], strings[2], strings[3], Integer.parseInt(strings[4]),
                        Integer.parseInt(strings[5]), Integer.parseInt(strings[6]));
                list.add(hero);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //武力值前三名
        list.stream().sorted( (hero1, hero2) -> hero2.getPower() - hero1.getPower()
        ).limit(3).forEach(hero -> System.out.println(hero.getName()));
        System.out.println("==================================================");
        //按出生地分组
        Map<String, List<Heroes>> map1 = list.stream().collect(Collectors.groupingBy((hero) -> hero.getLocation()));
        map1.forEach((location, hero) -> System.out.println(location + ":" +
                hero.stream().map(h -> h.getName()).collect(Collectors.toList())));
        System.out.println("==================================================");
        //找出寿命前三的武将
        list.stream().sorted((hero1, hero2) ->
                (hero2.getDeath() - hero2.getBirth()) - (hero1.getDeath() - hero1.getBirth())).limit(3).forEach(
                hero -> System.out.println(hero.getName()));
        System.out.println("==================================================");
        //女性寿命最高的
        list.stream().filter(hero -> hero.getSex().equals("女")).sorted((hero1, hero2) ->
                (hero2.getDeath() - hero2.getBirth()) - (hero1.getDeath() - hero1.getBirth())).limit(1).forEach(
                hero -> System.out.println(hero.getName()));
        System.out.println("==================================================");
        //找出武力排名前三
        Set<Integer> powerSet = list.stream().map(hero -> hero.getPower()).sorted(
                (power1, power2) -> power2 - power1).limit(3).collect(Collectors.toSet());
        list.stream().filter(hero -> powerSet.contains(hero.getPower())).forEach((hero) -> System.out.println(hero.getName()));
        System.out.println("==================================================");
        //按各个年龄段分组
        Map<String, List<Heroes>> ageMap = list.stream().collect(Collectors.groupingBy((hero) -> {
            int age = hero.getDeath() - hero.getBirth();
            if (age <= 20) {
                return "0-20";
            } else if (age <= 40) {
                return "21-40";
            } else if (age <= 60) {
                return "41-60";
            } else
                return "61以上";
        }));
        ageMap.forEach((ageGroup, hero) -> System.out.println(ageGroup + ":" +
                hero.stream().map(h -> h.getName()).collect(Collectors.toList())));
        System.out.println("==================================================");
        //按武力段分组
        Map<String, List<Heroes>> powerMap = list.stream().collect(Collectors.groupingBy((hero) -> {
            if (hero.getPower() < 70) {
                return "70以下";
            } else if (hero.getPower() < 80) {
                return "70-80";
            } else if (hero.getPower() < 90) {
                return "80-90";
            } else
                return "90以上";
        }));
        powerMap.forEach((powerGroup, hero) -> System.out.println(powerGroup + ":" +
                hero.stream().map(h -> h.getName()).collect(Collectors.toList())));
        System.out.println("==================================================");
        //按出生地分组后，统计各组人数
        Map<String, List<Heroes>> map2 = list.stream().collect(Collectors.groupingBy((hero) -> hero.getLocation()));

        map2.forEach((location, hero) -> System.out.println(location + ":" +
                hero.stream().map(h -> h.getName()).collect(Collectors.toList()) + "\n" +
                "共" + hero.stream().map(h -> h.getName()).collect(Collectors.toList()).size() + "人"));
    }
}

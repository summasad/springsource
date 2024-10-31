package lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class ForEachEx2 {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(new Student("홍길동", 90, 96),
                new Student("김수정", 80, 85));

        for (Student student : list) {
            System.out.println(student.getName() + ": " + student.getEng() + ": " + student.getMath());
        }

        list.forEach((student) -> {
            System.out.println(student.getName() + ": " + student.getEng() + ": " + student.getMath());
        });

        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);

        System.out.println(items.get("A"));

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }

        // List : 데이터 중복 가능, 순서 있음, 향상된 for 가능
        // Set : 데이터 중복 불가, 순서 의미 없음, 향상된 for 가능
        // Map : key 값 중복 불가, value 중복 가능, 향상된 for 불가

        // => 접근성 통일하자 : Iterator
        Set<String> ketSet = items.keySet();
        Iterator<String> keyIterator = ketSet.iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            Integer value = items.get(key);
            System.out.println("item : " + key + " Count : " + value);
        }

        // Bi : 2개의 인수를 넣어 1개의 값을 도출할때
        // Consumer : c.accpet(인수)
        items.forEach((k, v) -> {
            System.out.println("item : " + k + " Count : " + v);
        });
    }
}

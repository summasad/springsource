package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ForEachEx1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("사과", "딸기", "수박", "바나나", "배", "메론");
        for (String s : list) {
            System.out.println(s);
        }

        // <? super String> : 부모는 String 타입이나 상위 타입만(List 의 타입만)
        // Consumer<String> c = (x) -> System.out.println(x);
        // c.accept("")

        // forEach : 하나씩 꺼내서 출력하는 용도로 주로 사용
        list.forEach((x) -> System.out.println(x));
    }
}

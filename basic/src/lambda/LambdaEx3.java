package lambda;

@FuntionalInterface
interface Lambda3 {
    void method(int x);
}

@FuntionalInterface
interface Lambda4 {
    int method(int x, int y);
}

@FuntionalInterface
interface Lambda5 {
    int min(int x, int y);
}

public class LambdaEx3 {
    public static void main(String[] args) {
        Lambda3 obj = (x) -> System.out.println(x);
        obj.method(10);

        obj = (x) -> System.out.println(x * x);
        obj.method(10);

        Lambda4 obj2 = (x, y) -> x > y ? x : y;
        int result = obj2.method(5, 3);
        System.out.println("큰 수는 : " + result);

        Lambda5 obj3 = (x, y) -> x < y ? x : y;
        System.out.println("작은 수는 : " + obj3.min(6, 8));

    }
}

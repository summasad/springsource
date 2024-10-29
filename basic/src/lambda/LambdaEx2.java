package lambda;

// interface 선언
// new 를 직접적으로 할 수 없음
// 1) 구현 클래스 필요함
// 2) 익명 구현 클래스 사용

@FuntionalInterface // 일반 메소드가 두개 이상 들어오는 걸 컴파일 단계에서 체크 (static 이나 default 는 허용)
interface MyFunctionalInterface1 {
    void method();

    // void print();
    static void print() {
    }

    default void print1() {
    }
}

// class A implements MyFunctionalInterface1{
// @Override
// public void method() {
// 구현클래스
// }
// }

public class LambdaEx2 {
    public static void main(String[] args) {
        // MyFunctionalInterface1 obj = new MyFunctionalInterface1() {
        // // 익명 구현 클래스( }; 로 마무리)
        // @Override
        // public void method() {
        // System.out.println("인터페이스 구현");
        // }
        // };
        // obj.method();

        // 람다식 : 익명구현객체를 식으로 작성
        MyFunctionalInterface1 obj = () -> System.out.println("인터페이스 구현");
        obj.method();

        obj = () -> {
            int i = 10;
            System.out.println(i * i);
        };
        obj.method();
    }
}

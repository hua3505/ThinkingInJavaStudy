
/**
 * Created by Wolf Xu on 2016/6/8.
 */
public class B extends A {

    int b = generateB();

    int generateB() {
        System.out.print("generateB()");
        return 5;
    }

    B() {
        System.out.print("B()");
    }

    B(int i) {
        super(i);
        System.out.print("B(int i)");
    }

    public static void main(String[] args) {
        new B();
        System.out.print("\n");
        new B(2);
        System.out.print("\n");
        System.out.print("main()");
    }
}

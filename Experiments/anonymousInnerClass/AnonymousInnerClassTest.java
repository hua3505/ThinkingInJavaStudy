public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        Fish fish = new Fish() {
            void swim() {
                sayHello();
                System.out.print("swiming!\n");
            }
            void sayHello() {
                System.out.print("hello!\n");
            }
        };
        fish.sayHi();
        fish.swim();
    }
}

interface Bird {
    void fly();
}

abstract class Fish {
    void sayHi() {
        System.out.print("hi!\n");
    }
    abstract void swim();
}
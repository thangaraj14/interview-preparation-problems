package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;

public class FunctionalInterfaceExamples {

    public static void main(String[] args) {
//        FuncInter<String, String> inter = s -> s.toLowerCase(Locale.ROOT);
//        System.out.println(inter.execute("aDkle"));
//
//        List<String> names = List.of("anakjc", "cmnsd c", "bdcskjb");
//        Function<String, Integer> intfn = String::length;
//        List<Integer> newList = map(names, intfn);
//        System.out.println(newList);

//        System.out.println(createFactory(()->Math.random()*100, Double::intValue).create())

        Consumer<String> c1= s-> System.out.println(s+""+Math.random()*100);
        Consumer<String> c2= s-> System.out.println(s+""+Math.random()*99);
        Consumer<String> c3= c1.thenAccept(c2);
        c3.accept("Hi : ");

        Functional<Square,Integer> f1= Square::getArea;
        Functional<Integer,Double> f2= Math::sqrt;

        Functional<Square,Double> f3= f2.compose(f1);
        System.out.println(f3.apply(new Square(10)));

        //currying
        Functional<Integer, Function<Integer,Function<Integer,Integer>>> func= u->v->w->u+v+w;
        System.out.println(func.apply(3).apply(4).apply(6));



    }

    private static <T, R> List<R> map(List<T> names, Function<T, R> intfn) {
        List<R> newList = new ArrayList<>();
        for (T name : names) {
            newList.add(intfn.apply(name));
        }
        return newList;
    }

    public static <T, R> IFactory<R> createFactory(IProducer<T> producer, IConfigurator<T, R> configurator) {
        return () -> {
            return configurator.configure(producer.produce());
        };
    }

}

interface FuncInter<R, T> {
    R execute(T t);
}

interface IFactory<T> {
    T create();
}

interface IProducer<T> {
    T produce();
}

interface IConfigurator<T, R> {
    R configure(T t);

}

interface Consumer<T> {
    void accept(T t);
    default Consumer<T> thenAccept(Consumer<T> next){
        return (T t)->{
            this.accept(t);
            next.accept(t);
        };
    }
}

@FunctionalInterface
interface Functional<T,R>{
    R apply(T t);

    default <V> Functional<V,R> compose(Functional<V,T> before){
        return (V v)-> this.apply(before.apply(v));
    }
}

class Square{
    private int area;

    Square(int area){
        this.area=area;
    }
    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}


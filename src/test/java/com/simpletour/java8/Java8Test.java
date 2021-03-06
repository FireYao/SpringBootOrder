package com.simpletour.java8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Test {

    @Test
    public void name1() throws Exception {
        List<Integer> strings = Arrays.asList(12, 4, 5);
        strings.sort((str1, str2) -> str1 - str2);
        System.out.println(strings);
    }


    @Test
    public void name2() throws Exception {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        });


        Thread.sleep(100);
        t.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main");
        }


    }

    @Test
    public void name3() throws Exception {
        List<Integer> list = Arrays.asList(10, 22, 20, 331, 4, 123, 44, 2, 1, 23, 124, 552, 10);

        list.sort(((o1, o2) -> {
            return o1 - o2;
        }));

        list.stream()
                .filter(i -> i > 100)
                .forEach(i -> System.out.print(i + " "));
    }


    int temp1 = 1;
    static int temp2 = 2;

    @Test
    public void name4() throws Exception {

        int temp3 = 3;
        final int temp4 = 4;
        final int temp5 = 5;
        Function<Integer, Integer> f1 = (i -> temp5 + i);
        Function<Integer, Integer> f2 = (i -> {
            return temp5 + i;
        });

//        System.out.println(f1.apply(2));

        List<Integer> list = Arrays.asList(10, 22, 20, 331, 4, 200, 200, 200, 123, 44, 2, 1, 23, 124, 552, 10);


        list.sort(Integer::compareTo);

        Stream<Integer> distinct = list.stream().filter(i -> i > 100).distinct();
//        System.out.println(distinct.max(Integer::compareTo).get());

        //10个不重复随机数
        // 生成器函数
        //limit()是这个条语句执行终止的关键
        Stream.generate(() ->
                (int) (Math.random() * 10 + 1)
        ).distinct().limit(10).forEach(i -> System.out.println(i));

    }

    UnaryOperator<Long> unaryOperator = integer -> integer == 0 ? 1 : integer * this.unaryOperator.apply(integer - 1);

    @Test
    public void name5() throws Exception {

        //嵌套lambda表达式
        Callable<Runnable> callable1 = () -> () -> System.out.println(Thread.currentThread().getName());

//        callable1.call().run();

        Callable<Integer> callable2 = Thread.currentThread().getName().equalsIgnoreCase("main") ? (() -> 42) : (() -> 24);
//        System.out.println(callable2.call());

        System.out.println(unaryOperator.apply(6L));

    }


    @Test
    public void name6() throws Exception {

        List<String> collection = Arrays.asList("1", "2", "4", "123", "300");
        System.out.println(collection.stream().mapToInt(Integer::parseInt).sum());
    }

    @Test
    public void name7() throws Exception {
        List<Integer> list = Arrays.asList(10, 22, 20, 331, 4, 200, 200, 200, 123, 44, 2, 1, 23, 124, 552, 10);
        list.stream().sorted((i1, i2) -> i2 - i1).mapToInt(Integer::intValue).distinct().forEach(i -> System.out.print(i + " "));
    }


    @Test
    public void name8() throws Exception {
        List<String> collection = Arrays.asList("1232", "1", "2", "4", "123", "300");
//        collection.stream().forEach(s -> System.out.print(s + " "));
//        Stream.generate(Math::random).limit(3).forEach(i -> System.out.println(i));
        List<Integer> list = Arrays.asList(10, 22, 20, 331, 4, 200, 200, 200, 123, 44, 2, 1, 23, 124, 552, 10);
        Stream<String> stringStream = list.stream().map(s -> (s.toString()));


    }

    @Test
    public void name9() throws Exception {
        List<String> strs = Arrays.asList("1,2,3", "123,4", "5,6,7");
        List<String[]> strArray = strs.stream().map(str -> str.split(",")).collect(Collectors.toList());
        List<String> strList = strs.stream().map(str -> str.split(",")).flatMap(Arrays::stream).collect(Collectors.toList());
//        System.out.println("strArray => " + JSON.toJSONString(strArray));
//        System.out.println("strList => " + JSON.toJSONString(strList));

        List<String[]> collect = strs.stream().map(s -> s.split(",")).collect(Collectors.toList());

        List<String> collect1 = collect.stream().flatMap(Arrays::stream).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(collect));
        System.out.println(JSON.toJSONString(collect1));
    }


    @Test
    public void name10() throws Exception {
        Stream<String> peek = Stream.of("one", "two", "three", "four")
                .peek(s -> System.out.println("FilterPeekValue::" + s))
                .map(String::toUpperCase)
                .peek(s -> System.out.println("MapPeekValue::" + s));

        //当遇上collect forEach之类的终结操作时,流的其他中间作才会执行;
        List<String> collect = peek.collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
    }


    @Test
    public void name11() throws Exception {

        List<Integer> list = Arrays.asList(10, 22, 20, 331, 4, 200, 200, 200, 123, 44, 2, 1, 23, 124, 552, 10);

        //distinct 筛选去重
        Integer reduce = list.stream().distinct()
                //规约 将一个流里的元素反复结合起来 得到一个值  如果reduce(T identity, BinaryOperator)不传第一个参数identity 将返回一个Optional<T> 避免空指针异常
                .reduce(0, ((integer, integer2) -> integer + integer2));
        Optional<Integer> reduce1 = list.stream().distinct()
                .reduce((integer, integer2) -> integer + integer2);
        //判断stream里是否有任意一个大于100
     /*   boolean b = list.stream().anyMatch(integer -> integer < 100);
        System.out.println(b);*/
    }


    @Test
    public void name12() throws Exception {

        Stream.Builder<Object> builder = Stream.builder();
        builder.add(1);
        builder.add(2);
        builder.add(3);
        builder.add(4);
        builder.add(5);
        Stream<Object> build = builder.build();
        build.forEach(o -> System.out.println(o));
    }


    @Test
    public void name13() throws Exception {


        Stream<Integer> stream = Stream.of(10, 22, 20, 331, 4, 200, 200, 200, 123, 44, 2, 1, 23, 124, 552, 10);

        //过滤小于20的数,保留大于20
        stream.filter(integer -> integer > 20)
                //升幂排序
                .sorted((o1, o2) -> o1.compareTo(o2))
                .forEach(integer -> System.out.print(integer + " "));

    }

    @Test
    public void name14() throws Exception {

        Stream<Person> stream = Stream.of(new Person(1)
                , new Person(1)
                , new Person(1)
                , new Person(4)
                , new Person(5)
                , new Person(6)
                , new Person(7)
                , new Person(8)
                , new Person(9)
                , new Person(10));

        stream.forEach(person -> {
//            person.setAge(1);
            if (person.getAge() == 3) {
                System.out.println("3岁");
                return;//forEach中无法用return/break提前结束循环
            }
            System.out.println(person);
        });
        //此时会报异常.Stream执行foreach后 该Stream就关闭了
        //java.lang.IllegalStateException: stream has already been operated upon or closed
//        List<Person> collect = stream.collect(Collectors.toList());
//        System.out.println(JSON.toJSONString(collect));

    }

    @Test
    public void name15() throws Exception {
        //等差数列
        //iterate和reduce类似
        //seed:2种子为该数列第一个值
        //此后每个数按照f(i+2)规律递增
        //iterate会无限生成元素，必须要有limit()限制Stream的大小
        Stream.iterate(2, i -> i + 2).limit(10).forEach(integer -> System.out.print(integer + " "));
    }


    @Test
    public void name16() throws Exception {
        //以Person年龄分组
        Map<Integer, List<Person>> collect = Stream.generate(Person::new).limit(10).collect(Collectors.groupingBy(Person::getAge));
        System.out.println(JSONObject.toJSON(collect));
        //筛选 年龄大于20和小于20两组
        Map<Boolean, List<Person>> collect1 = Stream.generate(Person::new).limit(10).sorted((o1, o2) -> o1.getAge() - o2.getAge()).collect(Collectors.partitioningBy(p -> p.getAge() > 20));
        print(collect1);
    }

    @Test
    public void name17() throws Exception {

//        Optional.ofNullable(123).ifPresent(System.out::println);

        Optional<String> firstName = Optional.of("tom");
        System.out.println("First Name is set? " + firstName.isPresent());
        System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
        System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
        System.out.println();

    }


    @Test
    public void name18() throws Exception {

        Stream<Student> stream = Stream.of(new Student(70, "A"),
                new Student(80, "B"),
                new Student(90, "C"),
                new Student(100, "D"),
                new Student(100, "E"),
                new Student(78, "F"),
                new Student(91, "G"),
                new Student(84, "H"),
                new Student(79, "I"),
                new Student(65, "J"),
                new Student(100, "K"),
                new Student(98, "L"),
                new Student(59, "M"),
                new Student(60, "N"),
                new Student(66, "O"));

        List<Student> collect = stream.sorted(
                //分数降幂排序 分数相同再比较名字
                Comparator.comparing(Student::getScore)
                        .reversed()
                        .thenComparing(Student::getName))
                .collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(collect, true));

    }

    @Test
    public void name19() throws Exception {

        File[] file = new File(".").listFiles();

        List<String> collect = Arrays.stream(file).
                //过滤isFile
                        filter(file1 -> file1.isFile())
                //转换文件名列表
                .map(file1 -> file1.getName()).collect(Collectors.toList());

        String string = JSONObject.toJSONString(collect, true);
        System.out.println(string);

    }

    List<Student> studentList = Arrays.asList(new Student(80, "B"),
            new Student(90, "A"),
            new Student(100, "D"),
            new Student(100, "E"),
            new Student(58, "F"),
            new Student(41, "G"),
            new Student(84, "H"),
            new Student(59, "I"),
            new Student(65, "J"),
            new Student(100, "K"),
            new Student(98, "L"),
            new Student(59, "M"),
            new Student(60, "N"),
            new Student(66, "O"));

    @Test
    public void name20() throws Exception {
        //统计分数不及格的学生个数
        Integer c = studentList.stream()
                .map(student -> student.getScore() < 60 ? 1 : 0)
                .reduce(0, Integer::sum);

        System.out.println(c);
    }

    @Test
    public void name21() throws Exception {
        Map<String, Integer> collect = studentList.stream().collect(Collectors.toMap(Student::getName, Student::getScore));
        print(collect);
    }

    @Test
    public void name22() throws Exception {
        String collect = studentList.stream().map(Student::getName).collect(Collectors.joining(",", "---", "---"));
        System.out.println(collect);
    }

    @Test
    public void name23() throws Exception {
        Integer collect = studentList.stream().map(Student::getScore).collect(Collectors.reducing(0, Integer::sum));
        print(collect);
    }

    @Test
    public void name24() throws Exception {
        Map<String, List<Student>> collect = studentList.stream()
                .sorted((s1, s2) -> s1.getScore() - s2.getScore())
                .collect(Collectors.groupingBy((s) -> {
                    if (s.getScore() < 60)
                        return "不及格";
                    else if (s.getScore() > 60 && s.getScore() < 90)
                        return "良好";
                    else
                        return "优秀";
                }));
        print(collect);
    }

    @Test
    public void name25() throws Exception {
        Optional<String> reduce = studentList.stream().map(Student::getName).sorted().reduce((s, s2) -> s + "," + s2);
        System.out.println(reduce.get());
    }

    public void name26(Long a) throws Exception {
        Long b;
        b = a == null ? 0 : a;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void name27() throws Exception {

        int a = 1;
        int b = 1;

        a -= b;
        System.out.println(a);
        System.out.println(b);

    }

    @Test
    public void name28() throws Exception {

        System.out.println(String.format("123,{%s},321,{%s}", "32aa", "zzz"));


    }

    public static void print(Object obj) {
        System.out.println(JSONObject.toJSONString(obj, true));
    }

}

package com.example.demo.java.util.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
// https://www.jb51.net/article/138519.htm
public class StreamTest {
    /**
     * stream.collect() 的本质由三个参数构成,
     * 1. Supplier 生产者, 返回最终结果
     * 2. BiConsumer<R, ? super T> accumulator 累加器
     * 第一个参数是要返回的集合, 第二个参数是遍历过程中的每个元素,
     * 将流中每个被遍历的元素添加到集合中
     * 3. BiConsumer<R, R> combiner 合并器, 在有并行流的时候才会有用, 一个流时代码不会走到这里
     * 将第二步遍历得到的所有流形成的list都添加到最终的list中,
     * 最后返回list1
     */
    @Test
    public void Test() {
        Stream<String> stream = Stream.of("hello", "world", "helloworld");
        // 最原始和基础的方式
        /*
        List<String> list = stream.collect(
                ()->new ArrayList(),
                (theList, item) -> theList.add(item),
                (list1, list2) -> list1.addAll(list2)
        );
        */
        // 打印出更详尽的过程
        List<String> listDetail = stream.collect(
                () -> {
                    ArrayList<String> arrayList = new ArrayList<>();
                    System.out.println("第一个list诞生, size: " + arrayList.size());
                    return arrayList;
                },
                (theList, item) -> {
                    System.out.println("第二个list的size: " + theList.size());
                    theList.add(item);
                },
                (list1, list2) -> {
                    System.out.println("第三个list1的size: " + list1.size());
                    System.out.println("第四个list2的size: " + list2.size());
                    list1.addAll(list2);
                }
        );
        /* 输出
            第一个list诞生, size: 0
            第二个list的size: 0
            第二个list的size: 1
            第二个list的size: 2
        * */
        // 使用方法引用来传递行为, 更加清晰易懂, new(新建) -> add(累加) -> addAll(合并)
        List<String> list2 = stream.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        String concat = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        System.out.println(concat);
    }

    @Test
    public void Test2() {
        Stream<String> stream = Stream.of("hello", "world", "helloworld");
        // 这样的写法兼具灵活和简单
        ArrayList<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
        TreeSet<String> treeSet = stream.collect(Collectors.toCollection(TreeSet::new));
        String s = stream.collect(Collectors.joining()); // 拼接成字符串
        HashMap<String, String> map = stream.collect(HashMap::new, (x, y) -> {
            x.put(y, y); // 自己做自己的key
        }, HashMap::putAll);
    }
}

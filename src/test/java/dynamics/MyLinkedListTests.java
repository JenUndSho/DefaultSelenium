package dynamics;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MyLinkedListTests {

    @BeforeMethod
    public void before(){
        System.out.println("Test started.....");
    }

    @AfterMethod
    public void after(){
        System.out.println("Test finished.....\n");
    }

    @Test
    public void linkedListTest1() throws Exception {

        MyLinkedList<Integer> ls = new MyLinkedList<>();

        ls.add(10);
        ls.add(20);

        ls.print();

        System.out.println(ls);
        ls.clear();
        System.out.println(ls);

    }

    @Test
    public void linkedListTest2() throws Exception{
        MyLinkedList<String> ls2 = new MyLinkedList<>();

        ls2.add("Vova");
        ls2.add("Petya");
        ls2.add("Katya");
        ls2.addFirst("1");
        ls2.add("last");
        ls2.add(4,"JENJA");

        System.out.println(ls2.get(1));
        System.out.println(ls2.hashCode());

        ls2.print();

        Integer [] arr = new Integer[]{1,2,3,4,5};

        MyLinkedList<Integer> listArray = new MyLinkedList<Integer>(arr);
        System.out.println(listArray);

        MyLinkedList<String> listArr = new MyLinkedList<String>(ls2);
        listArr.remove("1");
        System.out.println(listArr.contains("o"));

        System.out.println(listArr);
    }

    @Test
    public void likedListTest3() throws Exception{
        MyLinkedList<String> ls3 = new MyLinkedList<>();

        ls3.add(0,"xyz");
        ls3.add(0,"zyx");
        ls3.add(0,"tut");

        ls3.set(2, "JENJA");

        ls3.remove(2);

        ls3.print();
    }


}

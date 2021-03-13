package dynamics;

import java.lang.annotation.ElementType;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyLinkedList<E> {

    private Element<E> head;

    class Element<E>{
        public E info;
        public Element<E> pNext;
        public Element<E> pPrev;


    }

    public MyLinkedList(){
        head = null;
    }

    public MyLinkedList(E[] mas){
        Element<E> temp = new Element<>();
        temp.info = mas[0];
        temp.pPrev = null;
        temp.pNext = null;
        head = temp;


        for(int i=1; i< mas.length; i++){
            Element<E> u = new Element<>();
            temp.pNext = u;

            u.pPrev = temp;
            u.pNext = null;
            u.info = mas[i];
            
            temp = u;
        }
    }

    public MyLinkedList(MyLinkedList<E> list) throws Exception{
        Element<E> temp = new Element<>();
        Element<E> listElem = list.head;

        if(list.head == null){
            throw new Exception("You try to copy null list");
        }

        temp.pPrev = null;
        temp.pNext = null;
        temp.info = listElem.info;
        head = temp;

        listElem = listElem.pNext;
        while(listElem != null){

            Element<E> put = new Element<>();
            temp.pNext = put;
            put.pPrev = temp;
            put.pNext = null;
            put.info = listElem.info;

            temp = put;
            listElem = listElem.pNext;

        }

    }

    // Из листа в массив?
    //      E [] mas = new E[size()];     не работает! Предупреждение:
    //  Type parameter 'E' cannot be instantiated directly

    public int size(){
        int size = 0;
        Element<E> temp = head;

        while(temp != null){
            size++;
            temp = temp.pNext;
        }

        return size;
    }

    public E get(int index) throws Exception{

        if(index < 0)
            throw new Exception("Index should be >=0");

        Element<E> temp = head;
        int i = 0;

        while(temp != null){

            if(i == index)
                return temp.info;

            temp = temp.pNext;
            i++;

        }

        throw new Exception("There is no element on this index!");

    }

    public void add(E data){

        Element<E> temp = new Element<>();


        if (head==null){
            temp.info = data;
            temp.pNext = null;
            temp.pPrev = null;

            head = temp;
        }
        else{
            Element<E> findLast = head;

            while (findLast.pNext != null)
                findLast=findLast.pNext;

            findLast.pNext = temp;
            temp.info = data;
            temp.pPrev = findLast;
            temp.pNext = null;

        }

    }

    public void addFirst(E data){

        Element<E> temp = new Element<>();

        if(head == null){
            temp.info = data;
            temp.pNext = null;
            temp.pPrev = null;

            head = temp;
        }
        else{
            temp.info = data;
            temp.pPrev = null;
            temp.pNext = head;
            head = temp;
        }

    }

    public void add(int index, E data) throws Exception {

        Element<E> temp = new Element<>();
        temp.info = data;
        Element beforeTemp = head;

        if(index < 0)
            throw new Exception("Index should be >=0");

        if(index == 0 && size() == 0){
            add(data);
            return;
        }
        else if(index == 0 && size() != 0){
            addFirst(data);
            return;
        }

        if (size()-1<index)
            throw new Exception("Size is smaller than index you want to insert into the data ");

        if(size() == 0)
            throw new Exception("The size of list is 0, so you can't add anything ");


        for(int i=0; i<index-1; i++)
            beforeTemp = beforeTemp.pNext;
        Element afterTemp = beforeTemp.pNext;

        beforeTemp.pNext = temp;
        temp.pPrev = beforeTemp;
        temp.pNext = afterTemp;

    }

    public void set(int index, E data) throws Exception{
        if(index<0)
            throw new Exception("Index can't be < 0");
        if(size()-1 < index)
            throw new Exception("Size is smaller than index you want to insert into the data");
        if(size() == 0)
            throw new Exception("The size of list is 0, so you can't add anything");

        Element<E> temp = head;

        int i=0;
        while((i != index) && (temp != null)){
            i++;
            temp = temp.pNext;
        }

        temp.info = data;

    }

    public void remove(E data){

        Element<E> bTemp;

        Element<E> temp = head;

       if(temp == null) return;

       while(temp != null){

           if(temp.info == data && temp == head){
               head = temp.pNext;
               temp=null;
               return;
           }

           if(temp.info == data){
               Element<E> beforeTemp = temp.pPrev;
               Element<E> afterTemp = temp.pNext;

               if(afterTemp == null){
                   beforeTemp.pNext=afterTemp;
               }
               else{
                   beforeTemp.pNext = afterTemp;
                   afterTemp.pPrev = beforeTemp;
               }
               temp = null;
               return;
           }

           bTemp = temp;
           temp = bTemp.pNext;
           temp.pPrev = bTemp;

       }

    }

    public void remove(int index) throws Exception{

        Element<E> bTemp;

        Element<E> temp = head;

        if (temp == null) return;

        if(index < 0)
            throw new Exception("Index should be > 0");

        if(size()-1 < index)
            throw new Exception("Size is smaller than index you want to insert into the data");

        int i = 0;

        while(temp != null){

            if(i == index && temp == head){
                head = temp.pNext;
                temp=null;
                return;
            }

            if(i == index){
                Element<E> beforeTemp = temp.pPrev;
                Element<E> afterTemp = temp.pNext;

                if(afterTemp == null){
                    beforeTemp.pNext=afterTemp;
                }
                else{
                    beforeTemp.pNext = afterTemp;
                    afterTemp.pPrev = beforeTemp;
                }
                temp = null;
                return;
            }

            bTemp = temp;
            temp = bTemp.pNext;
            temp.pPrev = bTemp;
            i++;

        }

    }

    public void clear(){
        head = null;
    }

    public boolean contains(E data){
        Element<E> temp = head;

        while(temp != null){
            if(temp.info == data){
                return true;
            }
            temp = temp.pNext;
        }

        return false;
    }

    public void print(){
        Element<E> temp;
        temp=head;

        while(temp != null){
            System.out.print(temp.info + " -> ");
            temp=temp.pNext;
        }
        System.out.print(" Null\n");

    }

    @Override
    public String toString(){
        Element<E> temp;
        temp=head;
        StringBuilder result = new StringBuilder();

        while(temp != null){
           result.append(temp.info).append(" -> ");
           temp=temp.pNext;
        }
        return result + "Null";
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if ((o == null) || (getClass() != o.getClass()))
            return false;

        MyLinkedList<E> myLinkedList = (MyLinkedList<E>) o;

        Element<E> thisLst = head;
        Element<E> secondLst = myLinkedList.head;

        if(size() != myLinkedList.size())
            return false;
        while(thisLst != null && secondLst != null){

            if(thisLst.info != secondLst.info)
                return false;

            thisLst = thisLst.pNext;
            secondLst = secondLst.pNext;

        }

        return true;

    }

    @Override
    public int hashCode(){
        int result = (head != null) ? head.hashCode() : 0;

        Element<E> temp = head.pNext;

        while(temp != null){
            result = 31*result + temp.hashCode();
            temp = temp.pNext;
        }

        return result;
    }


}

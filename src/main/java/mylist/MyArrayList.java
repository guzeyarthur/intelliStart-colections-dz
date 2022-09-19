package mylist;

import interfaces.MyList;

public class MyArrayList<T> implements MyList<T> {
    private T[] array;
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity = DEFAULT_CAPACITY;
    private int size=0;

    public MyArrayList(T[] array) {
        this.array = array;
        size = array.length;
        capacity = array.length;
    }

    public MyArrayList(int capacity) {
        if (capacity < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        this.capacity = capacity;
        array = (T[]) new Object[this.capacity];
    }

    public MyArrayList() {
        array = (T[]) new Object[this.capacity];
    }

    @Override
    public void clear() {
        size=0;
        array = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index>=size || index<0){
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public boolean add(T value) {
        if(size>=capacity)
        {
            array = createArrayCapacity();
        }
        array[size]=value;
        size++;
        return true;
    }


    @Override
    public T remove(int index) {
        if (index<size && index>=0)
        {
            T removedValue = array[index];
            array[index]=null;
            for (int i = index; i < size; i++)
            {
                array[i] = array[i+1];
            }
            array[size-1]=null;
            size--;
            return removedValue;
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    private T[] createArrayCapacity(){
        capacity = (capacity*3)/2+1;
        T[] newArray = (T[]) new Object[capacity];
        for (int i=0; i<size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}

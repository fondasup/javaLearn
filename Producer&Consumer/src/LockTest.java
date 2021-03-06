import java.util.LinkedList;

public class LockTest {
    private int size = 1;
    private LinkedList<Integer> list = new LinkedList<>();

    synchronized public void put(int num){
        if (isFull()){
            try {
                System.out.println("0000000000000000");
                wait(); //进入等待状态可以自己激活么
                System.out.println("=============");
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("put" + num);
        list.add(num);
        System.out.println("size" + list.size());
        notifyAll();
    }


    public boolean isEmpety(){
        return list.isEmpty();
    }

    public boolean isFull(){
        return  list.size() == size;
    }

    synchronized public void take(){
        while (isEmpety()){
            try {
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(list.getFirst());
        list.remove();
        notify();
    }

    public static void main(String[] args){
        LockTest lockTest = new LockTest();
        Producer1 producer1 = new Producer1(lockTest);
        Consumer1 consumer1 = new Consumer1(lockTest);
//        consumer1.start();
        producer1.start();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        consumer1.start();

    }
}

class Producer1 extends Thread{
    private LockTest lockTest;
    Producer1(LockTest lockTest){
        this.lockTest = lockTest;
    }

    public void run(){
        for(int i=0;i<5;i++){
            lockTest.put(i);
        }
    }
}


class Consumer1 extends Thread{
    private LockTest lockTest;
    Consumer1(LockTest lockTest){
        this.lockTest = lockTest;
    }

    public void run(){
        for(int i=0;i<1;i++){
            lockTest.take();
        }
    }
}


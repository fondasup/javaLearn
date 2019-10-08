import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class QueueManager{
    private LinkedBlockingDeque<Integer> queue;
    QueueManager(){
        this.queue = new LinkedBlockingDeque(50);
    }

    void put(Integer ele){
        try {
            queue.put(ele);
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    Integer take(){
        try{
           return queue.take();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return null;
    }
}

class Producer extends Thread{
    private QueueManager queue;
    Producer(QueueManager q){
        this.queue = q;
    }

    public void run(){
        for(int i=0;i<100;i++){
            System.out.println("put " + i);
//            try{
//                Thread.sleep(1000);
//            }catch (InterruptedException e){
//                Thread.currentThread().interrupt();
//            }
            queue.put(i);
        }
    }
}

class Consumer extends Thread{
    private QueueManager queue;

    Consumer(QueueManager q){
        this.queue = q;
    }

    public void run(){
        for(int i=0;i<100;i++){
            int a = queue.take();
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            System.out.println(a);
        }
    }
}

class Test{
    public static void main(String[] args){
        QueueManager queue = new QueueManager();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        producer.start();
        consumer.start();
    }
}



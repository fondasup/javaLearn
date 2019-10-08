
class InnerClass {
    static class Test{
        int num = 0;
        Test(int n){
            num = n;
        }
    }
}

class Main{
    public static void main(String[] args){
        InnerClass.Test test1 = new InnerClass.Test(1);
        InnerClass.Test test2 = new InnerClass.Test(2);
        System.out.println(test1.num);
        System.out.println(test2.num);
    }
}

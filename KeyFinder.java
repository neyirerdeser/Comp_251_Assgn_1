public class KeyFinder {



    public static void main(String []args) {
        int A = 973;
        double modulo = 105;
        for(int i=1; i<1000; i++){
            int ak = (int)(Math.pow(2,10)*i+modulo);
            if(ak%A == 0) {
                System.out.println(i);
                break;
            }
    }


    }

}

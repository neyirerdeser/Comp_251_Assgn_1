public class main {
     
    public static void main(String[] args) {

        System.out.println("Tests for Chaining:");

        Chaining ch = new Chaining(10,0,-1);
        int[] chainKeys = {1,20,93,973,1023};
        int[] chainAnswers = {30,0,11,17,1};
        for(int i = 0; i< chainAnswers.length; i++) {
            int hashValue = ch.chain(chainKeys[i]);
            if(hashValue != chainAnswers[i]) {
                System.out.println("problem in Chaining.chain - key:"+ chainKeys[i]);
                System.exit(1);
            }
        }   System.out.println(chainKeys.length+" tests passed for:\tChaining.chain");


        // some (Ak)mod2^w values that result in the same hash values
        int[]values = {97,105,102,113,106,96,127};
        chainKeys = new int[7];
        for(int i = 0; i< chainKeys.length; i++){
            chainKeys[i] = keyFinder(ch.A, values[i]);
        }

        int collision = 0;
        for(int i=0; i<7; i++) {
            collision = ch.insertKey(chainKeys[i]);
            if(collision != i){
                System.out.println("problem in Chaining.insertKey - key:"+ chainKeys[i]);
                System.exit(1);
            }
        }   System.out.println(chainKeys.length+" tests passed for:\tChain Insertion");

        int[]chainKeyArray = {4,86,33,689,12,876,123};

        collision = ch.insertKeyArray(chainKeyArray);
        if(collision != 1) {
            System.out.println("problem in Chaining.insertKey when inserting array");
            System.exit(1);
        }   System.out.println(chainKeyArray.length+" tests passed for:\tChain Insertion with Array");

        System.out.println("\nTests for Open Addressing:");

        Open_Addressing oa = new Open_Addressing(10,0,-1);
        int[]openKeys = {1,20};
        int[][]openAnswers = {{30,31,0,1,2,3,4,5,6}, {0,1,2,3,4,5,6,7,8}};
        int testLength = 0;
        for(int i = 0; i< openKeys.length; i++) {
            for(int j=0; j<openAnswers[i].length; j++) {
                testLength++;
                int hashValue = oa.probe(openKeys[i],j);
                if(hashValue != openAnswers[i][j]) {
                    System.out.println("problem in OpenAddress.probe - key:"+ openKeys[i]+" i:"+j);
                    System.exit(1);
                }
            }
        }   System.out.println(testLength+" tests passed for:\tOpenAddress.probe");


        for(int i=0; i<35; i++){
            collision = oa.insertKey(1);
            if((i<33 && collision != i)||(i>=33 && collision != 32)){
                System.out.println("problem in Open Address Insertion - key:1 try:"+ i);
                System.exit(1);
            }
        }   System.out.println(35+" tests passed for:\tOpen Address Insertion: incl Successful/Unsuccessful");

        for(int i=0; i<32; i++){
            oa.removeKey(1);
        }

        int[]openKeyArray = {34,79,14,875,13,75,123,456,914,935};
        collision = oa.insertKeyArray(openKeyArray);

        if(collision != 5) {
            System.out.println("problem in OpenAddress.insertKey when inserting array");
            System.exit(1);
        }   System.out.println(openKeyArray.length+" tests passed for:\tOpen Address Insertion with Array");


        int[]openDeletes    = {34,13,423,935,79,123,456,57,14,75,875,914};
        int[]openDelAnswers = {0,  0, 32,  1, 0,  0,  3,32, 1, 0,  0,  0};

        for(int i=0; i<openDeletes.length; i++){
            if(oa.removeKey(openDeletes[i]) != openDelAnswers[i]) {
                System.out.println("problem in OpenAddress.removeKey - key:"+openDeletes[i]);
                System.exit(1);
            }
        }   System.out.println(openDeletes.length+" tests passed for:\tOpen Address Remove: incl Successful/Unsuccessful");


    }

    public static void printTable(Open_Addressing oa){
        for(int i=0; i<oa.Table.length; i++) {
            System.out.println(i+"\t"+oa.Table[i]);
        }
    }

    public static int quotientFinder(int A, double modulo){
        for(int i=1; i<1000; i++) {
            int ak = (int) (Math.pow(2, 10) * i + modulo);
            if (ak % A == 0) {
                return ak;
            }
        }
        return 0;
    }

    public static int keyFinder(int A, double modulo){
        int ak = quotientFinder(A,modulo);
        return ak/A;
    }
}
public class main {
    public static void main(String[] args) {
       
        System.out.println("========CREATING AN INTERFACE (KYLE)========");

        Interface Kyle = new Interface();

        Node curK = Kyle.getOrigin();

        System.out.println("\n--------Adding nodes--------");
        for(int i=-1; i <= 1; i++) //Big square around origin
        {
            for(int j=1; j >= -1; j--)
            {
                Kyle.addPoint(new ExampleFunction1(), i, j);
            }
        }

        Kyle.addPoint(new ExampleFunction1(), 17, 3); //Other nodes
        Kyle.addPoint(new ExampleFunction1(), -1, 3);
        Kyle.addPoint(new ExampleFunction1(), 13, -3);
        Kyle.addPoint(new ExampleFunction1(), -12, -300);
        Kyle.addPoint(new ExampleFunction1(), 0, -300);
        Kyle.addPoint(new ExampleFunction1(), -12, 0);
        Kyle.addPoint(new ExampleFunction1(), 0, 0);

        curK = curK.right;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.right;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.down;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.up;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.right;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.up;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.down;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.left;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.left;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.left;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.left;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.down;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.up;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.left;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        curK = curK.down;
        System.out.println("Finding Kyles Nodes... " + "V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " ");
        
        curK = Kyle.getOrigin();

        System.out.println("\n--------Adding nodes from array to new Interface (Tasha)-------- Did you set the origin in the array constructor???");
        
        Node[] TashaArr = new Node[9];

        TashaArr[0] = new Node(new ExampleFunction1().clone(), 20, 20);

        Node test = new Node(new ExampleFunction1().clone(), -50, 20);
        ExampleFunction2 func = new ExampleFunction2();
        test.setFunction(func);

        TashaArr[1] = test;
        TashaArr[2] = new Node(new ExampleFunction3().clone(), -40, 20);
        TashaArr[3] = new Node(new ExampleFunction4().clone(), -40, 21);
        TashaArr[4] = new Node(new ExampleFunction1().clone(), -420, 21);
        TashaArr[5] = new Node(new ExampleFunction3().clone(), 10, 11);
        TashaArr[6] = new Node(new ExampleFunction4().clone(), 420, 0);
        TashaArr[7] = new Node(new ExampleFunction4().clone(), 0, 11);
        TashaArr[8] = new Node(new ExampleFunction4().clone(), 0, 0);

        Interface Tasha = new Interface(TashaArr);

        Node curT = Tasha.getOrigin();

        curT = curT.right;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " ");
        curT = curT.up;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " ");
        curT = curT.down;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " ");
        curT = curT.right;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " ");
        curT = curT.up;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " ");
        curT = curT.left;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Jumping to v1 axis");
        curT = curT.left;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Jumping to -40 v1");
        curT = curT.left;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Jumping to -50 v1");
        curT = curT.right;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Jumping back to -40 v1");
        curT = curT.up;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Jumping up to 21 v2");
        curT = curT.left;
        System.out.println("Finding Tashas Nodes... " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Jumping left to -420 v1");

        curT = Tasha.getOrigin();

        System.out.println("\n========Stacking Functions========");

        Tasha.addPoint(new ExampleFunction1().clone(), 20, 20);
        Tasha.addPoint(new ExampleFunction1().clone(), -50, 20);
        Tasha.addPoint(new ExampleFunction4().clone(), -40, 20);
        Tasha.addPoint(new ExampleFunction2().clone(), -40, 21);
        Tasha.addPoint(new ExampleFunction3().clone(), -420, 21);
        Tasha.addPoint(new ExampleFunction4().clone(), 10, 11);

        curT = curT.right;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + "");
        curT = curT.up;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 3");
        curT = curT.down;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " ");
        curT = curT.right;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " ");
        curT = curT.up;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 1");
        curT = curT.left;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Jumping to v1 axis");
        curT = curT.left;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 3");
        curT = curT.left;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 1");

        curT = curT.right;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 3");
        curT = curT.prevVal;
        System.out.println("Going in                 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 4");
        curT = curT.nextVal;
        System.out.println("Coming Out               ");

        curT = curT.up;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 2");
        curT = curT.prevVal;
        System.out.println("Going in                 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 4");
        curT = curT.nextVal;
        System.out.println("Coming Out               ");
        
        curT = curT.left;
        System.out.println("Finding Tashas Nodes...2 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 1");
        curT = curT.prevVal;
        System.out.println("Going in                 " + "V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " // Should be function 3");
        curT = curT.nextVal;
        System.out.println("Coming Out    hah gey           ");

        System.out.println("\n========Getting Points========");

        System.out.println(Tasha.getPoint(-50, 20) + " should be a node");
        System.out.println(Tasha.getPoint(-40, 20) + " should be a node");
        System.out.println(Kyle.getPoint(-12, -300) + " should be a node");
        System.out.println(Tasha.getPoint(0, 0) + " should be null");
        System.out.println(Tasha.getPoint(-50, 0) + " should be null");
        System.out.println(Tasha.getPoint(0, 20) + " should be null");
        System.out.println(Kyle.getPoint(99, 99) + " should be null");



        System.out.println("\n========Printing Points by Function========");

        System.out.println("\nTasha");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction1()).getFunctionName())  + "// 5 Nodes");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction2()).getFunctionName())  + "       // 1 Node");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction3()).getFunctionName())  + " // 3 Nodes");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction4()).getFunctionName())  + "  // 3 Nodes");

        System.out.println("\nKyle");
        System.out.println(Kyle.printFunctionValues((new ExampleFunction1()).getFunctionName())  + "// 8 Nodes");
        System.out.println(Kyle.printFunctionValues((new ExampleFunction2()).getFunctionName())  + "// 0 Nodes");
        System.out.println(Kyle.printFunctionValues((new ExampleFunction3()).getFunctionName())  + "// 0 Nodes");
        System.out.println(Kyle.printFunctionValues((new ExampleFunction4()).getFunctionName())  + "// 0 Nodes");

        System.out.println("\n========Removing Points========");

        Kyle.removePoint( 17, 3);
        Kyle.removePoint(-1, 3);

        Tasha.removePoint(13, -3);
        Tasha.removePoint(10, 11);

        Kyle.removePoint(-9, -1323); //Doesn't exist
        Tasha.removePoint(10, 11);
        Tasha.removePoint(10, 11);
        Tasha.removePoint(10, 11); //removing many times
        Tasha.removePoint(0, 11);
        Tasha.removePoint(2, 0);
        Tasha.removePoint(0, 0); //testing axis deletion (shouldn't work)
        
        System.out.println("Tasha");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction1()).getFunctionName())  + "// 4 Nodes");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction2()).getFunctionName())  + "       // 2 Nodes");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction3()).getFunctionName())  + " // 2 Nodes");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction4()).getFunctionName())  + "  // 2 Nodes");

        System.out.println("\nKyle");
        System.out.println(Kyle.printFunctionValues((new ExampleFunction1()).getFunctionName())  + "// 6 Nodes");


        System.out.println("\n========Testing Axis Removed========");

        curT = Tasha.getOrigin();
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 1 origin");
        curT = curT.up;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 1 up");
        curT = curT.up;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 1 up");
        
        if(curT.up != null){
            System.out.println("\n BLERP \n BLERP \n BLERP\n BLERP LOOKS LIKE YOUR Y AXIS ISN'T DELETING WITH YOUR NODES");
        }

        Tasha.addPoint(new ExampleFunction4().clone(), 1, 22);
        
        curT = curT.up;
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 2 up");
        curT = curT.down;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 2 down");

        Tasha.removePoint(1, 22);

        if(curT.up != null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         LOOKS LIKE YOUR Y AXIS ISN'T DELETING WITH YOUR NODES====================!!!=============");
        }

        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 3 start");

        Tasha.addPoint(new ExampleFunction4().clone(), 1, 22);
        Tasha.addPoint(new ExampleFunction4().clone(), -3, 22);
        
        curT = curT.up;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 3 up");
        curT = curT.down;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 3 down");

        Tasha.removePoint(1, 22);
        

        if(curT.up == null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         THIS AXIS SHOULD NOT DELETED====================!!!=============");
        }

        Tasha.removePoint(-3, 22);

        if(curT.up != null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         LOOKS LIKE YOUR Y AXIS ISN'T DELETING WITH YOUR NODES====================!!!=============");
        }

        Tasha.addPoint(new ExampleFunction4().clone(), 1, 22);
        Tasha.addPoint(new ExampleFunction3().clone(), 1, 22);

        curT = curT.up;
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 4 up");
        curT = curT.down;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 4 down");

        Tasha.removePoint(1, 22);

        if(curT.up == null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         THIS AXIS SHOULD NOT DELETED====================!!!=============");
        }

        curT = curT.up;
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 5 up");
        curT = curT.down;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 5 down");

        
        Tasha.addPoint(new ExampleFunction3().clone(), 1, 23);
        Tasha.removePoint(1, 22);

        if(curT.up == null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         THIS AXIS SHOULD NOT DELETED====================!!!=============");
        }

       
        
        curT = curT.up;
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 6 up");
        curT = curT.down;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v2 test 6 down");

        Tasha.removePoint(1, 23);
        //-----------------------V1axis test--------------------

        Tasha.addPoint(new ExampleFunction1().clone(), 21, 20);
        Tasha.removePoint(21, 20);

        curT = Tasha.getOrigin();
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 1 origin");
        curT = curT.right;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 1 right");
        
        
        if(curT.right != null){
            System.out.println("\n BLERP \n BLERP \n BLERP\n BLERP LOOKS LIKE YOUR Y AXIS ISN'T DELETING WITH YOUR NODES");
        }

        Tasha.addPoint(new ExampleFunction4().clone(), 21, 22);
        
        curT = curT.right;
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 2 right");
        curT = curT.left;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 2 left");

        Tasha.removePoint(21, 22);

        if(curT.right != null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         LOOKS LIKE YOUR Y AXIS ISN'T DELETING WITH YOUR NODES====================!!!=============");
        }

        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 3 start");

        Tasha.addPoint(new ExampleFunction4().clone(), 21, 22);
        Tasha.addPoint(new ExampleFunction4().clone(), 21, -22);
        
        curT = curT.right;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 3 right");
        curT = curT.left;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 3 left");

        Tasha.removePoint(21, 22);
        

        if(curT.right == null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         THIS AXIS SHOULD NOT DELETED====================!!!=============");
        }

        Tasha.removePoint(21, -22);

        if(curT.right != null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         LOOKS LIKE YOUR Y AXIS ISN'T DELETING WITH YOUR NODES====================!!!=============");
        }

        Tasha.addPoint(new ExampleFunction4().clone(), 21, 22);
        Tasha.addPoint(new ExampleFunction3().clone(), 21, 22);

        curT = curT.right;
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 4 right");
        curT = curT.left;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 4 left");

        Tasha.removePoint(21, 22);

        if(curT.right == null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         THIS AXIS SHOULD NOT DELETED====================!!!=============");
        }

        curT = curT.right;
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 5 right");
        curT = curT.left;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 5 left");

        
        Tasha.addPoint(new ExampleFunction3().clone(), 21, 23);
        Tasha.removePoint(21, 22);

        if(curT.right == null){
            System.out.println("\nBLERP \n          BLERP \n                    BLERP\n                              BLERP\n                                         THIS AXIS SHOULD NOT DELETED====================!!!=============");
        }

       
        
        curT = curT.right;
        System.out.println("\nV1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 6 right");
        curT = curT.left;
        System.out.println("V1: " + curT.getVariables()[0] + " V2: " + curT.getVariables()[1] + " " + curT.getFunction().getFunctionName() + " Axis v1 test 6 left");

        Tasha.removePoint(21, 23);

        System.out.println("\n========Removing Function 2 Nodes========");

        Tasha.removeAllFunctionPoints("Example function 2");
        Kyle.removeAllFunctionPoints("Example function 2");

        System.out.println("Tasha");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction1()).getFunctionName())  + "// 4 Nodes");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction2()).getFunctionName())  + "// 0 Nodes Goodbye");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction3()).getFunctionName())  + " // 2 Nodes");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction4()).getFunctionName())  + "  // 2 Nodes");

        System.out.println("\nKyle");
        System.out.println(Kyle.printFunctionValues((new ExampleFunction1()).getFunctionName())  + "// 6 Nodes");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction2()).getFunctionName())  + "// 0 Nodes Goodbye");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction3()).getFunctionName())  + " // 3 Nodes");
        System.out.println(Tasha.printFunctionValues((new ExampleFunction4()).getFunctionName())  + "  // 2 Nodes");

        System.out.println("\nAdding functions to kyle real quick... bur bur brub...");

        Kyle.addPoint(new ExampleFunction1().clone(), 2, 2);
        Kyle.addPoint(new ExampleFunction1().clone(), 4, 1);
        Kyle.addPoint(new ExampleFunction4().clone(), 69, 420);
        Kyle.addPoint(new ExampleFunction2().clone(), -69, -420);
        Kyle.addPoint(new ExampleFunction3().clone(), -420, -56);
        Kyle.addPoint(new ExampleFunction4().clone(), 11, 3);

        System.out.println("\n========Admin...========");

        System.out.println("\n--------Counting Kyle's nodes--------");
        System.out.println(Kyle.countNumberOfPoints());
        System.out.println("\n--------Finding Tasha's Max Node--------:");
        System.out.println(Tasha.findMax() + " v1: " + Tasha.findMax().getVariables()[0] + ", v2: " + Tasha.findMax().getVariables()[1]);
        System.out.println("\n--------Finding Tasha's Min Node--------:");
        System.out.println(Tasha.findMin() + " v1: " + Tasha.findMin().getVariables()[0] + ", v2: " + Tasha.findMin().getVariables()[1]);
        System.out.println("\n--------Finding Kyle's min Value--------:");
        System.out.println(Kyle.findMinValue());
        System.out.println("\n--------Finding Kyle's max Value--------:");
        System.out.println(Kyle.findMaxValue());


        System.out.println("\n========Squashing Kyle into an array========");
        
        Node[] kyleArr = Kyle.toArray();

        System.out.println("\n--------And printing his node links--------\n");
        for(int i = 0; i < Kyle.countNumberOfPoints(); i++){
            for(int j = 0; j < 6; j++){
                System.out.print(kyleArr[i].getNodeLinks()[j]);
                System.out.print(" | ");
            }
            System.out.println("");
        }

        System.out.println("\nSHOULD BE:\nU[][]{} | D[69][0]{0} | R[][]{} | L[0][420]{0} | P[][]{} | N[][]{} |\nU[13][0]{0} | D[][]{} | R[][]{} | L[0][-3]{0} | P[][]{} | N[][]{} |\nU[][]{} | D[11][0]{0} | R[][]{} | L[0][3]{0} | P[][]{} | N[][]{} |\nU[][]{} | D[4][0]{0} | R[][]{} | L[1][1]{2} | P[][]{} | N[][]{} |\nU[][]{} | D[2][0]{0} | R[][]{} | L[0][2]{0} | P[][]{} | N[][]{} |\nU[][]{} | D[1][0]{0} | R[4][1]{5} | L[0][1]{0} | P[][]{} | N[][]{} |\nU[1][0]{0} | D[][]{} | R[][]{} | L[0][-1]{0} | P[][]{} | N[][]{} | \nU[][]{} | D[-1][0]{0} | R[0][1]{0} | L[][]{} | P[][]{} | N[][]{} |\nU[-1][0]{0} | D[][]{} | R[0][-1]{0} | L[][]{} | P[][]{} | N[][]{} |\nU[-12][0]{0} | D[][]{} | R[0][-300]{0} | L[][]{} | P[][]{} | N[][]{} |\nU[-69][0]{0} | D[][]{} | R[0][-420]{0} | L[][]{} | P[][]{} | N[][]{} |\nU[-420][0]{0} | D[][]{} | R[0][-56]{0} | L[][]{} | P[][]{} | N[][]{} |");

        System.out.println("\n========Counting Nodes in quadrants========");

        System.out.println("Tasha");


        System.out.println("\n====V2====\n||Q1||Q0||\nV1==Og==||\n||Q2||Q3||\n==========\n");

        for(int i=0; i < 4; i++)
        {
            System.out.println("Count in Q" + i + ": " + Tasha.numPointsPerQuadrant()[i]);
        }

        System.out.println("\nShould be:\nCount in Q0: 2\nCount in Q1: 7\nCount in Q2: 0\nCount in Q3: 0\n");

        System.out.println("Kyle");
        for(int i=0; i < 4; i++)
        {
            System.out.println("Count in Q" + i + ": " + Kyle.numPointsPerQuadrant()[i]);
        }

        System.out.println("\nShould be:\nCount in Q0: 5\nCount in Q1: 1\nCount in Q2: 4\nCount in Q3: 2\n");

        System.out.println("\n========Printing Function Values1========");
        System.out.println("\nKyle:");
        
        System.out.println(Kyle.printFunctionValues("Example function 1"));
        System.out.println(Kyle.printFunctionValues("Example function 2"));
        System.out.println(Kyle.printFunctionValues("Example function 3"));
        System.out.println(Kyle.printFunctionValues("Example function 4"));

        System.out.println("Should be: ");
        System.out.println("312;2;0;0;2;4;5;10");
        System.out.println("-69");
        System.out.println("-1820");
        System.out.println("113;1110");


        System.out.println("\nTasha:");
        
        System.out.println(Tasha.printFunctionValues("Example function 1"));
        System.out.println(Tasha.printFunctionValues("Example function 2"));
        System.out.println(Tasha.printFunctionValues("Example function 3"));
        System.out.println(Tasha.printFunctionValues("Example function 4"));

        System.out.println("Should be: ");
        System.out.println("399;30;30;40;40");
        System.out.println("*blank space*");
        System.out.println("-2205;-300");
        System.out.println("-380;-379");

        System.out.println("\n========Traversing around========");
        for(int i=-3; i <= 3; i++) //Big square around origin
        {
            for(int j=3; j >= -3; j--)
            {
                Kyle.addPoint(new ExampleFunction2(), i, j);
            }
        }
        for(int i=-3; i <= 3; i++) //Big square around origin
        {
            for(int j=3; j >= -3; j--)
            {
                Kyle.addPoint(new ExampleFunction4(), i, j);
            }
        }
        for(int i=-3; i <= 3; i++) //Big square around origin
        {
            for(int j=3; j >= -3; j--)
            {
                Kyle.addPoint(new ExampleFunction1(), i, j);
            }
        }
        for(int i=-3; i <= 3; i++) //Big square around origin
        {
            for(int j=3; j >= -3; j--)
            {
                Kyle.addPoint(new ExampleFunction3(), i, j);
            }
        }

        curK = Kyle.getOrigin();
        curK = curK.left;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.left;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.up;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.up;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.up;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.right;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.right;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.left;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.left;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.down;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName());
        curK = curK.up;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " // Should be function 1");
        curK = curK.prevVal;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " // Should be function 2");
        if(curK.up != null || curK.down != null || curK.right != null || curK.left != null){
            System.out.println("Uh oh... Looks like you still have some leaky pipes down here. Better fix that.");
        }
        curK = curK.prevVal;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " // Should be function 3");
        if(curK.up != null || curK.down != null || curK.right != null || curK.left != null){
            System.out.println("Uh oh... Looks like you still have some leaky pipes down here. Better fix that.");
        }
        curK = curK.prevVal;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " // Should be function 4");
        if(curK.up != null || curK.down != null || curK.right != null || curK.left != null){
            System.out.println("Uh oh... Looks like you still have some leaky pipes down here. Better fix that.");
        }

        System.out.println("--------Skipping over holes--------");

        curK = curK.nextVal;
        curK = curK.nextVal;
        curK = curK.nextVal;

        curK = Kyle.getPoint(-2, 3);
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");

        curK = curK.prevVal;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.prevVal;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.nextVal;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        



        Kyle.removeAllFunctionPoints("Example function 3");
        Kyle.removeAllFunctionPoints("Example function 4");
        Kyle.removeAllFunctionPoints("Example function 1");

        Kyle.removePoint(1, -1);
        Kyle.removePoint(1, -1);

        Kyle.removePoint(2, 2);
        Kyle.removePoint(2, 2);

        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");

        curK = curK.right;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.right;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.right;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.down;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.down;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.down;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "            HuP!");
        curK = curK.down;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "  PLONK!");
        curK = curK.right;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.up;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.up;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.up;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " HuP!");
        curK = curK.up;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " PLONK");
        curK = curK.right;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + "");
        curK = curK.down;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " Hup!");
        curK = curK.left;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " PLONK... Hup!");
        curK = curK.right;
        System.out.println("V1: " + curK.getVariables()[0] + " V2: " + curK.getVariables()[1] + " " + curK.getFunction().getFunctionName() + " PLONK");

        System.out.println("\n========Node/Function things========");

        System.out.println("\nGet point/Get value");
        System.out.println(Kyle.getPoint(1, 1).getValue());

        System.out.println("Null function test");
        Function testFunc = null;
        Node testNode = new Node(testFunc, 5, 5);
        System.out.println(testNode.getValue());

        System.out.println("countNumberOfPoints, Kyle, should be 35");
        System.out.println(Kyle.countNumberOfPoints());
        System.out.println("\ncountNumberOfPoints, Tasha, should be 9");
        System.out.println(Tasha.countNumberOfPoints()); 

        Kyle.clearAllData();
        Tasha.clearAllData();

        kyleArr = Kyle.toArray();

        System.out.println("\nTesting node count after clearAllData");
        System.out.println(Kyle.countNumberOfPoints()); 
       

        for(int i = 0; i < Kyle.countNumberOfPoints(); i++){ // Will run into proplems if your count or clearAllData doesn't work
            for(int j = 0; j < 6; j++){
                System.out.print(kyleArr[i].getNodeLinks()[j]);
                System.out.print(" | ");
            }
            System.out.println("");
        }

        System.out.println("\n========START OF ORIGINAL MAIN========");
        Interface interface1 = new Interface();
        for(int i=-1; i <= 1; i++)
        {
            for(int j=1; j >= -1; j--)
            {
                interface1.addPoint(new ExampleFunction1(), i, j);
            }
        }
        System.out.println(interface1.printFunctionValues((new ExampleFunction1()).getFunctionName()));
        for(int i=-2; i <= 2; i++)
        {
            for(int j=-2; j <= 2; j++)
            {
                interface1.addPoint(new ExampleFunction2(), i, j);
            }
        }

        System.out.println(interface1.printFunctionValues((new ExampleFunction2()).getFunctionName()));
        

        Node n1 = interface1.getPoint(1, 1);
        for(int i=0; i < 6; i++)
        {
            System.out.print(n1.getNodeLinks()[i]);
        }
        System.out.println();
        Node n2 = interface1.getPoint(2, 2);
        for(int i=0; i < 6; i++)
        {
            System.out.print(n2.getNodeLinks()[i]);
        }
        System.out.println();
        for(int i=0; i < 4; i++)
        {
            System.out.println("Count in Q" + i + ": " + interface1.numPointsPerQuadrant()[i]);
        }
        System.out.println("Number of nodes/points: " + interface1.countNumberOfPoints());
        System.out.println(n1.getFunction().getFunctionName());
        System.out.println(n1.prevVal.getFunction().getFunctionName());
        System.out.println(n2.getFunction().getFunctionName());
        Node n3 = interface1.removePoint(1, 1);
        if(n3 == n1)
            System.out.println("Correct");
        else 
            System.out.println("Problem");
        Node n4 = interface1.getPoint(1, 1);
        if(n4 != n1)
            System.out.println("Correct");
        else 
            System.out.println("Problem");
        System.out.println(n4.getFunction().getFunctionName());
        System.out.println(n4.getValue() + " == " + interface1.calculateValue((new ExampleFunction2()), 1, 1));
        
    }
}

class ExampleFunction1 extends Function{
    public ExampleFunction1(){
        functionName = "Example function 1";
    }

    public float calculate(int v1, int v2){
        return Math.abs(v1+v2);
    }

    public Function clone(){
        return new ExampleFunction1();
    }
}

class ExampleFunction2 extends Function{
    public ExampleFunction2(){
        functionName = "Example function 2";
    }

    public float calculate(int v1, int v2){
        return Math.max(v1, v2);
    }

    public Function clone(){
        return new ExampleFunction2();
    }
}

class ExampleFunction3 extends Function{
    public ExampleFunction3(){
        functionName = "Example function 3";
    }

    public float calculate(int v1, int v2){
        return (v1 - v2) * 5;
    }

    public Function clone(){
        return new ExampleFunction3();
    }
}

class ExampleFunction4 extends Function{
    public ExampleFunction4(){
        functionName = "Example function 4";
    }

    public float calculate(int v1, int v2){
        return v1 * 10 + v2;
    }

    public Function clone(){
        return new ExampleFunction4();
    }
}

/*
-!=================OUTPUT=================!-

========CREATING AND INTERFACE (KYLE)========

--------Adding nodes--------
Finding Kyles Nodes... V1: 1 V2: 0 V1Axis
Finding Kyles Nodes... V1: 13 V2: 0 V1Axis
Finding Kyles Nodes... V1: 13 V2: -3 Example function 1
Finding Kyles Nodes... V1: 13 V2: 0 V1Axis
Finding Kyles Nodes... V1: 17 V2: 0 V1Axis
Finding Kyles Nodes... V1: 17 V2: 3 Example function 1
Finding Kyles Nodes... V1: 17 V2: 0 V1Axis
Finding Kyles Nodes... V1: 13 V2: 0 V1Axis
Finding Kyles Nodes... V1: 1 V2: 0 V1Axis
Finding Kyles Nodes... V1: 0 V2: 0 Origin
Finding Kyles Nodes... V1: -1 V2: 0 V1Axis
Finding Kyles Nodes... V1: -1 V2: -1 Example function 1
Finding Kyles Nodes... V1: -1 V2: 0 V1Axis
Finding Kyles Nodes... V1: -12 V2: 0 V1Axis
Finding Kyles Nodes... V1: -12 V2: -300 Example function 1

--------Adding nodes from array to new Interface (Tasha)-------- Did you set the origin in the array constructor???
Finding Tashas Nodes... V1: 10 V2: 0 V1Axis
Finding Tashas Nodes... V1: 10 V2: 11 Example function 3
Finding Tashas Nodes... V1: 10 V2: 0 V1Axis
Finding Tashas Nodes... V1: 20 V2: 0 V1Axis
Finding Tashas Nodes... V1: 20 V2: 20 Example function 1
Finding Tashas Nodes... V1: 0 V2: 20 V2Axis // Jumping to v1 axis
Finding Tashas Nodes... V1: -40 V2: 20 Example function 3 // Jumping to -40 v1
Finding Tashas Nodes... V1: -50 V2: 20 Example function 1 // Jumping to -50 v1
Finding Tashas Nodes... V1: -40 V2: 20 Example function 3 // Jumping back to -40 v1
Finding Tashas Nodes... V1: -40 V2: 21 Example function 4 // Jumping up to 21 v2
Finding Tashas Nodes... V1: -420 V2: 21 Example function 1 // Jumping left to -420 v1

========Stacking Functions========
Finding Tashas Nodes...2 V1: 10 V2: 0 V1Axis
Finding Tashas Nodes...2 V1: 10 V2: 11 Example function 3 // Should be function 3
Finding Tashas Nodes...2 V1: 10 V2: 0 V1Axis
Finding Tashas Nodes...2 V1: 20 V2: 0 V1Axis
Finding Tashas Nodes...2 V1: 20 V2: 20 Example function 1 // Should be function 1
Finding Tashas Nodes...2 V1: 0 V2: 20 V2Axis // Jumping to v1 axis
Finding Tashas Nodes...2 V1: -40 V2: 20 Example function 3 // Should be function 3
Finding Tashas Nodes...2 V1: -50 V2: 20 Example function 1 // Should be function 1
Finding Tashas Nodes...2 V1: -40 V2: 20 Example function 3 // Should be function 3
Going in                 V1: -40 V2: 20 Example function 4 // Should be function 4
Coming Out
Finding Tashas Nodes...2 V1: -40 V2: 21 Example function 2 // Should be function 2
Going in                 V1: -40 V2: 21 Example function 4 // Should be function 4
Coming Out
Finding Tashas Nodes...2 V1: -420 V2: 21 Example function 1 // Should be function 1
Going in                 V1: -420 V2: 21 Example function 3 // Should be function 3
Coming Out    hah gey

========Getting Points========
Node@6bc7c054 should be a node
Node@232204a1 should be a node
Node@4aa298b7 should be a node
null should be null
null should be null
null should be null
null should be null

========Printing Points by Function========

Tasha
399;30;30;40;40// 5 Nodes
21       // 1 Node
-2205;-300;-5 // 3 Nodes
-380;-379;111  // 3 Nodes

Kyle
312;2;0;2;0;2;10;20// 8 Nodes
// 0 Nodes
// 0 Nodes
// 0 Nodes

========Removing Points========
Tasha
399;30;30;40;40// 4 Nodes
21       // 2 Nodes
-2205;-300 // 2 Nodes
-380;-379  // 2 Nodes

Kyle
312;2;0;0;2;10// 6 Nodes

========Testing Axis Removed========

V1: 0 V2: 0 Origin Axis v2 test 1 origin
V1: 0 V2: 20 V2Axis Axis v2 test 1 up
V1: 0 V2: 21 V2Axis Axis v2 test 1 up

V1: 0 V2: 22 V2Axis Axis v2 test 2 up
V1: 0 V2: 21 V2Axis Axis v2 test 2 down

V1: 0 V2: 21 V2Axis Axis v2 test 3 start
V1: 0 V2: 22 V2Axis Axis v2 test 3 up
V1: 0 V2: 21 V2Axis Axis v2 test 3 down

V1: 0 V2: 22 V2Axis Axis v2 test 4 up
V1: 0 V2: 21 V2Axis Axis v2 test 4 down

V1: 0 V2: 22 V2Axis Axis v2 test 5 up
V1: 0 V2: 21 V2Axis Axis v2 test 5 down

V1: 0 V2: 23 V2Axis Axis v2 test 6 up
V1: 0 V2: 21 V2Axis Axis v2 test 6 down

V1: 0 V2: 0 Origin Axis v1 test 1 origin
V1: 20 V2: 0 V1Axis Axis v1 test 1 right

V1: 21 V2: 0 V1Axis Axis v1 test 2 right
V1: 20 V2: 0 V1Axis Axis v1 test 2 left

V1: 20 V2: 0 V1Axis Axis v1 test 3 start
V1: 21 V2: 0 V1Axis Axis v1 test 3 right
V1: 20 V2: 0 V1Axis Axis v1 test 3 left

V1: 21 V2: 0 V1Axis Axis v1 test 4 right
V1: 20 V2: 0 V1Axis Axis v1 test 4 left

V1: 21 V2: 0 V1Axis Axis v1 test 5 right
V1: 20 V2: 0 V1Axis Axis v1 test 5 left

V1: 21 V2: 0 V1Axis Axis v1 test 6 right
V1: 20 V2: 0 V1Axis Axis v1 test 6 left

========Removing Function 2 Nodes========
Tasha
399;30;30;40;40// 4 Nodes
// 0 Nodes Goodbye
-2205;-300 // 2 Nodes
-380;-379  // 2 Nodes

Kyle
312;2;0;0;2;10// 6 Nodes
// 0 Nodes Goodbye
-2205;-300 // 3 Nodes
-380;-379  // 2 Nodes

Adding functions to kyle real quick... bur bur brub...

========Admin...========

--------Counting Kyle's nodes--------
12

--------Finding Tasha's Max Node--------:
Node@74a14482 v1: -420, v2: 21

--------Finding Tasha's Min Node--------:
Node@1540e19d v1: -40, v2: 21

--------Finding Kyle's min Value--------:
-1820.0

--------Finding Kyle's max Value--------:
1110.0

========Squashing Kyle into an array========

--------And printing his node links--------

U[][]{} | D[69][0]{0} | R[][]{} | L[0][420]{0} | P[][]{} | N[][]{} |
U[13][0]{0} | D[][]{} | R[][]{} | L[0][-3]{0} | P[][]{} | N[][]{} |
U[][]{} | D[11][0]{0} | R[][]{} | L[0][3]{0} | P[][]{} | N[][]{} |
U[][]{} | D[4][0]{0} | R[][]{} | L[1][1]{2} | P[][]{} | N[][]{} |
U[][]{} | D[2][0]{0} | R[][]{} | L[0][2]{0} | P[][]{} | N[][]{} |
U[][]{} | D[1][0]{0} | R[4][1]{5} | L[0][1]{0} | P[][]{} | N[][]{} |
U[1][0]{0} | D[][]{} | R[][]{} | L[0][-1]{0} | P[][]{} | N[][]{} |
U[][]{} | D[-1][0]{0} | R[0][1]{0} | L[][]{} | P[][]{} | N[][]{} |
U[-1][0]{0} | D[][]{} | R[0][-1]{0} | L[][]{} | P[][]{} | N[][]{} |
U[-12][0]{0} | D[][]{} | R[0][-300]{0} | L[][]{} | P[][]{} | N[][]{} |
U[-69][0]{0} | D[][]{} | R[0][-420]{0} | L[][]{} | P[][]{} | N[][]{} |
U[-420][0]{0} | D[][]{} | R[0][-56]{0} | L[][]{} | P[][]{} | N[][]{} |

SHOULD BE:
U[][]{} | D[69][0]{0} | R[][]{} | L[0][420]{0} | P[][]{} | N[][]{} |
U[13][0]{0} | D[][]{} | R[][]{} | L[0][-3]{0} | P[][]{} | N[][]{} |
U[][]{} | D[11][0]{0} | R[][]{} | L[0][3]{0} | P[][]{} | N[][]{} |
U[][]{} | D[4][0]{0} | R[][]{} | L[1][1]{2} | P[][]{} | N[][]{} |
U[][]{} | D[2][0]{0} | R[][]{} | L[0][2]{0} | P[][]{} | N[][]{} |
U[][]{} | D[1][0]{0} | R[4][1]{5} | L[0][1]{0} | P[][]{} | N[][]{} |
U[1][0]{0} | D[][]{} | R[][]{} | L[0][-1]{0} | P[][]{} | N[][]{} |
U[][]{} | D[-1][0]{0} | R[0][1]{0} | L[][]{} | P[][]{} | N[][]{} |
U[-1][0]{0} | D[][]{} | R[0][-1]{0} | L[][]{} | P[][]{} | N[][]{} |
U[-12][0]{0} | D[][]{} | R[0][-300]{0} | L[][]{} | P[][]{} | N[][]{} |
U[-69][0]{0} | D[][]{} | R[0][-420]{0} | L[][]{} | P[][]{} | N[][]{} |
U[-420][0]{0} | D[][]{} | R[0][-56]{0} | L[][]{} | P[][]{} | N[][]{} |

========Counting Nodes in quadrants========
Tasha

====V2====
||Q1||Q0||
V1==Og==||
||Q2||Q3||
==========

Count in Q0: 2
Count in Q1: 7
Count in Q2: 0
Count in Q3: 0

Should be:
Count in Q0: 2
Count in Q1: 7
Count in Q2: 0
Count in Q3: 0

Kyle
Count in Q0: 5
Count in Q1: 1
Count in Q2: 4
Count in Q3: 2

Should be:
Count in Q0: 5
Count in Q1: 1
Count in Q2: 4
Count in Q3: 2


========Printing Function Values1========

Kyle:
312;2;0;0;2;4;5;10
-69
-1820
113;1110
Should be:
312;2;0;0;2;4;5;10
-69
-1820
113;1110

Tasha:
399;30;30;40;40

-2205;-300
-380;-379
Should be:
399;30;30;40;40
*blank space*
-2205;-300
-380;-379

========Traversing around========
V1: -1 V2: 0 V1Axis
V1: -2 V2: 0 V1Axis
V1: -2 V2: 1 Example function 1
V1: -2 V2: 2 Example function 1
V1: -2 V2: 3 Example function 1
V1: -1 V2: 3 Example function 1
V1: 0 V2: 3 V2Axis
V1: -1 V2: 3 Example function 1
V1: -2 V2: 3 Example function 1
V1: -2 V2: 2 Example function 1
V1: -2 V2: 3 Example function 1 // Should be function 1
V1: -2 V2: 3 Example function 2 // Should be function 2
V1: -2 V2: 3 Example function 3 // Should be function 3
V1: -2 V2: 3 Example function 4 // Should be function 4
--------Skipping over holes--------
V1: -2 V2: 3 Example function 1
V1: -2 V2: 3 Example function 2
V1: -2 V2: 3 Example function 3
V1: -2 V2: 3 Example function 2
V1: -2 V2: 3 Example function 2
V1: -1 V2: 3 Example function 2
V1: 0 V2: 3 V2Axis
V1: 1 V2: 3 Example function 2
V1: 1 V2: 2 Example function 2
V1: 1 V2: 1 Example function 2
V1: 1 V2: 0 V1Axis            HuP!
V1: 1 V2: -2 Example function 2  PLONK!
V1: 2 V2: -2 Example function 2
V1: 2 V2: -1 Example function 2
V1: 2 V2: 0 V1Axis
V1: 2 V2: 1 Example function 2 HuP!
V1: 2 V2: 3 Example function 2 PLONK
V1: 3 V2: 3 Example function 2
V1: 3 V2: 2 Example function 2 Hup!
V1: 1 V2: 2 Example function 2 PLONK... Hup!
V1: 3 V2: 2 Example function 2 PLONK

========Node/Function things========

Get point/Get value
1.0
Null function test
-Infinity
countNumberOfPoints, Kyle, should be 35
35

countNumberOfPoints, Tasha, should be 9
9

Testing node count after clearAllData
0

!========START OF ORIGINAL MAIN========!

2;0;0;2
-2;-1;1;2;-1;-1;1;2;1;1;1;2;2;2;2;2
U[1][2]{2}D[1][0]{0}R[2][1]{2}L[0][1]{0}P[1][1]{1}N[][]{}
U[][]{}D[2][1]{2}R[][]{}L[1][2]{2}P[][]{}N[][]{}
Count in Q0: 5
Count in Q1: 5
Count in Q2: 5
Count in Q3: 5
Number of nodes/points: 20
Example function 1
Example function 2
Example function 2
Correct
Correct
Example function 2
1.0 == 1.0 */
public class TopologicalSort extends Sorting {
    public TopologicalSort(){
        this.name = "TopologicalSort";
    }

    int i = 0;
    int j = 0;
    @Override
    public String[] sortAcs(Vertex[] array) throws CycleException {
        i = 1;
        j = array.length;

        for (int x = 0; x < array.length; x++) {
            array[x].setNum(0);
            array[x].setTSNum(0);
        }

        int s = 0;
        while (s < array.length && array[s].getNum() == 0) {
            TS(array[s]);
            s++;
        }

        int length = array.length;
        for (int p = 1; p <= length-1; p++) {
            Vertex temp = array[p];
            int l = p-1;
            while (l >= 0 && array[l].getTSNum() > temp.getTSNum()) {
                array[l+1] = array[l];
                l--;
            }
            array[l+1] = temp;
        }

        return vertexToString(array);
    }

    @Override
    public String[] sortDsc(Vertex[] array) throws CycleException{
        i = 1;
        j = array.length;

        for (int x = 0; x < array.length; x++) {
            array[x].setNum(0);
            array[x].setTSNum(0);
        }

        int s = 0;
        while (s < array.length && array[s].getNum() == 0) {
            TS(array[s]);
            s++;
        }

        int length = array.length;
        for (int p = 1; p <= length-1; p++) {
            Vertex temp = array[p];
            int l = p-1;
            while (l >= 0 && array[l].getTSNum() < temp.getTSNum()) {
                array[l+1] = array[l];
                l--;
            }
            array[l+1] = temp;
        }

        return vertexToString(array);
    }

    private void TS(Vertex v) throws CycleException {
        v.setNum(i++);
        for (int k = 0; k < v.getEdges().length; k++) {
            if (v.getEdges()[k] != null) {
                Vertex u = v.getEdges()[k].pointB;

                if (u.getNum() == 0)
                    TS(u);
                else if (u.getTSNum() == 0)
                    throw new CycleException();
            }
        }
        v.setTSNum(j--);
    }
}

class CycleException extends Exception{
    public String message = "Cycle has been detected";
}

class Stack {
    private String arr[];
    private int top;

   Stack() {
       arr = new String[100];
       int top = -1;
   }

   public void push(String x) {
       arr[++top] = x;
   }
   public String pop() {
       return arr[top--];
   }
}

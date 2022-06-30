public class SelectionSort extends Sorting {
    
    public SelectionSort(){
        this.name = "SelectionSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        int length = array.length;
        for (int i = 0; i <= length-1; i++) {
            int min = i;
            for (int j = i; j < length; j++)
                if (array[j].getVName().compareTo(array[min].getVName()) < 0)
                    min = j;

            Vertex temp = array[min];
            array[min] = array[i];
            array[i] = temp;
            this.printArr(vertexToString(array));
        }

        return vertexToString(array);
    }

    @Override
    public String[] sortDsc(Vertex[] array) {
        int length = array.length;
        for (int i = 0; i <= length-1; i++) {
            int min = i;
            for (int j = i; j < length; j++)
                if (array[j].getVName().compareTo(array[min].getVName()) > 0)
                    min = j;

            Vertex temp = array[min];
            array[min] = array[i];
            array[i] = temp;
            this.printArr(vertexToString(array));
        }

        return vertexToString(array);
    }
}

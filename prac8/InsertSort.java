public class InsertSort extends Sorting {

    public InsertSort(){
        this.name = "InsertSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        int length = array.length;
        for (int i = 1; i <= length-1; i++) {
            Vertex temp = array[i];
            int j = i-1;
            while (j >= 0 && array[j].getVName().compareTo(temp.getVName()) > 0) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;
            this.printArr(vertexToString(array));
        }

        return vertexToString(array);
    }

    @Override
    public String[] sortDsc(Vertex[] array) {
        int length = array.length;
        for (int i = 1; i <= length-1; i++) {
            Vertex temp = array[i];
            int j = i-1;
            while (j >= 0 && array[j].getVName().compareTo(temp.getVName()) < 0) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;
            this.printArr(vertexToString(array));
        }

        return vertexToString(array);
    }
}

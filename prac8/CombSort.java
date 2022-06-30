public class CombSort extends Sorting {
    public CombSort(){
        this.name = "CombSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        int length = array.length;
        int gap = length;
        boolean swapped = true;

        while (gap != 1 || swapped) {
            gap = gap(gap);
            swapped = false;

            for (int i = 0; i < length-gap; i++) {
                if (array[i].getVName().compareTo(array[i+gap].getVName()) > 0) {
                    Vertex temp = array[i];
                    array[i] = array[i+gap];
                    array[i+gap] = temp;

                    swapped = true;
                }
            }
            this.printArr(vertexToString(array));
            System.out.println("Gap: "+gap);
        }

        return vertexToString(array);
    }

    @Override
    public String[] sortDsc(Vertex[] array) {
        int length = array.length;
        int gap = length;
        boolean swapped = true;

        while (gap != 1 || swapped) {
            gap = gap(gap);
            swapped = false;

            for (int i = 0; i < length-gap; i++) {
                if (array[i].getVName().compareTo(array[i+gap].getVName()) < 0) {
                    Vertex temp = array[i];
                    array[i] = array[i+gap];
                    array[i+gap] = temp;

                    swapped = true;
                }
            }
            this.printArr(vertexToString(array));
            System.out.println("Gap: "+gap);
        }

        return vertexToString(array);
    }

    private int gap(int gap) {
        gap = (gap*10)/13;
        return (gap < 1) ? 1 : gap;
    }
}



@SuppressWarnings("unchecked")
public class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap(int capacity) {
	super(capacity);
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    @Override
    public void insert(T elem) {
        if (currentSize >= capacity) return;

        mH[++currentSize] = elem;
        int i = currentSize;

        //Only swap if not root node
        while (i > 1 && ((Comparable) mH[i]).compareTo(mH[i/2]) < 0) {
            swap(i, (i/2));
            i = (i/2);
        }
    }

    public T removeMin() {
        if (isEmpty()) return null;

        //Store removed node
        T popped = ((T) mH[1]);

        //replace first node
        mH[1] = mH[currentSize--];

        //rebalance
        heapify(1);

        return popped;
    }

    public void delete(T elem) {
        int index = getIndex(elem);

        if (index == -1) return;

        //Replace node with last node
        mH[index] = mH[currentSize--];

        //Rebalance
        heapify(index);
    }


    //Helper functions
    private void swap(int i, int j)
    {
        Comparable<T> tmp;
        tmp = mH[i];

        mH[i] = mH[j];
        mH[j] = tmp;
    }

    private void heapify(int i)
    {
        if (!(i > (currentSize / 2) && i <= currentSize)) {
            if (((Comparable) mH[i]).compareTo(mH[2 * i] ) > 0|| ((Comparable) mH[i]).compareTo(mH[(2 * i) + 1]) > 0) {
                if (((Comparable) mH[2 * i]).compareTo(mH[(2 * i) + 1]) < 0) {
                    swap(i, (2 * i));
                    heapify((2 * i));
                }
                else {
                    swap(i, ((2 * i) + 1));
                    heapify(((2 * i) + 1));
                }
            }
        }
    }

    private int getIndex(T elem) {
        for(int i = 0; i < currentSize; ++i) {
            if (mH[i] == elem) {
                return i;
            }
        }
        return -1;
    }

}
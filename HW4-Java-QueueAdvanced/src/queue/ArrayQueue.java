package queue;

import java.util.Iterator;

public class ArrayQueue extends AbstractQueue {
    private int head, tail;
    private Object[] elements;

    public ArrayQueue() {
        head = tail = 0;
        elements = new Object[2];
    }

    protected void enqueueImpl(Object e) {
        if (size() == elements.length) {
            increaseCapacity();
        }
        elements[tail] = e;
        tail = inc(tail);
    }

    protected void dequeueImpl() {
        elements[head] = null;
        head = inc(head);
    }

    protected Object head() {
        return elements[head];
    }

    protected void clearImpl() {
        head = tail = 0;
        elements = new Object[2];
    }

    public Object[] toArray() {
        Object[] result = new Object[size()];
        if (head < tail || head == tail && elements[head] == null) {
            System.arraycopy(elements, head, result, 0, tail - head);
        } else {
            System.arraycopy(elements, head, result, 0, elements.length - head);
            System.arraycopy(elements, 0, result, elements.length - head, tail);
        }
        return result;
    }

    public Iterator<Object> iterator() {
        return new ArrayQueueIterator();
    }

    protected Queue getQueue() {
        return new ArrayQueue();
    }

    private void increaseCapacity() {
        Object[] increased = new Object[elements.length * 2];
        System.arraycopy(elements, head, increased, 0, elements.length - head);
        System.arraycopy(elements, 0, increased, elements.length - head, tail);
        elements = increased;
        head = 0;
        tail = elements.length / 2;
    }

    private int inc(int a) {
        return (a + 1) % elements.length;
    }

    public class ArrayQueueIterator extends AbstractQueueIterator {
        private int pnt = (elements.length + head - 1) % elements.length;

        @Override
        public Object nextImpl() {
            pnt = inc(pnt);
            return elements[pnt];
        }
    }
}

package tech.insight;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author gongxuanzhangmelt@gmail.com
 **/
public class LinkedList<E> implements List<E> {

    private int size;
    private Node<E> head;
    private Node<E> tail;


    @Override
    public void add(E element) {
        Node<E> node = new Node<>(tail, element, null);
        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }
        tail = node;
        size++;
    }

    @Override
    public void add(E element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(element);
            return;
        }
        Node<E> indexNode = findNode(index);
        Node<E> pre = indexNode.pre;
        Node<E> node = new Node<>(pre, element, indexNode);
        if (pre == null) {
            head = node;
        } else {
            pre.next = node;
        }
        indexNode.pre = node;
        size++;
    }

    private Node<E> findNode(int index) {
        Node<E> result = null;
        if (index < size / 2) {
            result = head;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            result = tail;
            for (int i = size - 1; i > index; i--) {
                result = result.pre;
            }
        }
        return result;
    }


    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return removeNode(findNode(index));
    }

    private E removeNode(Node<E> node) {
        Node<E> pre = node.pre;
        Node<E> next = node.next;
        if (pre == null) {
            head = next;
        } else {
            pre.next = next;
        }
        if (next == null) {
            tail = pre;
        } else {
            next.pre = pre;
        }
        node.pre = null;
        node.next = null;
        size--;
        return node.value;
    }

    @Override
    public boolean remove(E element) {
        Node<E> node = head;
        while (node != null) {
            if (Objects.equals(node.value, element)) {
                removeNode(node);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = findNode(index);
        E oldValue = node.value;
        node.value = element;
        return oldValue;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return findNode(index).value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    class LinkedListIterator implements Iterator<E> {
        Node<E> node = head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            if (node == null) {
                throw new NoSuchElementException();
            }
            E result = node.value;
            node = node.next;
            return result;
        }
    }

    class Node<E> {
        Node<E> pre;
        Node<E> next;
        E value;

        public Node(E value) {
            this.value = value;
        }

        public Node(Node<E> pre, E value, Node<E> next) {
            this.pre = pre;
            this.value = value;
            this.next = next;
        }

    }


}

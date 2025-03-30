package tech.insight;


/**
 * @author gongxuanzhangmelt@gmail.com
 **/
public class MyHashMap<K, V> {

    private Node<K, V>[] table = new Node[16];

    private int size = 0;

    public V put(K key, V value) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            table[keyIndex] = new Node<>(key, value);
            size++;
            resizeIfNecessary();
            return null;
        }
        while (true) {
            if (head.key.equals(key)) {
                V oldValue = head.value;
                head.value = value;
                return oldValue;
            }
            if (head.next == null) {
                head.next = new Node<>(key, value);
                size++;
                resizeIfNecessary();
                return null;
            }
            head = head.next;
        }
    }

    public V get(K key) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            return null;
        }
        if (head.key.equals(key)) {
            table[keyIndex] = head.next;
            size--;
            return head.value;
        }
        Node<K, V> pre = head;
        Node<K, V> current = head.next;
        while (current != null) {
            if (current.key.equals(key)) {
                pre.next = current.next;
                size--;
                return current.value;
            }
            pre = pre.next;
            current = current.next;
        }
        return null;
    }


    private void resizeIfNecessary() {
        if (this.size < table.length * 0.75) {
            return;
        }
        Node<K, V>[] newTable = new Node[this.table.length * 2];
        for (Node<K, V> head : this.table) {
            if (head == null) {
                continue;
            }
            Node<K, V> current = head;
            while (current != null) {
                int newIndex = current.key.hashCode() & (newTable.length - 1);
                if (newTable[newIndex] == null) {
                    newTable[newIndex] = current;
                    Node<K, V> next = current.next;
                    current.next = null;
                    current = next;
                } else {
                    Node<K, V> next = current.next;
                    current.next = newTable[newIndex];
                    newTable[newIndex] = current;
                    current = next;
                }
            }
        }
        this.table = newTable;
        System.out.println("扩容了，扩容到" + this.table.length);
    }

    public int size() {
        return size;
    }

    private int indexOf(Object key) {
        return key.hashCode() & (table.length - 1);
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


}

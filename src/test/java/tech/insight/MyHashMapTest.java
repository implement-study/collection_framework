package tech.insight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class MyHashMapTest {


    @Test
    void testApi() {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        int count = 100000;
        for (int i = 0; i < count; i++) {
            myHashMap.put(String.valueOf(i), String.valueOf(i));
        }

        assertEquals(count, myHashMap.size());

        for (int i = 0; i < count; i++) {
            assertEquals(String.valueOf(i), myHashMap.get(String.valueOf(i)));
        }

        myHashMap.remove("8");
        assertNull(myHashMap.get("8"));

        assertEquals(count - 1, myHashMap.size());

    }

}

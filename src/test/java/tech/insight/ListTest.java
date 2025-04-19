package tech.insight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author gongxuanzhangmelt@gmail.com
 **/
class ListTest {

    @Test
    void operateTest() {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            list.add(String.valueOf(i));
        }
        assertEquals(30, list.size());
        list.remove(15);
        list.remove("18");
        assertEquals(28, list.size());
        assertEquals("16", list.get(15));
        assertEquals("24", list.get(22));

        list.forEach(System.out::println);
    }

}

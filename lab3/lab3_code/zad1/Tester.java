package lab3_code.zad1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class Tester {
    
    /**
     * Test for PSEUDO REGEX
     * @throws Exception
     */
    @Test
    public void TestItemRegex() throws Exception
    {
        assertFalse(new Item("1", "aaa","123").CompareByName("bbbb"));
        assertTrue(new Item("1", "aaa","123").CompareByName("???"));
        assertTrue(new Item("1", "12345","123").CompareByName("1?*?5"));
        assertTrue(new Item("1", "12345","123").CompareByName("1?*5"));
        assertTrue(new Item("1", "12345","123").CompareByName("12?*?"));
        assertTrue(new Item("1", "12345","123").CompareByName("12?*?"));
        assertFalse(new Item("1", "12345","123").CompareByName("?1*"));
        assertTrue(new Item("1", "12345","123").CompareByName("?*?"));
        assertFalse(new Item("1", "12345","123").CompareByName("?*?1"));
        assertFalse(new Item("1", "12345","123").CompareByName("?*1?"));
        assertTrue(new Item("1", "aaa","123").CompareByName("?a?"));
        assertFalse(new Item("1", "aaa","123").CompareByName("?b?"));
        assertFalse(new Item("1", "aaa","123").CompareByName("b"));
        assertFalse(new Item("1", "aaa","123").CompareByName("bas"));
        assertTrue(new Item("1", "aaa","123").CompareByName("aaa"));


        assertFalse(new Item("1", "pomidor","123").CompareByName("m*"));
        assertTrue(new Item("1", "pomidor","123").CompareByName("p*r"));
        assertTrue(new Item("1", "banan","123").CompareByName("ba*an"));
        assertTrue(new Item("1", "baklazan","123").CompareByName("ba*an"));
        assertTrue(new Item("1", "baklazan","123").CompareByName("ba*a?"));
        assertTrue(new Item("1", "baklaeeean","123").CompareByName("ba*an"));


        var items = new Item[]{ 
            new Item("1", "banan", "1"),
            new Item("2", "baklazan","2"), 
            new Item("3", "baklaeeean","2") 
        };
        String[] toTest = new String[]{ "?a*", "ba*an", "b*?", "ba??*n", "b*n", "***", "*"};

        for (int i = 0; i < toTest.length; i++) {
            for (int j = 0; j < items.length; j++) {
                String name = items[j].GetName();
                String key = toTest[i];
                assertTrue(items[j].CompareByName(toTest[i]));
            }
        }

    }

}

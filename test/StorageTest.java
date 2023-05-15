import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void testNotNull() {
        Storage storage = new Storage();

        String q = "question";
        String a = "answer";
        storage.setQnA(q, a);
        
        assertEquals(a, storage.getAns(q));
        assertEquals(storage.getSize(), 1);
    }

    @Test
    public void testQNull() {
        Storage storage = new Storage();

        String q = null;
        String a = "answer";

        storage.setQnA(q, a);
        storage.getAns(q);
        
        assertNotEquals(a, storage.getAns(q));
        assertEquals("Invalid input.", storage.getAns(q));

        assertEquals(0, storage.getSize());
    }

    @Test
    public void testANull() {
        Storage storage = new Storage();

        String q = "question";
        String a = null;

        storage.setQnA(q, a);
        storage.getAns(q);
        
        assertNotEquals(a, storage.getAns(q));
        assertEquals("Error: no answer for question stored.", storage.getAns(q));

        assertEquals(0, storage.getSize());
    }

    @Test
    public void testQandANull() {
        Storage storage = new Storage();

        String q = null;
        String a = null;

        storage.setQnA(q, a);
        storage.getAns(q);
        
        assertNotEquals(a, storage.getAns(q));
        assertEquals("Invalid input.", storage.getAns(q));

        assertEquals(0, storage.getSize());
    }
}

import com.mycompany.chatapp_1.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class Message_Test {

    private Message message;

    @BeforeEach
    public void setUp() {
        Message.resetArrays();
        message = new Message();
    }
// ================Part 2=======================
    @Test
    public void testMessageLength_Valid() {
        message.setMessage("Hi Mike, can you join us for dinner tonight?");
        Assertions.assertEquals("Message ready to send.", message.checkMessageLength());
    }

    @Test
    public void testMessageLength_Invalid() {
        String longMessage = "A".repeat(251);
        message.setMessage(longMessage);

        Assertions.assertEquals(
                "Message exceeds 250 characters by 1, please reduce the size.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testRecipientCell_Valid() {
        message.setRecipient("+27718693002");
        Assertions.assertTrue(message.checkRecipientCell());
    }

    @Test
    public void testRecipientCell_Invalid() {
        message.setRecipient("08575975889");
        Assertions.assertFalse(message.checkRecipientCell());
    }

    @Test
    public void testMessageID_Generated() {
        Assertions.assertTrue(message.checkMessageID());
        Assertions.assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testMessageHash_Created() {
        message.setMessage("Hi Mike, can you join us for dinner tonight?");

        String hash = message.createMessageHash();

        Assertions.assertNotNull(hash);
        Assertions.assertTrue(hash.contains(":"));
        Assertions.assertTrue(hash.endsWith("HITONIGHT?"));
    }

    @Test
    public void testMessageHash_OneWordMessage() {
        message.setMessage("Hello");

        String hash = message.createMessageHash();

        Assertions.assertNotNull(hash);
        Assertions.assertTrue(hash.endsWith("HELLOHELLO"));
    }

    // ---------- PART 2: SENT MESSAGE OPTIONS ----------

    @Test
    public void testSentMessage_Send() {
        message.setRecipient("+27718693002");
        message.setMessage("Hi Mike, can you join us for dinner tonight?");

        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = message.SentMessage();

        Assertions.assertEquals("Message successfully sent.", result);
        Assertions.assertTrue(message.returnTotalMessages() >= 1);
    }

    @Test
    public void testSentMessage_Disregard() {
        message.setRecipient("+27718693002");
        message.setMessage("Hi Mike, can you join us for dinner tonight?");

        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = message.SentMessage();

        Assertions.assertEquals("Message deleted.", result);
    }

    @Test
    public void testSentMessage_Store() {
        message.setRecipient("+27718693002");
        message.setMessage("Hi Keegan, did you receive the payment?");

        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = message.SentMessage();

        Assertions.assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testSentMessage_InvalidOption() {
        message.setRecipient("+27718693002");
        message.setMessage("Testing invalid option");

        String input = "9\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = message.SentMessage();

        Assertions.assertEquals("Invalid option selected.", result);
    }

    
    // ===================== PART 3 TESTS =====================

@Test
public void testPart3_SentMessagesArrayCorrectlyPopulated() {

    Message.addSentMessage(
            "0011111111",
            "00:1:DIDCAKE?",
            "+27838884567",
            "Did you get the cake?"
    );

    Message.addSentMessage(
            "0022222222",
            "00:2:ITTIME!",
            "+27838884567",
            "It is dinner time!"
    );

    Assertions.assertEquals(2, Message.getSentCount());

    Assertions.assertTrue(Message.getSentMessage(0).contains("Did you get the cake?"));
    Assertions.assertTrue(Message.getSentMessage(1).contains("It is dinner time!"));
}

@Test
public void testPart3_DisplayLongestMessage() {

    Message.addStoredMessage(
            "0011111111",
            "00:1:DIDCAKE?",
            "+27838884567",
            "Did you get the cake?"
    );

    Message.addStoredMessage(
            "0022222222",
            "00:2:WHEREON?",
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

    Message.addStoredMessage(
            "0033333333",
            "00:3:OKYOU.",
            "+27838884567",
            "Ok, I am leaving without you."
    );

    Message.addStoredMessage(
            "0838884567",
            "08:4:ITTIME!",
            "+27838884567",
            "It is dinner time!"
    );

    String result = Message.displayLongestMessage();

    Assertions.assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
}

@Test
public void testPart3_SearchForMessageID() {

    Message.addStoredMessage(
            "0838884567",
            "08:4:ITTIME!",
            "+27838884567",
            "It is dinner time!"
    );

    String result = Message.searchByMessageID("0838884567");

    Assertions.assertTrue(result.contains("It is dinner time!"));
}

@Test
public void testPart3_SearchMessagesByRecipient() {

    Message.addStoredMessage(
            "0011111111",
            "00:1:WHEREON?",
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

    Message.addStoredMessage(
            "0022222222",
            "00:2:OKYOU.",
            "+27838884567",
            "Ok, I am leaving without you."
    );

    String result = Message.searchByRecipient("+27838884567");

    Assertions.assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
    Assertions.assertTrue(result.contains("Ok, I am leaving without you."));
}

@Test
public void testPart3_DeleteMessageUsingMessageHash() {

    Message.addStoredMessage(
            "0011111111",
            "00:1:WHEREON?",
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

    String result = Message.deleteByMessageHash("00:1:WHEREON?");

    Assertions.assertEquals("Message deleted successfully.", result);
}

@Test
public void testPart3_DisplayReport() {

    Message.addStoredMessage(
            "0011111111",
            "00:1:DIDCAKE?",
            "+27838884567",
            "Did you get the cake?"
    );

    Message.addStoredMessage(
            "0022222222",
            "00:2:ITTIME!",
            "+27838884567",
            "It is dinner time!"
    );

    String report = Message.displayReport();

    Assertions.assertTrue(report.contains("Stored Messages Report"));
    Assertions.assertTrue(report.contains("Message Hash"));
    Assertions.assertTrue(report.contains("Recipient"));
    Assertions.assertTrue(report.contains("Message"));
    Assertions.assertTrue(report.contains("Did you get the cake?"));
    Assertions.assertTrue(report.contains("It is dinner time!"));
}
}
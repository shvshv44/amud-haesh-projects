package encryptors

import org.junit.Test


class RepeatEncryptorTest extends GroovyTestCase {
    @Test
    public void test() {
        Encryptor repeat = new RepeatEncryptor();
        String ben = "ben";
        assertEquals(ben, repeat.decrypt(repeat.encrypt(ben)), "decrypted and encrypted text doesn't match")
    }
}

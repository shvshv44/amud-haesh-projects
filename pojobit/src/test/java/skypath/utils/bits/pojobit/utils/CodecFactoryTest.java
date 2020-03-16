package skypath.utils.bits.pojobit.utils;

import org.junit.Test;
import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.codecs.Codec;
import static org.junit.Assert.*;

public class CodecFactoryTest {

    private class TestPojo {}

    private class TestCodec implements Codec<TestPojo> {
        @Override
        public void serialize(TestPojo object, BitBuffer destinationBuffer, Bound boundAnnot) { }

        @Override
        public TestPojo deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) { return null; }
    }

    @Test
    public void addCodecTest() {
        CodecsFactory codecsFactory = new CodecsFactory();
        TestCodec testCodec = new TestCodec();
        codecsFactory.addCodec(TestPojo.class, testCodec);
        assertEquals(testCodec, codecsFactory.getCodec(TestPojo.class));
    }


}

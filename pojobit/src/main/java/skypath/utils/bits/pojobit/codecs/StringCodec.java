package skypath.utils.bits.pojobit.codecs;

import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.bits.BitSizeOf;

import java.nio.charset.Charset;

public class StringCodec implements Codec<String> {

    public void serialize(String object, BitBuffer destinationBuffer, Bound boundAnnot) {
        Charset charset = Charset.forName(boundAnnot.charset());
        byte [] bytes = charset.encode(object).array();
        int numberOfBits = BitSizeOf.CHAR.value() * object.length();
        destinationBuffer.add(bytes,numberOfBits);
    }

    public String deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return null;
    }
}

package shaq.pojobit.codecs;

import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;
import shaq.pojobit.bits.BitSizeOf;

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

package shaq.pojobit.codecs;


import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;
import shaq.pojobit.bits.BitSizeOf;
import shaq.pojobit.bits.ByteSizeOf;

import java.nio.ByteBuffer;

public class CharCodec implements Codec<Character>{

    public void serialize(Character object, BitBuffer destinationBuffer, Bound boundAnnot) {
        byte [] bytes = ByteBuffer.allocate(ByteSizeOf.CHAR.value()).putChar(object).array();
        destinationBuffer.add(bytes, BitSizeOf.CHAR.value());
    }

    public Character deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return null;
    }
}

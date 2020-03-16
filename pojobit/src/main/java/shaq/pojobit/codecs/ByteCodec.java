package shaq.pojobit.codecs;

import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;
import shaq.pojobit.bits.BitSizeOf;
import shaq.pojobit.bits.ByteSizeOf;

import java.nio.ByteBuffer;

public class ByteCodec implements Codec<Byte> {

    public void serialize(Byte object, BitBuffer destinationBuffer, Bound boundAnnot) {
        byte [] bytes = ByteBuffer.allocate(ByteSizeOf.BYTE.value()).put(object).array();
        destinationBuffer.add(bytes, BitSizeOf.BYTE.value());
    }

    public Byte deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return null;
    }
}

package shaq.pojobit.codecs;

import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;
import shaq.pojobit.bits.BitSizeOf;

import java.nio.ByteBuffer;

public class ByteArrayCodec implements Codec<byte[]> {
    public void serialize(byte[] object, BitBuffer destinationBuffer, Bound boundAnnot) {
        int size = BitSizeOf.BYTE.value() * object.length;
        byte [] bytes = ByteBuffer.allocate(object.length).put(object).array();
        destinationBuffer.add(bytes,size);
    }

    public byte[] deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return new byte[0];
    }
}

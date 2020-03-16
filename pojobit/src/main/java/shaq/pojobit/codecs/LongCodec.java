package shaq.pojobit.codecs;

import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;
import shaq.pojobit.bits.BitSizeOf;
import shaq.pojobit.bits.ByteSizeOf;

import java.nio.ByteBuffer;

public class LongCodec implements Codec<Long> {

    public void serialize(Long object, BitBuffer destinationBuffer, Bound boundAnnot) {
        int numberOfBits = boundAnnot.size();
        if(numberOfBits > BitSizeOf.LONG.value() || numberOfBits == 0) {
            numberOfBits = BitSizeOf.LONG.value();
        }

        byte [] bytes = ByteBuffer.allocate(ByteSizeOf.LONG.value()).putLong(object).array();
        destinationBuffer.add(bytes,numberOfBits);
    }

    public Long deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return null;
    }
}

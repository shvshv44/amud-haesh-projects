package shaq.pojobit.codecs;

import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;
import shaq.pojobit.bits.BitSizeOf;
import shaq.pojobit.bits.ByteSizeOf;

import java.nio.ByteBuffer;

public class ShortCodec implements Codec<Short> {

    public void serialize(Short object, BitBuffer destinationBuffer, Bound boundAnnot) {
        int numberOfBits = boundAnnot.size();
        if(numberOfBits > BitSizeOf.SHORT.value() || numberOfBits == 0) {
            numberOfBits = BitSizeOf.SHORT.value();
        }

        byte [] bytes = ByteBuffer.allocate(ByteSizeOf.SHORT.value()).putShort(object).array();
        destinationBuffer.add(bytes,numberOfBits);
    }

    public Short deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return null;
    }
}

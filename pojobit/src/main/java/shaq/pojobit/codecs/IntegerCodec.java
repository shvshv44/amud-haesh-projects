package shaq.pojobit.codecs;

import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;
import shaq.pojobit.bits.BitSizeOf;
import shaq.pojobit.bits.ByteSizeOf;

import java.nio.ByteBuffer;


public class IntegerCodec implements Codec<Integer> {

    public void serialize(Integer object, BitBuffer destinationBuffer, Bound boundAnnot) {
        int numberOfBits = boundAnnot.size();
        if(numberOfBits > BitSizeOf.INT.value() || numberOfBits == 0) {
            numberOfBits = BitSizeOf.INT.value();
        }

        byte [] bytes = ByteBuffer.allocate(ByteSizeOf.INT.value()).putInt(object).array();
        destinationBuffer.add(bytes,numberOfBits);
    }

    public Integer deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return null;
    }
}

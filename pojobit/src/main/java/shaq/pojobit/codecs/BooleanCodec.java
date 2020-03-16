package shaq.pojobit.codecs;

import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.Bit;
import shaq.pojobit.bits.BitBuffer;

public class BooleanCodec implements Codec <Boolean> {

    public void serialize(Boolean object, BitBuffer destinationBuffer, Bound boundAnnot) {
        destinationBuffer.add(new Bit(object));
    }

    public Boolean deserialize(BitBuffer bitBuffer,int currentBufferIndex, Bound boundAnnot) {
        Bit currentBit = bitBuffer.get(currentBufferIndex);
        return currentBit.value();
    }

}

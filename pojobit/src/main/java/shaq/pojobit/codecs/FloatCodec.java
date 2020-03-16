package shaq.pojobit.codecs;


import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;
import shaq.pojobit.bits.BitSizeOf;
import shaq.pojobit.bits.ByteSizeOf;

import java.nio.ByteBuffer;

public class FloatCodec implements Codec<Float> {

    public void serialize(Float object, BitBuffer destinationBuffer, Bound boundAnnot) {
        byte [] bytes = ByteBuffer.allocate(ByteSizeOf.FLOAT.value()).putFloat(object).array();
        destinationBuffer.add(bytes, BitSizeOf.FLOAT.value());
    }

    public Float deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return null;
    }
}

package shaq.pojobit.codecs;

import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;
import shaq.pojobit.bits.BitSizeOf;
import shaq.pojobit.bits.ByteSizeOf;

import java.nio.ByteBuffer;

public class DoubleCodec implements Codec<Double> {

    public void serialize(Double object, BitBuffer destinationBuffer, Bound boundAnnot) {
        byte [] bytes = ByteBuffer.allocate(ByteSizeOf.DOUBLE.value()).putDouble(object).array();
        destinationBuffer.add(bytes, BitSizeOf.DOUBLE.value());
    }

    public Double deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return null;
    }
}

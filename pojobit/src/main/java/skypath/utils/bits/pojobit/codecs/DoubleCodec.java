package skypath.utils.bits.pojobit.codecs;

import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.bits.BitSizeOf;
import skypath.utils.bits.pojobit.bits.ByteSizeOf;

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

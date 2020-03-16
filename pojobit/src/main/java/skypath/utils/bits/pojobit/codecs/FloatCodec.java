package skypath.utils.bits.pojobit.codecs;


import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.bits.BitSizeOf;
import skypath.utils.bits.pojobit.bits.ByteSizeOf;

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

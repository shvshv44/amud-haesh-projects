package skypath.utils.bits.pojobit.codecs;

import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.bits.BitSizeOf;
import skypath.utils.bits.pojobit.bits.ByteSizeOf;

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

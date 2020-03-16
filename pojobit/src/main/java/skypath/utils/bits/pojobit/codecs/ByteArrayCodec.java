package skypath.utils.bits.pojobit.codecs;

import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.bits.BitSizeOf;

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

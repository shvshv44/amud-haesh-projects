package skypath.utils.bits.pojobit.codecs;

import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.bits.BitSizeOf;
import skypath.utils.bits.pojobit.bits.ByteSizeOf;

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

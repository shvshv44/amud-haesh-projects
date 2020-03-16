package skypath.utils.bits.pojobit.codecs;

import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.bits.BitSizeOf;
import skypath.utils.bits.pojobit.bits.ByteSizeOf;

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

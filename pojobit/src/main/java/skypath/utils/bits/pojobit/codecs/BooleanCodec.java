package skypath.utils.bits.pojobit.codecs;

import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.Bit;
import skypath.utils.bits.pojobit.bits.BitBuffer;

public class BooleanCodec implements Codec <Boolean> {

    public void serialize(Boolean object, BitBuffer destinationBuffer, Bound boundAnnot) {
        destinationBuffer.add(new Bit(object));
    }

    public Boolean deserialize(BitBuffer bitBuffer,int currentBufferIndex, Bound boundAnnot) {
        Bit currentBit = bitBuffer.get(currentBufferIndex);
        return currentBit.value();
    }

}

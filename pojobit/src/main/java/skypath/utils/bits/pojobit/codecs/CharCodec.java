package skypath.utils.bits.pojobit.codecs;


import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.bits.BitSizeOf;
import skypath.utils.bits.pojobit.bits.ByteSizeOf;

import java.nio.ByteBuffer;

public class CharCodec implements Codec<Character>{

    public void serialize(Character object, BitBuffer destinationBuffer, Bound boundAnnot) {
        byte [] bytes = ByteBuffer.allocate(ByteSizeOf.CHAR.value()).putChar(object).array();
        destinationBuffer.add(bytes, BitSizeOf.CHAR.value());
    }

    public Character deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot) {
        return null;
    }
}

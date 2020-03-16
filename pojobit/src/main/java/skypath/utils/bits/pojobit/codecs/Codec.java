package skypath.utils.bits.pojobit.codecs;


import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;

public interface Codec <T> {

    public void serialize(T object, BitBuffer destinationBuffer, Bound boundAnnot);
    public T deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot);

}

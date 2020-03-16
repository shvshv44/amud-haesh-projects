package shaq.pojobit.codecs;


import shaq.pojobit.annotations.Bound;
import shaq.pojobit.bits.BitBuffer;

public interface Codec <T> {

    public void serialize(T object, BitBuffer destinationBuffer, Bound boundAnnot);
    public T deserialize(BitBuffer bitBuffer, int currentBufferIndex, Bound boundAnnot);

}

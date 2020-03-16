package shaq.pojobit.bits;

import java.util.ArrayList;
import java.util.Collection;

public class BitBuffer {

    private ArrayList<Bit> bits;

    public BitBuffer() {
        this.bits = new ArrayList<Bit>();
    }

    public Bit get (int index) {
        return bits.get(index);
    }

    public int size () {
        return bits.size();
    }

    public void add (Bit b) {
        bits.add(b);
    }

    public void add (Collection<Bit> bitsToAdd) {
        bits.addAll(bitsToAdd);
    }

    public void add (BitBuffer bitsToAdd) {
        for (int index = 0; index < bitsToAdd.size(); index++) {
            this.bits.add(bitsToAdd.get(index));
        }
    }

    public byte [] toByteArray() {
        byte [] bytes = new byte[sizeAsBytes()];

        for(int index = 0; index < bytes.length; index++) {
            byte byteToOperate = 0;
            for (int bitIndex = index * 8, indexInByte = 0; (bitIndex < bits.size()) && (bitIndex < (index+1)*8); bitIndex++, indexInByte++) {
                Bit currentBit = bits.get(bitIndex);
                byte bitValue = currentBit.asByte();
                byte shiftSize = (byte)(7-indexInByte);
                byte mask = (byte)(bitValue << shiftSize);
                byteToOperate = (byte)(byteToOperate | mask);
            }
            bytes[index] = byteToOperate;
        }

        return bytes;
    }

    public void add (byte[] bytes, int size) {
        int maxBytesSize = bytes.length * BitSizeOf.BYTE.value();
        if(size > maxBytesSize) {
            size = maxBytesSize;
        }

        int bitIndex = (size - 1)%BitSizeOf.BYTE.value();
        for (int byteIndex = bytes.length - 1 - ((size - 1)/BitSizeOf.BYTE.value()); byteIndex < bytes.length; byteIndex++) {
            while (bitIndex >= 0) {
                byte mask = (byte)(1 << bitIndex);
                int activeBit = (bytes[byteIndex] & mask);
                bits.add(new Bit(activeBit != 0));
                bitIndex--;
            }
            bitIndex = BitSizeOf.BYTE.value() - 1;
        }
    }

    private int sizeAsBytes() {
        return (int) Math.ceil(bits.size() / 8d);
    }

    public void padDefaultBits(int sizeOfPadding) {
        while (sizeOfPadding > 0) {
            bits.add(new Bit());
            sizeOfPadding--;
        }
    }

    @Override
    public String toString() {
        String bitListAsString = "";
        for(int index = 0; index < bits.size(); index++) {
            Bit bit = bits.get(index);
            bitListAsString += (bit.value())? "1":"0";
            if(((index + 1) % BitSizeOf.BYTE.value()) == 0) {
                bitListAsString += " ";
            }
        }
        bitListAsString += "";
        return bitListAsString;
    }
}

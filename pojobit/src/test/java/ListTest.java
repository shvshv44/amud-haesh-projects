import org.junit.Assert;
import org.junit.Test;
import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.encoding.Encoder;
import skypath.utils.bits.pojobit.utils.CodecsFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {


    private class BytePojo {

        @Bound
        public boolean bit1;
        @Bound public boolean bit2;
        @Bound public boolean bit3;
        @Bound public boolean bit4;
        @Bound public boolean bit5;
        @Bound public boolean bit6;
        @Bound public boolean bit7;
        @Bound public boolean bit8;

    }

    private class ByteListPojo {

        private List<BytePojo> bytePojos;

        public List<BytePojo> getBytePojos() {
            return bytePojos;
        }

        public void setBytePojos(List<BytePojo> bytePojos) {
            this.bytePojos = bytePojos;
        }
    }


    @Test
    public void ListTest() {

        byte [] expected = {9, 4};

        BytePojo byte1 = new BytePojo();
        byte1.bit1 = false;
        byte1.bit2 = false;
        byte1.bit3 = false;
        byte1.bit4 = false;
        byte1.bit5 = true;
        byte1.bit6 = false;
        byte1.bit7 = false;
        byte1.bit8 = true;

        BytePojo byte2 = new BytePojo();
        byte2.bit1 = false;
        byte2.bit2 = false;
        byte2.bit3 = false;
        byte2.bit4 = false;
        byte2.bit5 = false;
        byte2.bit6 = true;
        byte2.bit7 = false;
        byte2.bit8 = false;

        ByteListPojo listPojo = new ByteListPojo();
        listPojo.setBytePojos(new ArrayList<>());
        listPojo.getBytePojos().add(byte1);
        listPojo.getBytePojos().add(byte2);

        Encoder encoder = new Encoder(new CodecsFactory());
        byte [] actual = encoder.serialize(listPojo);
        Assert.assertEquals(Arrays.equals(expected,actual),true);
    }


}

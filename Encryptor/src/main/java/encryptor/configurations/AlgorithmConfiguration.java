package encryptor.configurations;

import encryptor.algorithms.EncryptionAlgorithm;
import encryptor.algorithms.shift.ShiftMultiplyEncryption;
import encryptor.algorithms.shift.ShiftUpEncryption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AlgorithmConfiguration {

    @Primary
    @Bean("shiftUp")
    public EncryptionAlgorithm createShiftUpAlgorithm() {
        return new ShiftUpEncryption();
    }

    @Bean("shiftMultiply")
    public EncryptionAlgorithm createShiftMultiplyAlgorithm() {
        return new ShiftMultiplyEncryption();
    }


}

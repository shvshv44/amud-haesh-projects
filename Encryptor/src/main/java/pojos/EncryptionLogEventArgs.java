package pojos;

import algorithms.EncryptionAlgorithm;

public class EncryptionLogEventArgs {
    private String originalFileName;
    private String outputFileName;
    private EncryptionAlgorithm algorithmType;
    private long operationLengthInMilliseconds;

    public EncryptionLogEventArgs(String originalFileName, String outputFileName, EncryptionAlgorithm algorithmType, long operationLengthInMilliseconds) {
        this.originalFileName = originalFileName;
        this.outputFileName = outputFileName;
        this.algorithmType = algorithmType;
        this.operationLengthInMilliseconds = operationLengthInMilliseconds;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public EncryptionAlgorithm getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(EncryptionAlgorithm algorithmType) {
        this.algorithmType = algorithmType;
    }

    public long getOperationLengthInMilliseconds() {
        return operationLengthInMilliseconds;
    }

    public void setOperationLengthInMilliseconds(long operationLengthInMilliseconds) {
        this.operationLengthInMilliseconds = operationLengthInMilliseconds;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EncryptionLogEventArgs)) {
            return false;
        }

        EncryptionLogEventArgs args = (EncryptionLogEventArgs) obj;
        return originalFileName == args.originalFileName &&
                outputFileName == args.outputFileName &&
                algorithmType == args.algorithmType &&
                operationLengthInMilliseconds == args.operationLengthInMilliseconds;
    }

    @Override
    public int hashCode() {
        return originalFileName.hashCode() + outputFileName.hashCode() + algorithmType.hashCode() + (int) operationLengthInMilliseconds;
    }
}

package pojos;

import algorithms.EncryptionAlgorithm;

public class EncryptionLogEventArgs {
    private String sourceFileName;
    private String destinationFileName;
    private EncryptionAlgorithm algorithm;
    private long startTime;
    private long endTime;

    public EncryptionLogEventArgs(String sourceFileName, String destinationFileName, EncryptionAlgorithm algorithm, long startTime, long endTime) {
        this.sourceFileName = sourceFileName;
        this.destinationFileName = destinationFileName;
        this.algorithm = algorithm;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getDestinationFileName() {
        return destinationFileName;
    }

    public void setDestinationFileName(String destinationFileName) {
        this.destinationFileName = destinationFileName;
    }

    public EncryptionAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(EncryptionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EncryptionLogEventArgs)) {
            return false;
        }

        EncryptionLogEventArgs args = (EncryptionLogEventArgs) obj;
        return sourceFileName == args.sourceFileName &&
                destinationFileName == args.destinationFileName &&
                algorithm == args.algorithm &&
                startTime == args.startTime &&
                endTime == args.endTime;
    }

    @Override
    public int hashCode() {
        return sourceFileName.hashCode() + destinationFileName.hashCode() + algorithm.hashCode() + (int)startTime + (int)endTime;
    }
}

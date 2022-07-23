package internals;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

public class TwitterSnowflakeUUID {

    /**
     * Distributed Sequence Generator.
     * Inspired by Twitter snowflake: https://github.com/twitter/snowflake/tree/snowflake-2010
     * <p>
     * This class should be used as a Singleton.
     * Make sure that you create and reuse a Single instance of SequenceGenerator per node in your distributed system cluster.
     */
    private static final int UNUSED_BITS = 1; // Sign bit, Unused (always set to 0)
    private static final int EPOCH_BITS = 41;
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;

    private static final int maxNodeId = (int) (Math.pow(2, NODE_ID_BITS) - 1);
    private static final int maxSequence = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    // Custom Epoch (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
    private static final long CUSTOM_EPOCH = 1420070400000L;

    private final int nodeId;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    // Create SequenceGenerator with a nodeId
    public TwitterSnowflakeUUID(int nodeId) {
        if (nodeId < 0 || nodeId > maxNodeId) {
            throw new IllegalArgumentException(String.format("NodeId must be between %d and %d", 0, maxNodeId));
        }
        this.nodeId = nodeId;
    }

    // Let SequenceGenerator generate a nodeId
    public TwitterSnowflakeUUID() {
        this.nodeId = createNodeId();
    }

    public synchronized long nextId() {
        long currentTimestamp = timestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                // Sequence Exhausted, wait till next millisecond.
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            // reset sequence to start with zero for the next millisecond
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        //Now, the first 41 bits of the ID (after the signed bit) will be filled with the epoch timestamp. Let’s do that using a left-shift -
        long id = currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS);

        //Next, we take the configured node ID and fill the next 10 bits with the node ID. Let’s say that the nodeId is 786 -
        //id |= nodeId << 12
        id |= (nodeId << SEQUENCE_BITS);

        //Finally, we fill the last 12 bits with the local counter.
        // Considering the counter’s next value is 3450, i.e. sequence = 3450, the final ID is obtained like so -
        id |= sequence;
        return id;
    }


    // Get current timestamp in milliseconds, adjust for the custom epoch.
    private static long timestamp() {
        /**
         * Let’s now understand how it works. Let’s say it’s June 9, 2018 10:00:00 AM GMT. The epoch timestamp for this particular time is 1528538400000.
         * First, we adjust our timestamp with respect to the custom epoch-
         * currentTimestamp = 1528538400000 - 1420070400000
         */
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    // Block and wait till next millisecond
    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }

    private int createNodeId() {
        int nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X", mac[i]));
                    }
                }
            }
            nodeId = sb.toString().hashCode();
        } catch (Exception ex) {
            nodeId = (new SecureRandom().nextInt());
        }
        nodeId = nodeId & maxNodeId;
        return nodeId;
    }
}

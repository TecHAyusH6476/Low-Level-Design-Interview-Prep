public class LeakyBucketRateLimmiter {
    private long currentWater = 0;
    private final long LeakRate = 2;
    private final long BucketCapacity = 5;
    private long lastLeakTimestamp = System.nanoTime();

    public synchronized boolean allowRequest() {
        leakWater();

        if (currentWater < BucketCapacity) {
            currentWater++;
            return true;
        }

        return false;
    }

    private void leakWater() {
        long now = System.nanoTime();
        long timeElapsed = (now - lastLeakTimestamp);
        long leakedWater = (timeElapsed * LeakRate) / 1000000000;
        
        if (leakedWater > 0) {
            currentWater = Math.max(0, currentWater - leakedWater);
            lastLeakTimestamp = now;
        }
    }

    public synchronized long getCurrentWater() {
        leakWater();
        return currentWater;
    }

    public static void main(String[] args) {
        LeakyBucketRateLimmiter rateLimmiter = new LeakyBucketRateLimmiter();
        for(int i = 0; i < 7; i++) {
            boolean isAllow = rateLimmiter.allowRequest();
            System.out.println("Request " + (i + 1) + " allowed: " + isAllow);
        }
    }
}

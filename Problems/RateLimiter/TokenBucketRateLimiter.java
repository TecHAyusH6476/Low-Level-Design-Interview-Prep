


public class TokenBucketRateLimiter{
    private long CurrTokenCount=2;
    private final long RefillRate=2;
    private final long TokenCapacity=5;
    private long lastRefillTimestamp=System.nanoTime();

    public synchronized boolean allowRequest() {
        refillTokens();

        if (CurrTokenCount > 0) {
            CurrTokenCount--;
            return true;
        }

        return false;
    }
    public void refillTokens(){
        long now=System.nanoTime();

        long timeElapsed=(now-lastRefillTimestamp);
        long refillTokenCount=(timeElapsed*RefillRate)/1000000000;
        
        CurrTokenCount=Math.min(refillTokenCount+CurrTokenCount,TokenCapacity);
        lastRefillTimestamp=now;
    }
    public synchronized long getCurrentTokens() {
        refillTokens();
        return CurrTokenCount;
    }

    public static void main(String[] args) {
        TokenBucketRateLimiter rateLimmiter=new TokenBucketRateLimiter();
         for(int i=0;i<5;i++){
            boolean isAllow=rateLimmiter.allowRequest();

            System.out.println(isAllow);
         }
    }
}

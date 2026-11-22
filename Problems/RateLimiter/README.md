# RateLimiting

# 1. 🪣 Token Bucket Rate Limiter (Java)

This project implements a **Token Bucket algorithm** in Java for **rate limiting** requests based on time and resource usage.

---

## 📌 What is Token Bucket?

The **Token Bucket algorithm** is a technique used to control the rate at which actions (e.g., API calls, jobs) are allowed to proceed. It uses a virtual bucket filled with "tokens":

- Each request **consumes tokens**.
- Tokens are **refilled over time** at a fixed rate.
- If not enough tokens are available, the request is **denied**.

---

## 📦 Features

- Thread-safe (uses `synchronized`)
- Simple time-based refill logic using `Instant`
- Flexible capacity and refill rate configuration

---

## 🧠 How It Works

```java
public class TokenBucket {
    private final long capacity;               // Max tokens the bucket can hold
    private final double fillRate;             // Refill rate (tokens per second)
    private double tokens;                     // Current available tokens
    private Instant lastRefillTimestamp;       // Last refill time

    public TokenBucket(long capacity, double fillRate) {
        this.capacity = capacity;
        this.fillRate = fillRate;
        this.tokens = capacity;                // Start with a full bucket
        this.lastRefillTimestamp = Instant.now();
    }

    public synchronized boolean allowRequest(int tokens) {
        refill();                              // Refill based on time passed

        if (this.tokens < tokens) {
            return false;                      // Not enough tokens, deny
        }

        this.tokens -= tokens;                 // Consume tokens
        return true;                           // Allow the request
    }

    private void refill() {
        Instant now = Instant.now();
        long elapsed = now.toEpochMilli() - lastRefillTimestamp.toEpochMilli();

        double tokensToAdd = (elapsed * fillRate) / 1000.0;
        this.tokens = Math.min(capacity, this.tokens + tokensToAdd);

        this.lastRefillTimestamp = now;
    }
}

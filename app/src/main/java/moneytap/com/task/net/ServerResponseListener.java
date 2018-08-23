package moneytap.com.task.net;

public interface ServerResponseListener<T> {
    T onResponse(boolean isSuccess);
}

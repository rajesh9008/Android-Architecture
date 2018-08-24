package moneytap.com.task.model;

public class SearchRequest {
    private String action;
    private String searchterm;
    private String requestType;

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getAction() {

        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

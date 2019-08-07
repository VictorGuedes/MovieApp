package com.example.android.movieapp.model.service;

public class NetworkState {

    private final Status status;
    private final String msg;

    static final NetworkState LOADED;
    static final NetworkState LOADING;
    static final NetworkState MAXPAGE;

    public NetworkState(Status status, String msg){
        this.status = status;
        this.msg = msg;
    }

    static {
        LOADED=new NetworkState(Status.SUCCESS,"Success");
        LOADING=new NetworkState(Status.RUNNING,"Running");
        MAXPAGE=new NetworkState(Status.MAX,"No More page");
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}

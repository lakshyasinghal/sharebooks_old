package com.sharebooks.login.http;





public interface RequestMaker {

	public abstract String sendRequest();

	public abstract String buildParamsString(List<ObjectPair> params);

	public abstract Response getResponse();
}
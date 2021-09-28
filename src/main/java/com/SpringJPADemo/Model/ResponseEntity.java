package com.SpringJPADemo.Model;

public class ResponseEntity<T> {
	
	int status;
	String message;
	T data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "ResponseEntity [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}

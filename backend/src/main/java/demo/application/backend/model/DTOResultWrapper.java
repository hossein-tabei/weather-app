package demo.application.backend.model;

public class DTOResultWrapper<T> {

	private int code;
	private String message;
	private T info;
	
	public DTOResultWrapper() {}
	
	public DTOResultWrapper(int code, String message, T info) {
		super();
		this.code = code;
		this.message = message;
		this.info = info;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "DTOWrapper [code=" + code + ", message=" + message + ", info=" + info + "]";
	}
	
}

package demo.application.backend.model;

public class DTOResultWrapper<T> {

	private String message;
	private T info;
	
	public DTOResultWrapper() {}
	
	public DTOResultWrapper(String message, T info) {
		super();
		this.message = message;
		this.info = info;
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
		return "DTOResultWrapper [message=" + message + ", info=" + info + "]";
	}

}

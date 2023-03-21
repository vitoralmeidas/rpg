package br.com.tills.dungeonsdragons.exceptions;

public class InputLessOrEqualThanZeroException extends RuntimeException {

	public InputLessOrEqualThanZeroException() {
		super();
	}
	
	public InputLessOrEqualThanZeroException(String s) {
		super(s);
	}
}

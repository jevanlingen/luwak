package io.github.jevanlingen.luwak.function.checked;

import java.util.Objects;

import io.github.jevanlingen.luwak.function.₵;

/**
 * A {@link ₵} that allows for checked exceptions.
 */
@FunctionalInterface
public interface _₵<T> {
	/**
	 * Performs this operation on the given argument.
	 *
	 * @param t the input argument
	 */
	void accept(T t) throws Exception;

	/**
	 * Returns a composed {@code Consumer} that performs, in sequence, this
	 * operation followed by the {@code after} operation. If performing either
	 * operation throws an exception, it is relayed to the caller of the
	 * composed operation.  If performing this operation throws an exception,
	 * the {@code after} operation will not be performed.
	 *
	 * @param after the operation to perform after this operation
	 * @return a composed {@code Consumer} that performs in sequence this operation followed by the {@code after} operation
	 * @throws NullPointerException if {@code after} is null
	 */
	default _₵<T> andThen(_₵<? super T> after) {
		Objects.requireNonNull(after);
		return (T t) -> { accept(t); after.accept(t); };
	}
}
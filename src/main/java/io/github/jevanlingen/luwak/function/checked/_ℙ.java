package io.github.jevanlingen.luwak.function.checked;

import java.util.Objects;
import java.util.function.Predicate;

import io.github.jevanlingen.luwak.function.ℙ;

/**
 * A {@link ℙ} that allows for checked exceptions.
 */
@FunctionalInterface
public interface _ℙ<T> {
	/**
	 * Evaluates this predicate on the given argument.
	 *
	 * @param t the input argument
	 * @return {@code true} if the input argument matches the predicate,
	 * otherwise {@code false}
	 */
	boolean test(T t) throws Exception;

	/**
	 * Returns a composed predicate that represents a short-circuiting logical
	 * OR of this predicate and another.  When evaluating the composed
	 * predicate, if this predicate is {@code true}, then the {@code other}
	 * predicate is not evaluated.
	 *
	 * <p>Any exceptions thrown during evaluation of either predicate are relayed
	 * to the caller; if evaluation of this predicate throws an exception, the
	 * {@code other} predicate will not be evaluated.
	 *
	 * @param other a predicate that will be logically-ORed with this predicate
	 * @return a composed predicate that represents the short-circuiting logical
	 * OR of this predicate and the {@code other} predicate
	 * @throws NullPointerException if other is null
	 */
	default _ℙ<T> and(_ℙ<? super T> other) {
		Objects.requireNonNull(other);
		return (t) -> test(t) && other.test(t);
	}

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @return a predicate that represents the logical negation of this predicate
	 */
	default _ℙ<T> negate() {
		return (t) -> !test(t);
	}

	/**
	 * Returns a composed predicate that represents a short-circuiting logical
	 * OR of this predicate and another.  When evaluating the composed
	 * predicate, if this predicate is {@code true}, then the {@code other}
	 * predicate is not evaluated.
	 *
	 * <p>Any exceptions thrown during evaluation of either predicate are relayed
	 * to the caller; if evaluation of this predicate throws an exception, the
	 * {@code other} predicate will not be evaluated.
	 *
	 * @param other a predicate that will be logically-ORed with this predicate
	 * @return a composed predicate that represents the short-circuiting logical
	 * OR of this predicate and the {@code other} predicate
	 * @throws NullPointerException if other is null
	 */
	default _ℙ<T> or(_ℙ<? super T> other) {
		Objects.requireNonNull(other);
		return (t) -> test(t) || other.test(t);
	}

	/**
	 * Returns the {@code ℙ} alias of a non-{@code null} predicate.
	 *
	 * @param predicate the predicate
	 * @param <T>       the type of arguments to the specified predicate
	 * @return a {@code predicate} alias
	 * @throws NullPointerException if predicate is {@code null}
	 */
	static <T> _ℙ<T> of(Predicate<T> predicate) {
		Objects.requireNonNull(predicate);
		return predicate::test;
	}

	/**
	 * Returns a predicate that tests if two arguments are equal according to {@link Objects#equals(Object, Object)}.
	 *
	 * @param <T>       the type of arguments to the predicate
	 * @param targetRef the object reference with which to compare for equality, which may be {@code null}
	 * @return a predicate that tests if two arguments are equal according to {@link Objects#equals(Object, Object)}
	 */
	static <T> _ℙ<T> isEqual(Object targetRef) {
		return (null == targetRef) ? Objects::isNull : targetRef::equals;
	}

	/**
	 * Returns a predicate that is the negation of the supplied predicate.
	 *
	 * @param <T>    the type of arguments to the specified predicate
	 * @param target predicate to negate
	 * @return a predicate that negates the results of the supplied predicate
	 * @throws NullPointerException if target is null
	 */
	@SuppressWarnings("unchecked")
	static <T> _ℙ<T> not(_ℙ<? super T> target) {
		Objects.requireNonNull(target);
		return (_ℙ<T>) target.negate();
	}
}

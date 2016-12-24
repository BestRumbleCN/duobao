/*
 * Copyright (C) 智炎发展. All rights reserved.
 * @(#) Assembler.java 2016-08-10 15:42
 */

package team.wuxie.crowdfunding.model.base;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Assembler<T, R> {

    /**
     * Applies this assembler to the given argument.
     *
     * @param t the assembler argument
     * @return the assembler result
     */
    R transform(T t);

    /**
     * Returns a composed assembler that first applies the {@code before}
     * assembler to its input, and then applies this assembler to the result.
     * If evaluation of either assembler throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of input to the {@code before} assembler, and to the
     *           composed assembler
     * @param before the assembler to apply before this assembler is applied
     * @return a composed assembler that first applies the {@code before}
     * function and then applies this assembler
     * @throws NullPointerException if before is null
     *
     * @see #andThen(Assembler)
     */
    default <V> Assembler<V, R> compose(Assembler<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> transform(before.transform(v));
    }

    /**
     * Returns a composed assembler that first applies this assembler to
     * its input, and then applies the {@code after} assembler to the result.
     * If evaluation of either assembler throws an exception, it is relayed to
     * the caller of the composed assembler.
     *
     * @param <V> the type of output of the {@code after} assembler, and of the
     *           composed function
     * @param after the assembler to apply after this assembler is applied
     * @return a composed assembler that first applies this assembler and then
     * applies the {@code after} assembler
     * @throws NullPointerException if after is null
     *
     * @see #compose(Assembler)
     */
    default <V> Assembler<T, V> andThen(Assembler<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.transform(transform(t));
    }

    /**
     * Transform the function interface.
     */
    default Function<T, R> toFunction() {
        return this::transform;
    }

    /**
     * Returns a assembler that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the assembler
     * @return a assembler that always returns its input argument
     */
    static <T> Assembler<T, T> identity() {
        return t -> t;
    }

    /**
     * Applies the assembler to list.
     *
     * @param tList the assembler argument list.
     * @param assembler the assembler of the element.
     * @param <T> the type of the input and output objects to the assembler.
     * @param <R> the type of the result of the assembler.
     * @return the assembler result.
     */
    @Nonnull
    static <T, R> List<R> transformList(List<T> tList, Assembler<T, R> assembler) {
        if (tList == null || tList.isEmpty()) {
            return Collections.emptyList();
        }
        return tList.stream().filter(Objects::nonNull)
                .map(assembler.toFunction())
                .collect(Collectors.toList());
    }
}

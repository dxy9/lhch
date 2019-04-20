package com.lhch.book.java8inaction.chap6;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/* TODO 太抽象了,无法理解这个接口
可变缩减操作，将输入元素累积到可变结果容器中，可选地在处理完所有输入元素后将累积结果转换为最终表示。还原操作可以顺序执行或并行执行。

    可变缩减操作的示例包括：
        将元素累积到集合中;
        使用StringBuilder连接字符串;
        计算有关元素的摘要信息，例如总和，最小值，最大值或平均值;
        计算“数据透视表”摘要，例如“卖方的最大价值交易”等。
        类收集器提供许多常见的可变减少的实现。

收集器由四个函数指定，这四个函数一起工作以将条目累积到可变结果容器中，并可选地对结果执行最终转换。他们是：
    创建新的结果容器（supplier（））
    将新数据元素合并到结果容器中（accumulator（））
    将两个结果容器合并为一个（合并器（））
    在容器上执行可选的最终转换（finisher（））

收集器还具有一组特征，例如Collector.Characteristics.CONCURRENT，它们提供可由缩减实现使用以提供更好性能的提示。

使用收集器顺序实现缩减将使用supplier函数创建单个结果容器，并为每个输入元素调用累加器函数一次。
并行实现将对输入进行分区，为每个分区创建结果容器，将每个分区的内容累积到该分区的子结果中，然后使用组合器函数将子结果合并为组合结果。

为确保顺序和并行执行产生相同的结果，收集器函数必须满足标识和关联约束。

    标识约束表示对于任何部分累积的结果，将其与空结果容器组合必须产生等效结果。
    也就是说，对于部分累积的结果，这是任何一系列累加器和组合器调用的结果，a必须等于combiner.apply（a，supplier.get（））。

    关联约束表示拆分计算必须产生等效结果。也就是说，对于任何输入元素t1和t2，下面计算中的结果r1和r2必须是等价的：

     A a1 = supplier.get();
     accumulator.accept(a1, t1);
     accumulator.accept(a1, t2);
     R r1 = finisher.apply(a1);  // result without splitting

     A a2 = supplier.get();
     accumulator.accept(a2, t1);
     A a3 = supplier.get();
     accumulator.accept(a3, t2);
     R r2 = finisher.apply(combiner.apply(a2, a3));  // result with splitting

对于没有UNORDERED特征的收集器，如果finisher.apply（a1）.equals（finisher.apply（a2）），则两个累积结果a1和a2是等效的。
对于无序收集器，放宽等价以允许与顺序差异相关的不相等。
（例如，如果元素包含相同的元素，忽略顺序，那么将元素累积到List的无序收集器会考虑两个等效的列表。）

基于收集器实现缩减的库（如Stream.collect（收集器））必须遵循以下约束：
    传递给累加器函数的第一个参数，两个参数传递给组合器函数，传递给整理器函数的参数必须是先前调用结果供应商，累加器或组合器函数的结果。
    除了将它们再次传递给累加器，组合器或修整器功能，或者将它们返回到缩减操作的调用者之外，该实现不应该对任何结果提供者，累加器或组合器函数的结果做任何事情。
如果将结果传递给组合器或修整器功能，并且不从该功能返回相同的对象，则不再使用它。
一旦结果传递给组合器或修整器功能，它就不会再次传递给累加器功能。
对于非并发收集器，从结果提供者，累加器或组合器函数返回的任何结果必须是串行线程限制的。这使得集合可以并行发生，而收集器无需实现任何其他同步。减少实现必须管理输入被正确分区，分区是单独处理的，并且只有在累积完成后才进行组合。
对于并发收集器，实现可以（但不是必须）同时实现还原。并发减少是使用相同的可同时修改的结果容器从多个线程同时调用累加器函数，而不是在累积期间保持结果隔离的情况。仅当收集器具有Collector.Characteristics.UNORDERED特征或原始数据无序时，才应应用并发减少。
除了收集器中的预定义实现之外，（Supplier，BiConsumer，BinaryOperator，Characteristics ...）的静态工厂方法可用于构建收集器。例如，您可以创建一个收集器，将窗口小部件累积到TreeSet中：

     Collector<Widget, ?, TreeSet<Widget>> intoSet =
         Collector.of(TreeSet::new, TreeSet::add,
                      (left, right) -> { left.addAll(right); return left; });

（此行为也由预定义的收集器Collectors.toCollection（供应商）实现。
apiNote：
使用收集器执行缩减操作应该产生相当于以下内容的结果：

     R container = collector.supplier().get();
     for (T t : data)
         collector.accumulator().accept(container, t);
     return collector.finisher().apply(container);

但是，库可以自由分区输入，对分区执行缩减，然后使用组合器功能组合部分结果以实现并行缩减。 （根据具体的减少操作，这可能会更好或更差，具体取决于累加器和组合器功能的相对成本。）
收藏家的目的是组成;收集器中的许多方法都是采用收集器并生成新收集器的函数。例如，给定以下收集器来计算员工流的工资总和：

     Collector<Employee, ?, Integer> summingSalaries
         = Collectors.summingInt(Employee::getSalary))

如果我们想创建一个收集器来按部门列出工资总额，我们可以使用Collectors.groupingBy（Function，Collector）重用“工资总和”逻辑

     Collector<Employee, ?, Map<Department, Integer>> summingSalariesByDept
         = Collectors.groupingBy(Employee::getDepartment, summingSalaries);


Type parameters:
<T> – 减少操作的输入元素的类型
<A> – 还原操作的可变累积类型（通常隐藏为实现细节）
<R> – 还原操作的结果类型
 */
public interface Collector<T, A, R> {
    /**
     * Returns a new {@code Collector} described by the given {@code supplier},
     * {@code accumulator}, and {@code combiner} functions.  The resulting
     * {@code Collector} has the {@code Collector.Characteristics.IDENTITY_FINISH}
     * characteristic.
     *
     * @param supplier
     *         The supplier function for the new collector
     * @param accumulator
     *         The accumulator function for the new collector
     * @param combiner
     *         The combiner function for the new collector
     * @param characteristics
     *         The collector characteristics for the new
     *         collector
     * @param <T>
     *         The type of input elements for the new collector
     * @param <R>
     *         The type of intermediate accumulation result, and final result,
     *         for the new collector
     * @return the new {@code Collector}
     * @throws NullPointerException
     *         if any argument is null
     */
    static <T, R> Collector<T, R, R> of(Supplier<R> supplier,
                                        BiConsumer<R, T> accumulator,
                                        BinaryOperator<R> combiner,
                                        Characteristics... characteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(characteristics);
        Set<Characteristics> cs = (characteristics.length == 0)
                ? Collectors.CH_ID
                : Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH,
                characteristics));
        return new Collectors.CollectorImpl<>(supplier, accumulator, combiner, cs);
    }

    /**
     * Returns a new {@code Collector} described by the given {@code supplier},
     * {@code accumulator}, {@code combiner}, and {@code finisher} functions.
     *
     * @param supplier
     *         The supplier function for the new collector
     * @param accumulator
     *         The accumulator function for the new collector
     * @param combiner
     *         The combiner function for the new collector
     * @param finisher
     *         The finisher function for the new collector
     * @param characteristics
     *         The collector characteristics for the new
     *         collector
     * @param <T>
     *         The type of input elements for the new collector
     * @param <A>
     *         The intermediate accumulation type of the new collector
     * @param <R>
     *         The final result type of the new collector
     * @return the new {@code Collector}
     * @throws NullPointerException
     *         if any argument is null
     */
    static <T, A, R> Collector<T, A, R> of(Supplier<A> supplier,
                                           BiConsumer<A, T> accumulator,
                                           BinaryOperator<A> combiner,
                                           Function<A, R> finisher,
                                           Characteristics... characteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(finisher);
        Objects.requireNonNull(characteristics);
        Set<Characteristics> cs = Collectors.CH_NOID;
        if (characteristics.length > 0) {
            cs = EnumSet.noneOf(Characteristics.class);
            Collections.addAll(cs, characteristics);
            cs = Collections.unmodifiableSet(cs);
        }
        return new Collectors.CollectorImpl<>(supplier, accumulator, combiner, finisher, cs);
    }

    /**
     * 一个创建并返回新的可变结果容器的函数。
     *
     * @return a function which returns a new, mutable result container
     */
    Supplier<A> supplier();

    /**
     * A function that folds a value into a mutable result container.
     *
     * @return a function which folds a value into a mutable result container
     */
    BiConsumer<A, T> accumulator();

    /**
     * A function that accepts two partial results and merges them.  The
     * combiner function may fold state from one argument into the other and
     * return that, or may return a new result container.
     *
     * @return a function which combines two partial results into a combined
     * result
     */
    BinaryOperator<A> combiner();

    /**
     * Perform the final transformation from the intermediate accumulation type
     * {@code A} to the final result type {@code R}.
     *
     * <p>If the characteristic {@code IDENTITY_TRANSFORM} is
     * set, this function may be presumed to be an identity transform with an
     * unchecked cast from {@code A} to {@code R}.
     *
     * @return a function which transforms the intermediate result to the final
     * result
     */
    Function<A, R> finisher();

    /**
     * Returns a {@code Set} of {@code Collector.Characteristics} indicating
     * the characteristics of this Collector.  This set should be immutable.
     *
     * @return an immutable set of collector characteristics
     */
    Set<Characteristics> characteristics();

    /**
     * Characteristics indicating properties of a {@code Collector}, which can
     * be used to optimize reduction implementations.
     */
    enum Characteristics {
        /**
         * Indicates that this collector is <em>concurrent</em>, meaning that
         * the result container can support the accumulator function being
         * called concurrently with the same result container from multiple
         * threads.
         *
         * <p>If a {@code CONCURRENT} collector is not also {@code UNORDERED},
         * then it should only be evaluated concurrently if applied to an
         * unordered data source.
         */
        CONCURRENT,

        /**
         * Indicates that the collection operation does not commit to preserving
         * the encounter order of input elements.  (This might be true if the
         * result container has no intrinsic order, such as a {@link Set}.)
         */
        UNORDERED,

        /**
         * Indicates that the finisher function is the identity function and
         * can be elided.  If set, it must be the case that an unchecked cast
         * from A to R will succeed.
         */
        IDENTITY_FINISH
    }
}

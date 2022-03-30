Intro https://spring.io/blog/2018/09/17/introducing-spring-data-jdbc
Adapted example from [here](https://javabydeveloper.com/spring-data-jdbc-many-to-many-example/)
Many-To-many https://spring.io/blog/2018/09/24/spring-data-jdbc-references-and-aggregates


> As repository is a concept derived from Domain Driven Design, thinking about database tables is the wrong approach. By definition you access aggregate roots from a repository. Effectively a repository is simulating a collection of these.

[Stack Overflow](https://stackoverflow.com/questions/21265262/are-you-supposed-to-have-one-repository-per-table-in-jpa)

> Repositories persist and load aggregates. An aggregate is a cluster of objects that form a unit, which should always be consistent. Also, it should always get persisted (and loaded) together. It has a single object, called the aggregate root, which is the only thing allowed to touch or reference the internals of the aggregate. The aggregate root is what gets passed to the repository in order to persist the aggregate.

> This brings up the question: How does Spring Data JDBC determine what is part of the aggregate and what isn’t? The answer is very simple: Everything you can reach from an aggregate root by following non-transient references is part of the aggregate.

> relationship between Book and Author
> If multiple aggregates reference the same entity, that entity can’t be part of those aggregates referencing it since it only can be part of exactly one aggregate. Therefore any Many-to-One and Many-to-Many relationship must be modeled by just referencing the id.

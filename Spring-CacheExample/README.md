### @Cacheable、@CachePut、@CacheEvict 注释介绍
~~~
    @Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
    @CachePut  主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
    @CachEvict 主要针对方法配置，能够根据一定的条件对缓存进行清空.
        它有一个属性 beforeInvocation，缺省为 false，即缺省情况下，都是在实际的方法执行完成后，才对缓存进行清空操作。期间如果执行方法出现异常，则会导致缓存清空不被执行。
~~~

基于 proxy 的 spring aop 带来的内部调用问题，导致 spring cache 失效  
要避免这个问题，就是要避免对缓存方法的内部调用，或者避免使用基于 proxy 的 AOP 模式，可以使用基于 `aspectJ` 的 AOP 模式来解决这个问题。
非 public 方法问题  
和内部调用问题类似，非 public 方法如果想实现基于注释的缓存，必须采用基于 AspectJ 的 AOP 机制

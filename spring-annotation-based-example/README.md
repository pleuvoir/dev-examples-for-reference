

## 该项目演示了 spring 基础，分为以下章节


#### chapter01 基础注解
```
@Configuration 

@Bean

@Scope

@Lazy

@Conditional
```

#### chapter02 演示基于包扫描的注册，以及自定义的过滤规则
```
@ComponentScan 

includeFilters excludeFilters @Filter 
```
#### chapter03 演示 bean 的多种注册方式
```
@Bean

@Import ImportBeanDefinitionRegistrar  ImportSelector class 

FactoryBean
```
#### chapter04 bean 的生命周期，可对 bean 做增强处理
```
接口 BeanPostProcessor

接口 InitializingBean, DisposableBean

@Bean(initMethod = "init", destroyMethod = "destroy")

@PostConstruct

@PreDestroy
```
#### chapter05 演示 IOC bean 的注入，通过 set 方法，构造方法，以及属性
```
@Autowired(required = false)

@Primary

@Qualifier
```
#### chapter06 演示基于 aspectj 的 aop，五种切入时机

#### chapter07 演示配置文件读取的多种方式
```
@PropertySource 
@Value
EmbeddedValueResolverAware 使用
PropertiesFactoryBean  使用（可同时加载多个文件）
```	

#### chapter08 演示 event 事件机制

#### chapter09 演示动态注册 bean 的三种方式（相对于 chapter3 提供了更为复杂需求下的实现）

#### chapter10 演示 @order 的使用

目前只有使用包扫描时有效，其内部被 `@Bean` 修饰的内部类会按顺序初始化，而对他本身是无排序作用的

#### chapter11 演示 java 方式配置引入其他配置类时遇到的问题（待解决）

#### chapter12 演示 prepareRefresh 阶段如何增加必要属性验证

#### groovy 演示基于 xml 格式，更改 groovy 文件实现热刷新



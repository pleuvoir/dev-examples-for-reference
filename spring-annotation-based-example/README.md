

## 该项目演示了 spring 基础，分为以下章节


#### chapter1 基础注解
```
	@Configuration 
	
	@Bean
	
	@Scope
	
	@Lazy
	
	@Conditional
```

#### chapter2 演示基于包扫描的注册，以及自定义的过滤规则
```
	@ComponentScan 
	
	includeFilters excludeFilters @Filter 
```
#### chapter3 演示 bean 的多种注册方式
```
	@Bean
	
	@Import ImportBeanDefinitionRegistrar  ImportSelector class 
	
	FactoryBean
```
#### chapter4 bean 的生命周期，可对 bean 做增强处理
```
	接口 BeanPostProcessor
	
	接口 InitializingBean, DisposableBean
	
	@Bean(initMethod = "init", destroyMethod = "destroy")
	
	@PostConstruct
	
	@PreDestroy
```
#### chapter5 演示 IOC bean 的注入，通过 set 方法，构造方法，以及属性
```
	@Autowired(required = false)
	
	@Primary
	
	@Qualifier
```
#### chapter6 演示基于 aspectj 的 aop，五种切入时机

#### chapter7 演示配置文件读取的多种方式
```
	@PropertySource 
	
	@Value
```	
#### chapter8 演示 event 事件机制
	
	




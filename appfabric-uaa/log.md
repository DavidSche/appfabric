# Mylog #
[url](https://github.com/rozidan/logger-spring-boot)
## Log your application

Apply the Logger to your application with `@EnableLogger` annotation in a configuration class:

```java
@Configuration
@EnableLogger
public class LoggerConfig {

}
```

Simply add the `@Loggable` annotation to a method, or to a class scope:

```java
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
	
	@Loggable
	@GetMapping
	public List<EmployeeDto> listAllEmployees() {
	}
}
```
More examples:

Warning whenever execution is over 2 sec:
```java
@Loggable(warnOver = 2, warnUnit = TimeUnit.SECONDS)
```
This will result 2 lines of log, one where 2 sec are over, and the other when execution is complete:
```text
.....c.i.s.l.w.c.EmployeeController           : #listAllEmployees([]): in PT2.833S and still running (max PT0.002S)
.....c.i.s.l.w.c.EmployeeController           : #listAllEmployees([]): [] in PT6.345S (max PT0.002S)
```

Log when enter to a method:
```java
@Loggable(entered = true)
```

Skip printing arguments and results of a method:
```java
@Loggable(skipArgs = true, skipResult = true)
```

Log with different level (default is INFO):
```java
@Loggable(LogLevel.WARN)
```

Set a different logger name (default is class name):
```java
@Loggable(value = LogLevel.WARN, name = "my-logger-name")
```




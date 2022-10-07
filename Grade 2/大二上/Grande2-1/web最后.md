# Структура JSF приложения最后的准备

## Билет 1

### 1.Фаза вызова приложение (application invocation phase) и фаза формирования ответа (response render phase)

![image-20211225220321082](C:\Users\x1761\Desktop\web\p2\10-1.png)

![image-20211225220321082](C:\Users\x1761\Desktop\web\p2\10-2.png)

![image-20211225220321082](C:\Users\x1761\Desktop\web\p2\10-3.png)

![image-20211225220321082](C:\Users\x1761\Desktop\web\p2\10-4.png)

![image-20211225220321082](C:\Users\x1761\Desktop\web\p2\10-5.png)

![image-20211225220321082](C:\Users\x1761\Desktop\web\p2\10-6.png)

## 2.Виды задания конфигураций Spring

英语https://stackoverflow.com/questions/35807056/how-many-ways-are-there-to-configure-the-spring-framework-what-are-the-differen

俄语https://habr.com/ru/post/489236/

## 3.Написать исходный код CDI бина, реализующего паттерн «команда» (Command Pattern)

英语https://stackoverflow.com/questions/20152373/how-to-implement-command-pattern-via-cdi

什么是command

https://www.baeldung.com/java-command-pattern

# Билет 2

## 1.Фазы вызова приложение(Invoke Application Phase) и формирования ответа сервера(Render Response Phase)

[Билет 1](#Билет 1)

## 2.Способы задания конфигурации в Spring

https://docs.spring.io/spring-batch/docs/current/reference/html/job.html

## 3.Напишите шаблон Command с использованием CDI бинов

[Билет 1](#Билет 1)

# Билет 3

## 1.Фазы jsf: Invoke Application и Render Response

[Билет 1](#Билет 1)

## 2. Способы задания конфигурации Spring

[Билет 2](#Билет 2)

## 3.Напишите шаблон Command с использованием CDI бинов

[Билет 1](#Билет 1)

# Билет 4

## 2.Фаза вызова приложения и фаза формирования ответа сервера

[Билет 1](#Билет 1)

其他的同上



# Билет 5

## 1. IoC и CDI в Java EE

dependency injection)就是基于ioc(Inverse of control)原则

### Scopes of CDI

*“…I think we need a more specific name for this pattern. Inversion of Control is too generic a term, and thus people find it confusing. As a result with a lot of discussion with various IoC advocates we settled on the name Dependency Injection.” – Martin Fowler*

- **Application Scope:** A *@ApplicationScope* annotation ascertains that the instance be created only once during the lifetime of the application. The class is triggered to instantiate for the first time when the actual injection occurs and is available to share across the application. The instance gets destroyed only when the application is closed.
- **Dependent Pseudo Scope:** A scope associated with the *@Dependent* annotation is bound to a single object and it is the default scope applied by the container when no scope is defined explicitly. Because service components with such scope are associated with a single object, they are not shareable between client applications.
- **Request Scope:** A *@RequestScope* annotation refers to a bean created once per request and destroyed when the request scope ends. During the lifetime of the single request, this components remains active to be shared by all beans involved in a single request.
- **Session Scope:** To share the service component across multiple request within the same HTTP session, *@SessionScope*d annotation can be used. Once the HTTP session begins, a service component is instantiated and maintained by the container until the session is expired or explicitly closed.
- **Conversation Scope:** A *@ConversationScope* annotated service component is live till the life cycle of a conversation. Conversation can be either transient or a long-running state. A conversation can a long-running procedure; for example, in a web application a registration process may need to fill in a form across multiple pages. Thus, conversation can begin by Conversation.begin() and mark its end by Conversation.end().

### Ioc and cdi

#### 	IoC - универсальный принцип, используется много где.

Основные концепции следующие:

- Жизненным циклом компонента управляет контейнер, а не программист.

То есть программист руками не инициирует создание компонента, не инициирует его переход в активную фазу и обработку запроса, и не инициирует его уничтожение. Программист может навешивать обработчики на события жизненного цикла.

- За взаимодействие между компонентами отвечает не программист, а контейнер.

То есть вместо того, чтобы из компонента "А" обратится в компонент "В" ручками, программист говорит контейнеру: перекинь мне, пожалуйста, эти данные в компонент В.

#### 	CDI (Context & Dependency injection) - 

позволяет снизить или совсем убрать зависимость компонента от контейнера.

Компонент, конечно, зависим от контейнера, так как второй его порождает. Мысль в том, что программист не зависит от API, предоставляемого контейнером.  API, предоставляемые контейнером используется декларативно.

То есть когда мы пишем компонент, мы обычно не реализуем их интерфейсов, все взаимодействие с контейнером и все спецификации того, что за компонент мы пишем, мы делаем с помощью аннотаций. Мы не вызываем API напрямую. Это тоже обычно делается через аннотации. Получается, что вся логика взаимодействия с контейнером делается через метапрограммирование. То есть мы задаем метаданные о том, что это за компонент,  что с ним нужно делать, на какие события жизненного цикла мы вешаем обработчики, и с какими внешними компонентами он взаимодействует и каким образом.



## 2.Структура и архитектура Vue: экземпляры, компоненты, директивы

https://cn.vuejs.org/v2/guide/components.html

https://www.runoob.com/try/try.php?filename=vue2-hw

https://juejin.cn/post/7010598161525768222

## 3.JSF страница, динамически подгружающая новости с ManagedBean. Формат: имя аннотация …. иллюстрация, поле которое при нажатии показывает полный текст новости

js

```
var show = true;
function showWriter(){
    if(show){
        document.getElementById("writer").innerText=document.getElementById("hide_writer").value;
    }else {
        document.getElementById("writer").innerText="";
    }
    show = !show;
}
```

html

```
<h:head>
    <h:outputScript library="js" name="newse.js"/>
</h:head>
<h:body>
    <div>
        <label onclick="showWriter()">Click to get your writer</label>
    </div>
    <div id="writer"></div>
    <h:inputText id="hide_writer" value="#{news.writer}" style="visibility: hidden"/>
</h:body>
</html>
```

bean

```
package Data;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Data
@ManagedBean(name = "news")
@SessionScoped
public class news {
    String writer = "Liao";
    String Date = "Date";
}
```



# Билет 6

## 1.MVC-модель JSF

### Controller

faceservlet - 我们不用管理

### View

xhtml or jsp with ui component

### Model

Managed bean

### 2. CDI Beans: основные аннотации

1. @Named: bean的名字会和类名一致
2. @inject: 可以在普通初始化方法，属性，以及构造方法上（构造只能有一个）。可以不依靠setter。
3. @ManagedPropery(value ="#{}")手动注入另一个bean
4. @ApplicationScoped
5. @ManagedBean

https://www.baeldung.com/java-ee-cdi

创建实例https://netbeans.apache.org/kb/docs/javaee/cdi-inject_zh_CN.html

https://blog.csdn.net/J2EE_ME/article/details/89139790

### 3.Написать React компонент формирующий таблицу пользователей, данные приходят в props

class Table extends React.Component {  

render(){

const {users} = this.props;

return (

```
<table className={"table table-striped table-bordered table-hover table-condensed"}>
    <thead>
    <tr>
        <th>UserName</th>
        <th>Password</th>
    </tr>
    </thead>
    <tbody>
    {
    	users.map((ele,key)=>{
    		return <tr key = {key}>
    			<th>{ele.username}</th>
    			<th>{ele.password}</th>
    		</tr>
    	})
    }
    </tbody>
</table>;
);
}
```

}

ReactDOM.render(<Table/>,    document.getElementById('example') );



# Билет 7

## 2.Spring Web MVC: View and View resolvers

在Spring中视图相关的两个接口是`ViewResolver`和`View`两个接口，`ViewResolver`用于视图名称和实际视图之间的映射，`View`接口用于准备和传递数据到实际的视图。

SpringMVC的视图解析流程为：
　　
1、调用目标方法，SpringMVC将目标方法返回的String、View、ModelMap或是ModelAndView都转换为一个ModelAndView对象；

2、然后通过视图解析器（ViewResolver）对ModelAndView对象中的View对象进行解析，将该逻辑视图View对象解析为一个物理视图View对象；

3、最后调用物理视图View对象的render()方法进行视图渲染，得到响应结果

 ### View

　　视图的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户。
　　为了实现视图模型和具体实现技术的解耦，Spring在org.springframework.web.servlet包中定义了一个高度抽象的View接口。
　　视图对象由视图解析器负责实例化。由于视图是无状态的，所以他们不会有线程安全的问题。所谓视图是无状态的，是指对于每一个请求，都会创建一个View对象。
　　JSP是最常见的视图技术。

### View resolvers

The *ViewResolver* maps view names to actual views.

![avator](C:\Users\x1761\Desktop\web\微信截图_20220118174510.png)

   1. ViewResolve的作用就是通过解析MdoelAndView，将MdoelAndView中的逻辑视图名变为一个真正的View对象，并将MdoelAndView中的Model取出。

      2.View的作用就是在获取到ViewResolve传来的View和Model，对Model进行渲染，通过View对象找到要展示给用户的物理视图，将渲染后的视图展示给用户。用很直白的话将就是将数据通过request存储起来，找到要展示给用户的页面，将这些数据放在页面中，并将页面呈现给用户

## 2.Angular ключевые особенности и различия с AngularJS

Angualr 是开源自由的谷歌的网络应用框架。由ts写成。使用时必须настроить сборочное окружение на базе node.js и npm。应用由NgModules组成。模块作为组件的上下文。组件组成views。组件在di的帮助下和服务器交互。可以创建spa(单页应用)。

![avator](C:\Users\x1761\Desktop\web\微信截图_20220123162259.png)

### 区别

1. Angular-基于ts的开源web应用程序框架  --  AngularJS-基于js的开源前端web框架
2. Angularjs不适用于移动端游览器
3. Angularjs支持MVC，使用model。而angular使用组件和指令(components and directives)
4. Angualrjs 使用bg-bind绑定数据，而angular使用()以及[]
5. AngularJs结构上没那么好管理
6. 路由不同

https://proglib.io/p/angular-and-react

## 3.Интерфейс на JSF(xhtml+CDI Бин) реализующий сервис по отчислению студентов. Студенты должны браться из коллекции на стороне сервера, напротив каждой фамилии должна находиться кнопка "Отчислить" клик по которой убирать отчисленного студента из списка. Список должен обновляться через AJAX.

```
<h:dataTable value="#{students.list}" var="o">
    <h:column>
        <f:facet name="header">Student name</f:facet>#{o.name}
    </h:column>
    <h:column>
        <f:facet name="header">isu number</f:facet>#{o.isu}
    </h:column>
    <h:column>
        <f:facet name="header">delete</f:facet>
        <f:commandButton value="отчислить">
            <f:ajax render="table"/>
        <f:commandButton/>
    </h:column>
</h:dataTable>
```

```
package Data;

import javax.faces.bean.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DataBase.DataBaseStorage;
import lombok.Data;

@ManagedBean(name="students")
@Data
@SessionScoped
public class students implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Student> list = new ArrayList<>();

    public void delete(Student s){
        this.list.remove(s);
    }
}
```

@Data

public class Student implements Serializable {
    private String name；

​    private int number

}



# Билет 8

## 1. Профили и платформы Java EЕ

Java EE — это платформа, построенная на основе Java SE, которая предоставляет API и среду времени выполнения для разработки и запуска крупномасштабных, многоуровневых, масштабируемых, надежных и безопасных сетевых приложений. Подобные приложения называют корпоративными (Enterprise applications), так как они решают проблемы, с которыми сталкиваются большие бизнесы.

![avatar](C:\Users\x1761\Desktop\web\lab4\微信截图_20220108204207.png)

## 2.Типы DI в Spring

setter注入、构造器注入和基于注解的注入（也叫field注入）

### 注解注入

1. 注入方式非常简单：加上`@Autowired注解`，加入要注入的字段，即可完成。

### 构造器注入

用类构造器。用xml辅助

```xml
<bean id="accountService" class="com.itshutong.service.impl.AccountServiceImpl">
    <constructor-arg name="name" value="罗辑"/>
    <!--       age 的值会被自动转为 int 型-->
    <constructor-arg name="age" value="32"/>
    <constructor-arg name="birthday" ref="now" />//引向另一个bean
</bean>
```

### setter注入

<bean name="springAction" class="com.bless.springdemo.action.SpringAction">  <!--(1)依赖注入,配置当前类中相应的属性-->  <property name="springDao" ref="springDao"></property>  </bean> 

或者name="" value = ""

https://juejin.cn/post/6844904206021853198

реже всего

https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring

多构造问题https://blog.csdn.net/zhujianlin1990/article/details/103685797

```
@Configuration
public class Beans {
    @Bean(name="dotBean")
    @Scope(value = "session")
    public DotBean dotBean(){
        return new DotBean();
    }
}
```

## 3.Написать интерфейс на React, состоящий из двух страничек: домашней (/home) и новостной (/news). Переход между страницами должен осуществляться при помощи гиперссылок.

```
<body>
<div id="app"></div>
</body>
```

```
ReactDOM.render(
    <Provider store={Store}>
         <Router>
                <App/>
         </Router>
    </Provider>,
    document.getElementById("app")
);
```

```
class App extends React.Component {
    render(){
        return(
            <div>
                <Routes>
                    <Route path={"/"} element={<LoginPage/>}/>
                    <Route path={"/main"} element={<Main/>}/>
                    <Route path={"/register"} element={<Register/>}/>
                </Routes>
            </div>
        )
    }
}
```

```
<a href={"/"}>Home</a>
```



# Билет 9

## 1.Структура JSF приложения

1. java-服务器（bean，数据库）

2. resources:图像等
3. webapp:jsp(xhtml)，web.xml，faces-config.xml配置文件

[Билет 6](#Билет 6)

![avator](C:\Users\x1761\Desktop\web\微信截图_20220123170037.png)

## 2.Spring MVC: особенности, интеграция в Spring

Spring MVC 拥有强大的灵活性，非侵入性和可配置性。
Spring MVC 提供了一个前端控制器 DispatcherServlet ，开发者无须额外开发控制器对象。
Spring MVC 分工明确，包括控制器、验证器、命令对象、处理器映射器、视图解析器等，每一个功能实现由一个专门的对象负责。
Spring MVC 可以自动绑定用户输入，并正确地转换数据类型。
Spring MVC 使用一个名称 / 值的 Map 对象实现更加灵活的模型数据类型。
Spring MVC 内置了常见的校验器，可以检验用户输入，如果校验不同，则重定向回输出表单。输入校验是可选的，并且支持编程方法及声明方式。
Spring MVC 支持国际化，支持根据用户区域显示多国语言，并且国际化的配置非常简单。
Spring MVC 支持多种视图技术，最常见的由JSP技术以及其他技术，包括 Velocity 和 FreeMarker 。
Spring MVC 提供了一个简单而强大的JSP标签库，支持数据绑定功能，使得编写JSP页面更加容易。

1、它是基于组件技术的.全部的应用对象,无论控制器和视图,还是业务对象之类的都是java组件.并且和Spring提供的其他基础结构紧密集成.
2、不依赖于Servlet API(目标虽是如此,但是在实现的时候确实是依赖于Servlet的)
3、可以任意使用各种视图技术,而不仅仅局限于JSP
4、支持各种请求资源的映射策略
5、它应是易于扩展的

https://zhuanlan.zhihu.com/p/22420952

————————————————
<dependency>  

 <groupId>org.springframework.boot</groupId> 

<artifactId>spring-boot-starter-web</artifactId>

<version>2.3.7.RELEASE</version> </dependency>

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.1.1.RELEASE</version>
</dependency>
<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
<dependency>  
    <groupId>javax.servlet</groupId>  
    <artifactId>servlet-api</artifactId>  
    <version>3.0-alpha-1</version>  
</dependency>
```

Spring是IOC和AOP的容器框架，SpringMVC是基于Spring功能之上添加的Web框架，想用SpringMVC必须先依赖Spring。

Spring MVC中自带一个开箱即用的Dispatcher Servlet，该Servlet的全名是org.springframework.web.servlet.DispatcherServlet。要使用这个servlet，需要在部署描述符（web.xml文件)中使用servlet和servlet-mapping元素来配置它，如下所示：

## 3.Написать CDI Bean калькулятор, поддерживающий 4 базовые операции для целых чисел

@RequestScoped

@Named

@Data

public class Cauculator{

​	private Integer a;

​	private Integer b;

​	public Integer add(){

​		return a+b;

​	}

​	public Integer min(){

​		return a-b;

​	}

​	public Double diver(){

​		return a/b;

​	}

​	public Integer mul(){

​		return a*b;

​	}

}



## Билет 10

## 1.Spring MVC: обработка запросов, DispatcherServlet

[билет 7]( # билет 7)

DispatcherServlet是前置控制器，配置在web.[xml文件](https://baike.baidu.com/item/xml文件/1994443)中的。拦截匹配的请求，Servlet拦截匹配规则要自己定义，把拦截下来的请求，依据相应的规则分发到目标Controller来处理，是配置spring MVC的第一步。

DispatcherServlet是前端控制器设计模式的实现，提供Spring Web MVC的集中访问点，而且负责职责的分派，而且与Spring IoC容器无缝集成，从而可以获得Spring的所有好处。

## 2.Single Page Application: преимущества, недостатки

Single page web application. В приложении только один html. Когда взаймодействует с пользователем, там обновляет ту html при помощи js, ajax и тд.

### 优点

- 良好的交互体验，页面首次加载完成后内容的改变不需要重新加载整个页面，具有更快的响应速度，具有桌面应用的即时性、网站的可移植性和可访问性。
- 良好的前后端工作分离模式，单页应用可以和`RESTful`架构一起使用，通过`RESTAPI`提供接口数据，有助于分离客户端和服务器端工作与`API`通用化。
- 减轻服务端压力，服务端不需要处理页面模板的逻辑与拼接，除首次加载页面外只需要提供数据信息即可，把计算尽量放在客户端，单页应用能提高单位服务器的负载量。
- 可维护性高，通常采用组件化与模块化开发，代码复用程度高，相对来说可维护性高

### 缺点

* 首屏加载时间会很长
* **плохо поддаются SEO оптимизации**. URL страниц практически не меняется, а данные подгружаются динамически, когда для оптимизации важна устойчивость и уникальные URL для каждой страницы. Поисковым ботам сложно просканировать SPA приложение, и на данный момент грамотно индексировать такие сайты умеет только Google;
* **сильно нагружают браузер.** Такая проблема возникает из-за относительно “тяжёлых” клиентских фреймворков;
* **могут недёшево стоить.** Разумеется, итоговая стоимость разработки разнится, но цена сложных сайтов довольно высока.(Кажется это потому что сложно работать с Router)

## 3.xhtml (JSF), CDI Bean: напишите интерфейс для ввода паспорта (серия, номер, дата выдачи, место выдачи)

@Data

@ManagedBean(name="passport")

public class Passport implements Serializable{

​	private String series;

​	private String Date;

​	private String place;

​	private String number;

}



<h:form >

​	<h:inputText value="#{passport.series}"/>

</h:form >



# Билет 11

## 1.Технология RMI. Использование RMI в Java EE

RMI允许Java方法引用远程对象并调用远程对象的方法。 远程对象可以驻留在另一个Java虚拟机，同一主机或整个网络上完全不同的主机上。 RMI通过对象序列化封送和解封方法参数，并支持跨网络动态下载类文件。

1. 定义远程接口并实现客户端和远程对象。
2. 编译源代码并生成存根和框架。
3. 使所需的类网络可访问。
4. 运行应用程序。

https://cloud.tencent.com/developer/article/1824106

## 2.Управление состоянием в React. Flux & Redux

props 从外界获取，status是内部状态。只有class组件有。

this.state={???}

this.setState({

​	???

});

https://juejin.cn/post/6844903806644256782

https://rune-dollar-ae8.notion.site/Web-b7258003fb804b53b57f3dc6c0c96d56

![](C:\Users\x1761\Desktop\web\微信截图_20220123223556.png)

### 3.Привести фрагмент кода управляемого бина, увеличивающего на 1 значение, отображаемое на кнопке при каждом клике по ней:
```
<h:commandButton actionListener = "#{myBean.increment}" value = "#myBean.time"/>
```

```
@Data
@ManagedBean(name = "myBean")
@SessionScoped
public class myBean {
    private int time = 0;
    public void increment(){
        this.time++;
    }
}
```



# Билет 12

## 1.JNDI. Варианты использования. Плюсы минусы

https://stackoverflow.com/questions/4365621/what-is-jndi-what-is-its-basic-use-when-is-it-used

用于检索资源。第三个实验用hibernate是写的文件就是一种使用

https://www.unblu.com/en/docs/latest/knowledge-base/jndi-datasource.html



## 2.React. Особенности. Архитектура

Одна из ключевых особенностей React — универсальность. Эту библиотеку можно использовать на сервере и на мобильных платформах с помощью React Native. Это принцип Learn Once, Write Anywhere или «Научитесь один раз, пишите где угодно».

Ещё одна важная особенность библиотеки — декларативность. С помощью React разработчик описывает, как компоненты интерфейса выглядят в разных состояниях. Декларативный подход сокращает код и делает его понятным

React основан на компонентах, это ещё одна ключевая особенность библиотеки. Каждый компонент возвращает часть пользовательского интерфейса со своим состоянием. Объединяя компоненты, программист создаёт завершённый интерфейс веб-приложения.

Важная особенность React — использование JSX. Это расширение синтаксиса JavaScript, которое удобно использовать для описания интерфейса. JSX похож на HTML, тем не менее это всё-таки JavaScript. 

К важным особенностям React относится использование виртуального DOM (Virtual DOM). Виртуальный DOM — объект, в котором хранится информация о состоянии интерфейса. При изменении состояния, например, после отправки формы или нажатия кнопки, React рассчитывает разницу между старым и новым состоянием. После этого библиотека отрисовывает новое состояние. Использование виртуального DOM позволяет библиотеке эффективно обновлять реальный DOM.

https://www.taniarascia.com/react-architecture-directory-structure/ из компонентов



## 3.Реализовать бин, который считает количество минут со старта приложения (или рестарта сервера)

```
@Data
@ApplicationScoped
@ManagedBean(name="timer")
public class Timer {
    private Date start;
    private Date now;
    private Integer mins;
    public Timer(){
        start = new Date();
        now = new Date();
    }

    public Integer getMins() {
        this.now = new Date();
        mins = (int)(now.getTime()-start.getTime())/60000;
        return mins;
    }
}
```

getTime得到的是毫秒。



# Билет 13

## 1.Платформы java. Назначение, сходства, отличия.

- Java SEhttps://ru.wikipedia.org/wiki/Java_Platform,_Standard_Edition

- Java EEhttps://baike.baidu.com/item/Java%20EE/2180381

- JAVA ME:

  Java Platform Compact Edition (Java ME) 为在移动和嵌入式设备上运行的应用程序提供了一个强大且可定制的环境：移动电话、数字接收器、蓝光光盘播放器、数字存储设备、M2M 模块、打印机等。

  Java ME 技术最初旨在克服为小型设备构建应用程序的限制。为此，Oracle 开发了 Java ME 技术来满足这些技术限制，并使 Java 应用程序能够在内存、屏幕尺寸和电池电量有限的小型设备上运行。

- JAVA EMBEDDED se me

- JAVA CARDhttps://ru.wikipedia.org/wiki/Java_Card

- JAVA TVhttps://en.wikipedia.org/wiki/Java_TV

## 2.Двухфазовые и трехфазовые конструкторы в Spring и Java EE.

https://github.com/spring-projects/spring-framework/issues/4934

## 3.10素数

```
<div>
just like that
</div>
<div id = "numbers">
#{number.result}
</div>
<h:form id="nf">
<h:commandButton value="click me" action="#{number.onClick}">
<f:ajax render="numbers" execute="nf"/>
</h:commandButton>
</h:form>


@Data
@ManagedBean(name="number")
@ApplicationScoped
public class NumberDot implements Serializable {
    private int number = 1;
    private HashSet<Integer> hashSet = new HashSet<>();
    private String result = "";
    public void onClick(){
        int a = 0;
        while (a<10){
            if(number==1||number==2||number==3){
                a++;
                hashSet.add(number);
                number++;
            }else {
                boolean add = true;
                for(int i = 2; i < number;i++){
                    if(number%i == 0){
                        add = false;
                    }
                }
                if(add){
                    a++;
                    hashSet.add(number);
                    number++;
                }
            }
        }
        result = toString();
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Integer i: hashSet){
            s.append(" ").append(i);
        }
        return s.toString();
    }
}
```

# Билет 14

## 1.Java EE CDI Beans стереотипы

注解模板

## Using Stereotypes in CDI Applications

https://blog.csdn.net/njq774327136/article/details/113818141

https://www.codeleading.com/article/31755712588/ 豆

A **stereotype** is a kind of annotation, applied to a bean, that incorporates other annotations. Stereotypes can be particularly useful in large applications where you have a number of beans that perform similar functions. A stereotype is a kind of annotation that specifies the following:

- A default scope
- Zero or more interceptor bindings
- Optionally, a `@Named` annotation, guaranteeing default EL naming
- Optionally, an `@Alternative` annotation, specifying that all beans with this stereotype are alternatives

A bean annotated with a particular stereotype will always use the specified annotations, so you do not have to apply the same annotations to many beans.

For example, you might create a stereotype named `Action`, using the `javax.enterprise.inject.Stereotype` annotation:

```
@RequestScoped
@Secure
@Transactional
@Named
@Stereotype
@Target(TYPE)
@Retention(RUNTIME)
public @interface Action {}
```

All beans annotated `@Action` will have request scope, use default EL naming, and have the interceptor bindings `@Transactional` and `@Secure`.

## 2.Разметка страницы в React- приложениях. JSX

https://www.cnblogs.com/cxying93/p/6114950.html

https://medium.com/@nutanbhogendrasharma/design-page-layout-and-styling-in-react-application-part-3-448d70263f70

## 3.JSF бин, который извлекает содержимое «такой-то» таблицы базы данных при создании сессии (@SessionScoped).

```
package Data;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="dotsTable")
@SessionScoped
public class DotsTable implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id",unique=true, nullable = false)
    private Long id;
    @Column(nullable = false,name="x")
    private Double x;
    @Column(nullable = false,name="y")
    private Double y;
    @Column(nullable = false,name="r")
    private Double r;
    @Column(nullable = false,name="hit")
    private boolean hit;
    @Column(nullable = false,name = "time")
    private String time;
    DotsTable(Double x,Double y,Double r, UserDate date){
        this.x = x;
        this.y = y;
        this.r = r;
        this.time = date.getDateString();
        this.hit=Validator.validateRange(x,y,r);
    }
}
```

```
public void addNewDot(DotsTable dot) throws Exception{
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String password = "163752410";
    String user = "postgres";
    Class.forName("org.postgresql.Driver");
    try(Connection connection = DriverManager.getConnection(url,user,password)){
        try(PreparedStatement ps= connection.prepareStatement(
                "INSERT INTO dotstable(hit,r,time,x,y) values (?,?,?,?,?)"
        )){
            ps.setBoolean(1,dot.isHit());
            ps.setDouble(2,dot.getR());
            ps.setObject(3,dot.getTime());
            ps.setDouble(4,dot.getX());
            ps.setDouble(5,dot.getY());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }catch (SQLException e){
        e.printStackTrace();
    };
}
```



# Билет 15

## 1.Валидаторы в JSF

https://studfile.net/preview/1029759/page:3/

## 2.Реализация контроллера в Spring Web MVC

https://developer.aliyun.com/article/654170

## 3.Vue.js простейший чат бот, который на любое сообщение отвечает «сам дурак»

```
<body>
<div id="app">
    <input type="text" v-model="message">
    <button v-on:click="chat">send</button>
    <div id="room"></div>
</div>
<script>
    new Vue({
        el: '#app',
        data: {
            message: 'Input here!'
        },
        methods:{
          chat:function (){
              let a = document.createElement("div");
              let aInner = document.createTextNode("You:"+this.message);
              a.appendChild(aInner);
              let b = document.createElement("div");
              let bInner = document.createTextNode("Robot:noob");
              b.appendChild(bInner);
              document.getElementById("room").appendChild(a);
              document.getElementById("room").appendChild(b);
          }
        }
    })
</script>
</body>

```



# Билет 16

## 1.Фаза формирования представления

[Билет 1](# Билет 1)

## 2.Spring framework. Отличия от Java EE

https://www.geeksforgeeks.org/difference-between-javaee-and-spring/

https://www.jianshu.com/p/910fc2a2415d

https://ru.education-wiki.com/1820801-java-ee-vs-spring



## 3.REST-контроллер, реализующий конвертацию температуры из Цельсия в Фаренгейты и наоборот

```
@RestController
public class AppController {
    @RequestMapping(value = {"/temp"})
    public Temperatrue app(HttpServletRequest request){
       bool isCen =  Boolean.getBoolean(request.getParameter("isCen"));
       Double temperatrue = Double.parseDouble(request.getParameter("temperature"));
       if(isCen){
       		isCen=!isCen;
       		temperatrue = temperatrue+273;
       }else{
       		isCen=!isCen;
			temperatrue = temperatrue-273;
	   }
        return new Temperatrue(temperatrue,isCen)
    }
}
@Data
public class Temperatrue{
	private double temperatrue;
	private bool isCen;
	public Temperatrue(Double temperatrue,bool isCen){
		this.isCen = isCen;
		this.temperatrue = temperatrue;
	}
}
```



# Билет 17

## 1.Java EE CDI Beans прерывание жизненного цикла (Interception)

https://blog.csdn.net/ilovewqf/article/details/50487273

https://blog.csdn.net/qq_33429968/article/details/64129265?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0.no_search_link&spm=1001.2101.3001.4242.1&utm_relevant_index=3

cdi大全

https://docs.oracle.com/middleware/12211/wls/WLPRG/cdi.htm#WLPRG549

拦截器位置

https://www.jianshu.com/p/09caf3e23ca3



## 2.React компонент. State + Props. Умные и глупые компоненты

state 和 props 主要的区别在于 **props** 是不可变的，而 state 可以根据与用户交互来改变。这就是为什么有些容器组件需要定义 state 来更新和修改数据。 而子组件只能通过 props 来传递数据。

聪明，state

傻瓜，props



## 3.Написать JSF страницу и многострочным полем, в которое можно вводить только стройные симврлы латинице.

```
<ui:define name="content">
    <h:inputTextarea id="text-area-id"  required="true" value="#{timer.s}" requiredMessage="only lating" cols="80" rows="20">
        <f:validator validatorId="input"/>
    </h:inputTextarea>
    <h:message for="text-area-id" style="color: red" id="validMes"/>
    <h:commandButton value="send">
        <f:ajax render="validMes" execute="text-area-id"/>
    </h:commandButton>
</ui:define>
```

```
@FacesValidator("input")
@Data
public class ValidatorInput implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (value.toString().matches("[^a-zA-Z]")){
            FacesMessage msg = new FacesMessage("y validation failed","field must be not null");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
```



# Билет 18

## 1.Rest контроллер в Spring: сериализация и десериализация данных

https://blog.csdn.net/qq_42617455/article/details/109622390



## 2.Архитектура приложения в Angular: модули, компоненты, представления, сервисы

![avator](C:\Users\x1761\Desktop\web\lab4\微信截图_20220113172306.png)

![微信截图_20220113172314](C:\Users\x1761\Desktop\web\lab4\微信截图_20220113172314.png)

## 3.Конфигурация faces-config, задающая managed bean с именем myBean, которым будет управлять сам программист

```
<managed-bean>
<managed-bean-name>helloWorld</managed-bean-name>
<managed-bean-class>net.proselyte.jsftutorial.HelloWorld</managed-bean-class>
<managed-bean-scope>request</managed-bean-scope>
</managed-bean>
```

```
<managed-bean>
    <managed-bean-name>myBean</managed-bean-name>
    <managed-bean-class>Data.Dots</managed-bean-class>
    <managed-bean-scope></managed-bean-scope>
</managed-bean>
```



# Билет 19

## 1.JSF: ключевые особенности, преимущества, недостатки

https://www.yiibai.com/jsf/jsf-features.html

![avator](C:\Users\x1761\Desktop\web\lab4\微信截图_20220113210355.png)

![微信截图_20220113210359](C:\Users\x1761\Desktop\web\lab4\微信截图_20220113210359.png)

![微信截图_20220113210420](C:\Users\x1761\Desktop\web\lab4\微信截图_20220113210420.png)

## 2.Что такое CDI bean и зачем они нужны, если есть Managed beans и EJB beans.

https://stackoverflow.com/questions/15520424/what-is-a-cdi-bean

https://stackoverflow.com/questions/27472052/ejb-bean-and-cdi-bean-and-injection

EJB AND CDIhttps://stackoverflow.com/questions/13487987/where-to-use-ejb-3-1-and-cdi

https://stackoverflow.com/questions/4684112/how-do-cdi-and-ejb-compare-interact

jsf vs cdihttps://stackoverflow.com/questions/4347374/backing-beans-managedbean-or-cdi-beans-named

https://blog.oio.de/2014/02/04/when-to-prefer-ejb-over-cdi/



# 3.Angular компонент, который позволяет поделиться чем-то в VK, Twitter, Facebook (API для соцсетей можно описать текстом)

app.module.ts

import { ReactiveFormsModule } from '@angular/forms';

@NgModule({

​	imports:[

​		ReactiveFormsModule,

  	BrowserModule,

​	],

   declarations: [

  FormComponent,

​	app.module.ts

]

}

export class AppMudult{}



form.component.ts

import { Component } from '@angular/core';

import { FormBuilder } from '@angular/forms';

import { [HttpClientModule](https://angular.cn/api/common/http/HttpClientModule) } from '@angular/common/[http](https://angular.cn/api/common/http)';

@Component({

 selector: 'app-form',

 templateUrl: './app-form.component.html',

 styleUrls: ['./app-form.component.css']

})

export class AppFormComponent{

​	appForm = this.formBuilder.group({

​		name:'';

​		password:''

​	});

​	constructor(private formBuilder: FormBuilder,

​		private userService:UserService,

​		private http: HttpClient

​	){}

​	onSubmit():void{

​		this.http.post(url,{data in json from form},).subscribe(after getting response);

​	}

}

let myfArr = this.skillForm.get('skills').value



html

<form [formGroup]="checkoutForm" (ngSubmit)="onSubmit()">

  <div>
    <label for="name">
      Name
    </label>
    <input id="name" type="text" formControlName="name">
  </div>

  <div>
    <label for="address">
      Address
    </label>
    <input id="address" type="text" formControlName="address">
  </div>

  <button class="button" type="submit">Purchase</button>

</form>

# Билет 20

## 1.Построение интерфейсов на JSF. Иерархия компонентов JSF.

Интерфейс строится из компонентов. Компоненты реализуют интерфейс javax.faces.component.UIComponent. И компонент объединены в древовидную структуру.JSP или xhtml применяется для представления. Для того, чтобы добавить компонент в страницу, можем использовать технологию facelets. Таким образом, мы работаем просто с разными тегами(ui:insert , h:head).  Основа дерева компонентов является UIViewRoot

![](C:\Users\x1761\Desktop\web\微信截图_20220123230646.png)

## 2.CDI beans: принципы инъекций

Context dependecy injection

context - значит можно взаймодействовать с другими компонентами и жц может зависеть от другого чего-то (bean...)

DI - inject какой-то компонент в приложение

CDI - позволяет снизить зависимтость компонента от контейнера. Программист не прямо вызывает API(Обычно при помощи аннотации).

Чтобы нормально inject. Первый шаг - это создать обычный класс с  сonstructor без аргумента. И потом прямо @inject.

Классы можно inject:

1. Почти все обычные java class
2. java ee resource
4. bean in distance

Необходимо существование beans.xml(хотя пустый).@Inject支持构造函数、方法和字段注解，也可能使用于静态实例成员。可注解成员可以是任意修饰符（private,package-private,protected,public）。

@Named или @Qualifier

可以用于方法，构造器，属性

## 3.Приложение на Angular, реализующее форму для заполнения бланка на отчисление по собственному желанию. Форма должна принимать на вход имя пользователя и дату, и формировать заполненный бланк заявления (на клиентской стороне)

import { [Component](https://angular.cn/api/core/Component) } from '@angular/core';

import { [FormBuilder](https://angular.cn/api/forms/FormBuilder) } from '@angular/forms'; 

@Component({

​	selector:'my-producer',

​	templateUrl:'./.html',

​	styleUrls:['./css']

})

export class CartComponent { 

​	checkoutform = this.formBuilder.group({    name: '',    date: ''  }); 

​	constructor(   private formBuilder: [FormBuilder](https://angular.cn/api/forms/FormBuilder),  ) {}

​	 delete():void{

​			this.studentService.delete(user)

​		}

}

<form [formGroup] = "checkoutform" (ngSubmit) = "delete()">
    <div>
        <label for="name">
            Name
        </label>
        <input id = "name" type="text" formControlName="name"/>
    </div>
    <div>
        <label for="date">
            date
        </label>
        <input id = "date" type="text" formControlName="date"/>
    </div>
</form>

# Билет 21

## 1.Managed Beans что это, применение, вызов из jsp/xhtml

Обычный класс в jsf, который содержит getter, setter и  constructor. Играет роль model UI.

 Создание bean:

1.  faces-config.xml

   <managed-bean>

   </managed-bean>

2. @ManagedBean + @???Scoped

Вызов при выражение #{}

![](C:\Users\x1761\Desktop\web\微信截图_20220123205118.png)

## 2.Spring Web MVC аритектура и состав

![avator](C:\Users\x1761\Desktop\web\微信截图_20220118174510.png)

## 3-1.Написать на Angular интерфейс, который проверяет если ли в куки sessionid и если нет, отправляет пользователя на аутентификацию по логину и паролю

@Component({

​	selector:

​	temlateUrl:

​	styleUrls:

})

class MyForm{

​	checkoutForm = this.FormBuilder.group({

​	name'',

​	password:''

});

​	sessionid = cookie.get("id");

​	constructor(

​		private formBuilder:FormBuilder,

​		private cookie:CookieService	

)

​	onSubmit:void{

​		--

​	}

}



html

<form [formGroup] = "checkoutForm" (ngSubmit)="onSubmit()">

  <div>
    <label for="name">
      Name
    </label>
    <input id="name" type="text" formControlName="name">
  </div>

  <div>
    <label for="password">
      Password
    </label>
    <input id="address" type="text" formControlName="password">
  </div>
```html
<div *ngIf = "sessionid">
<button class="button" type="submit">Send</button>    
</div>  
<div *ngIf = "!sessionid">
	Sorry, id exsits, you can't do submit.
</div>   
 
```

</form>

## 3-2.Реакт интерфейс,который при наличии у юзера куки с id=jSessionid показывает форму для ввода и пароля

```
import cookie from "react-cookies";
class App extends React.Component {
    render(){
        if(cookie.load("id")===jSessionid||cookie.load("id")==jSessionid){
        	return(
            <div className={"form-group"}>
                            <label>
                                input Y:
                            </label>
                            <br/>
                            <input type={"text"} name={"Y"} className={"form-control"} value={Y} onChange=								{getY}/>
                        </div>
			);
		}else {
            return(<div></div>);
        }
    }
}
```

## Билет 22

## 1.Контекст управляемых бинов. Конфигурация контекста бина

**@RequestScoped** - используется по умолчанию. Создаётся новый инстанс *managed bean* на каждый HTTP запрос. Если, например форма будет содержать данные, которые необходимо будет отправить на сервер для обработки, то инстанс данного бина будет создаваться 2 раза: 1 - создаётся по первому запросу (*initial request*), 2 - создаётся по отправке формы (*postback*). Контекст —запрос.

**@SessionScoped** - инстанс создаётся один раз при обращении пользователя к приложению, и используется на протяжении жизни сессии.*Managed bean* обязательно должен быть Serializable. Контекст — сессия.

**@ApplicationScoped** - инстанс создаётся один раз при обращении, и используется на протяжении жизни всего приложения. Не должен иметь состояния, а если имеет, то должен синхронизировать доступ, так как доступен для всех пользователей. Контекст — приложение.

**@ViewScoped** - инстанс создаётся один раз при обращении к странице, и используется ровно столько, сколько пользователь находится на странице (включая ajax запросы). Контекст — страница.

**@CustomScoped(value="#{someMap}")** - инстанс создаётся и сохраняется в Map. Программист сам управляет областью жизни

**@NoneScoped** - инстанс создаётся, но не привязывается ни к одной области жизни. Полезно применять в *managed bean*'е, на который ссылаются другие *managed bean*'ы, имеющие область жизни

+**flash scope** - объекты внутри этой области жизни будут доступны для последующего запроса, после чего очищаются. Другими словами объект во *flash scope* переживёт *redirect*, после чего умрёт

<managed-bean>

<managed-bean-name>calculator</managed-bean-name>

<managed-bean-class>com.arcmind.jsfquickstart.model.Calculator</managed-bean-class>

<managed-bean-scope>request</managed-bean-scope>

</managed-bean>

или аннотация

## 2.Шаблоны MVVM и MVP. Сходства и отличия от MVC

MVVMhttps://segmentfault.com/a/1190000023423921

MVPhttps://www.jianshu.com/p/4b754ea48a40

结合https://www.cnblogs.com/ranyonsue/p/12090647.html

## 3.Компонент для React, формирующий строку с автодополнением. Массив значений для автодополнения должен получаться с сервера посредством запроса к REST API

```
class AppComponent extends React.Component {
    constructor(props) {
        super(props);
        this.submitForm = this.submitForm.bind(this);
        this.state={textChanged:""}
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        this.refs.text.innerHTML = this.refs.text.innerHTML + this.state.textChanged;
    }

        submitForm (){
        //чтобы страницу обновляет, в этом методе дожно менять значение state
            event.preventDefault();
            let bridge = this;
            $.ajax({
                url: "api/?",
                method:"POST",
                data:{
                    ?
                },
                async:false,
                success:function (res){
                    data = bridge.state.textChanged + res.data;
                    bridge.setState({textChanged:data});
                }
            })
        }

    render() {
        return (
            <div ref={"text"}/>
            <button onClick={this.submitForm}>send</button>
        );
    }
}
```



## Билет 23

## 1.REST в спринге: методы и аргументы

https://blog.csdn.net/theVicTory/article/details/109605644

https://blog.csdn.net/lavorange/article/details/50696936

GET：获取URI对应信息

POST：创建相应值

PUT：创建/更新

DELETE：删除

HEAD：获取文件是否存在

аргумент может быть map, HttpServletRequest, объект java class(@Request body). Если при помощи spring mvc, даже можно просто использовть @RequestParam

## 2.Навигация в реакте. React Router

<BrowserRouter>(histroty api) или <hashRouter>(hash для сихроннизации ui и url.Не поддерживает loction.key and location.state)

1. import {BrowserRouter} from react-router-dom
2. <BrowserRouter>

​			<App/> // это где мы хочем использовать навигацию

​		</BrowserRouter>

​	3. 

```
<Routes>
    <Route path={"/"} element={<LoginPage/>}/>
    <Route path={"/main"} element={<Main/>}/>
    <Route path={"/register"} element={<Register/>}/>
</Routes>
```

Так пишим url и соответственные компонент(страница)

<Switch/> помогает только показывет один компонент

this.props.history.push(`/user/${value}`)

## 3.Реализовать бронь авиабилетов на jsf





# Билет 24

## 1.Класс FacesServlet - назначение, особенности конфигурации

​	**FacesServlet**

**FacesServlet** является сервлетом, который управляет жизненным циклом обработки запросов для веб-приложений, которые используют Поверхности JavaServer, чтобы создать пользовательский интерфейс.	

- Обрабатывает запросы с браузера.

- Формирует объекты-события и вызывает методы-слушатели.

Этот сервлет должен автоматически быть отображен, если он явно не отображается в `web.xml` или `web-fragment.xml`

<servlet-name>Faces Servlet</servlet-name>

<servlet-class>javax.faces.webapp.FacesServlet</servlet-class>

<load-on-startup>1</load-on-startup>

</servlet>

<servlet-mapping>

<servlet-name>Faces Servlet</servlet-name>

<url-pattern>/faces/*</url-pattern>

</servlet-mapping>

## 2.Vue.js - ключевые особенности, преимущества и недостатки

1) фрейворк легчий
2) MVVM, данные двухстронный
3) Взаймодействие между vue.js и страницей при помощи директивы
4) Состоит из компонентов
5) Как react. Тоже есть state, который может обновлять страницу

+

vue的优势：轻量级框架、简单易学、双向数据绑定、组件化、数据和结构的分离、虚拟DOM、运行速度快。

http://lantana-studio.ru/blog/verstka/7-osnovnyh-preimushhestv-vue-js-o-kotoryh-vazhno-znat/

-

https://medium.com/nuances-of-programming/reactjs-angular5-%D0%B8-vue-js-%D0%BA%D0%B0%D0%BA%D0%BE%D0%B9-%D1%84%D1%80%D0%B5%D0%B9%D0%BC%D0%B2%D0%BE%D1%80%D0%BA-%D0%B2%D1%8B%D0%B1%D1%80%D0%B0%D1%82%D1%8C-%D0%B2-2018-%D0%B3%D0%BE%D0%B4%D1%83-101702e51043

https://www.w3cschool.cn/article/16180153.html

## 3.REST - контроллер на Spring Web MVC, предоставляющий CRUD-интерфейс к таблице со списком покемонов

@RestController

public class MyRestController{

​	@Autowired

​		PokomenService pokomemService;

​	public Result addPokomem(HttpServletRequest request){

​		???

​		pokomemService.add(new Pokomem(?,?,?));

​	}

}

@Service

public class PokomemService{

​	@Autowired

​	PokomemRepository pokomemRepository

​	public void add(Pokomem p){

​		accountRepository.save(new PokomemJpa());

​	}

}

@Entity

@Data

PokomemJpa{

​	@Id

​	@GeneratedValue

​	private Interger id;

}

public interface PokomemRepository extends JpaRepository<PokomemJpa,Interger>{}

# Билет 25

## 1.Конверторы JSF, создание и назначе6ние

https://studfile.net/preview/1029759/page:6/

默认或者自己写

<f:converter converterId="">

## 2.Реализация model в Spring web MVC

https://www.javatpoint.com/spring-mvc-model-interface

https://www.baeldung.com/spring-mvc-model-model-map-model-view

https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation

## 3.Написать на vue.js интерфейс для навигации по текстовому документу, представленному в виде большой строки, должны быть реализованы переходы на следующую и предыдущую страницу

<head>
<script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
<script src="https://cdn.staticfile.org/vue-router/2.7.0/vue-router.min.js"></script>
</head>
<body>

<script>
	new Vue({
        el:"#router"
    	methods:{
            back:function(){
                this.$router.go(-1);
            },
            front:function{
                this.$router.go(1);               
            }
        }
    });
</script>

```vue
<div id="router">
	<div>
    	<a v-on:click="back">back</a>
  	</div>
  	<div>
   	 	<a v-on:click="front">front</a>
  	</div>	
</div>
```
</body>



# Билет 26

## 1.CDI Beans

CDI bean 是一个 POJO，普通的旧 java 对象，已由 CDI 容器自动实例化，并注入到应用程序中的所有和任何合格的注入点。 CDI 容器在部署期间启动 bean 发现过程。

Context dependecy injection

context - значит можно взаймодействовать с другими компонентами и жц может зависеть от другого чего-то (bean...)

DI - inject какой-то компонент в приложение

CDI - позволяет снизить зависимтость компонента от контейнера. Программист не прямо вызывает API(Обычно при помощи аннотации).

Чтобы нормально inject. Первый шаг - это создать обычный класс с  сonstructor без аргумента. И потом прямо @inject.

Классы можно inject:

1. Почти все обычные java class
2. Session beans
3. java ee resource
4. bean in distance

- `@RequestScoped`
- `@SessionScoped`
- `@ApplicationScoped`
- `@ConversationScoped`
- `@Dependent`

Необходимо существование beans.xml(хотя пустый).

@Named или @Qualifier

## 2.Angular DI

https://angular.cn/guide/dependency-injection

https://www.html.cn/qa/angular-js/10645.html

https://www.jianshu.com/p/4b10948d456c

![微信截图_20220123212214](C:\Users\x1761\Desktop\web\微信截图_20220123212214.png)

![微信截图_20220123212223](C:\Users\x1761\Desktop\web\微信截图_20220123212223.png)





## 3.JSF страничка с данными из бина

```
<h:inputText id="Y" value="#{dots.dot.y}" required="true">
	<f:validator validatorId="validatorInputY"/>
</h:inputText><br/>

<h:dataTable value="#{dots.dotsList}" var="o" id="table" width="400px" align="center" border="1">
    <h:column>
        <f:facet name="header">X</f:facet>#{o.x}
    </h:column>
    <h:column>
        <f:facet name="header">Y</f:facet>#{o.y}
    </h:column>
    <h:column>
        <f:facet name="header">R</f:facet>#{o.r}
    </h:column>
    <h:column>
        <f:facet name="header">Result</f:facet>#{o.hit}
    </h:column>
    <h:column>
        <f:facet name="header">Time</f:facet>#{o.date.dateString}
    </h:column>
</h:dataTable>
```

# Билет 27

## 1.Шаблоны и представление в Angular

https://angdev.ru/doc/angular-templates/

![](C:\Users\x1761\Desktop\web\微信截图_20220123212658.png)

![微信截图_20220123212715](C:\Users\x1761\Desktop\web\微信截图_20220123212715.png)

## 2.Dependency Lookup Spring

*Dependency lookup* is a pattern where a caller asks the container object for an object with a specific name or of a specific type. *Dependency injection* is a pattern where the container passes objects by name to other objects, via either constructors, properties, or factory methods.If you're using dependency lookup an object asks Spring for another object by name. With dependency injection Spring gives one object another object (without the receiving object asking for it).例如当时写实验时写的bean查找

https://www.quora.com/What-is-dependency-lookup-in-Spring

https://stackoverflow.com/questions/28039232/what-is-the-difference-between-dependency-injection-and-dependency-look-up

## 3. Конфигурация, чтобы JSF обрабатывал все запросы приходящие с .xhtml и со всех URL, начинающихся с /faces/

```
<servlet>
    <servlet-name>FacesServlet</servlet-name>
    <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>FacesServlet</servlet-name>
    <url-pattern>/*.xhtml</url-pattern>
    <url-pattern>/faces/*</url-pattern>
</servlet-mapping>
```

# Билет 28

## 1.Process validations phase, Update module values phase

[Билет 1](# Билет 1)

## 2.Жизненный цикл Spring-приложения

https://itsobes.ru/assets/JavaSobes/303.jpg

https://reflectoring.io/spring-bean-lifecycle/



- 重要https://blog.csdn.net/qq_38658567/article/details/110414035



https://stackoverflow.com/questions/24177556/application-lifecycle-events-with-spring

https://www.fatalerrors.org/a/understanding-the-spring-application-context-lifecycle.html



中文https://www.jianshu.com/p/43b65ed2e166

## 3.интерфейс на Anguliar, который выводит интерактивные часы с обновление каждую секунду

https://blog.csdn.net/qq_36451496/article/details/103294597

# Билет 29

## 1.Реализация Ajax в jsp

Как ajax в  javascript. Просто при помощи XMLHttpRequest или дополнительных бибиотек, например jquery(там метод ajax).

1. События происходит
2. Вызов метод ajax(XMLHttpReuqest, ajax,axios.get.....)
3. Запрос к сервлету
4.  Ждать ответ от сервера
5. Динамически меняет страницу на клиент по ответу

Не забуйте что мы работаем с servlet.  Url ajax должен быть  url к servlet, который мы сам пишим web.xml

```
<servlet>
    <servlet-name>AreaCheckServlet</servlet-name>
    <servlet-class>AreaCheckServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>AreaCheckServlet</servlet-name>
    <url-pattern>/checkAndResult</url-pattern>
</servlet-mapping>
```

## 2.CDI beans: контекст (Bean Scope)

### 1. Request – @RequestScoped

This scope describes a user’s interaction with a web application in a single HTTP request. The instance of the `@RequestScoped` annotated bean has an HTTP request lifecycle.

### 2. Session – @SessionScoped

This scope desrcibes a user’s interaction with a web application across multiple HTTP requests.

### 3. Application – @ApplicationScoped

In this case the state is shared across all users’ interactions with a web application. The container provides the same instance of the `@ApplicationScoped` annotated bean to all client requests.

### 4. Conversation – @ConversationScoped

This scope describes a user’s interaction with a JavaServer Faces application, within explicit developer-controlled boundaries that extend the scope across multiple invocations of the JavaServer Faces lifecycle. All long-running conversations are scoped to a particular HTTP servlet session and may not cross session boundaries.

Note that with `ConversationScoped` beans we achieve the same functionality we need from a `ViewScoped` JSF bean. In addition, with the `ConversationScoped` beans we can maintain the same conversation – or state – between distinct page requests. But when we leave a conversation without it, the managed bean will stay active until it times out.

A thing to notice is that beans that use session or conversation scope must be serializable. This is because the the container passivates the HTTP session from time to time, so when the session is activated again the beans’ state must be retrieved.

### 5. Singleton – @Singleton pseudo-scope

This is a pseudo-scope. It defines that a bean is once instantiated. When a CDI managed bean is injected into another bean, the CDI container makes use of a proxy. The proxy is the one to handle calls to the bean. Though, `@Singleton` annotated beans don’t have a proxy object. Clients hold a direct reference to the singleton instance. So, what happens when a client is serialized ? We must ensure that the singleton bean remains a singleton. To do so there are a fiew ways, such as, have the singleton bean implement `writeResolve()` and `readReplace()` (as defined by the Java serialization specification), make sure the client keeps only a transient reference to the singleton bean, or give the client a reference of type `Instance<X>` where `X` is the bean type of the singleton bean.

### 6. Dependent – @Dependent pseudo-scope

This pseudo-scope means that an object exists to serve exactly one client (bean) and has the same lifecycle as that client (bean). This is the default scope for a bean which does not explicitly declare a scope type. An instance of a dependent bean is never shared between different clients or different injection points. It is strictly a dependent object of some other object. It is instantiated when the object it belongs to is created, and destroyed when the object it belongs to is destroyed.

All predefined scopes except `@Dependent` are contextual scopes. CDI places beans of contextual scope in the context whose lifecycle is defined by the Java EE specifications. For example, a session context and its beans exist during the lifetime of an HTTP session. Injected references to the beans are contextually aware. The references always apply to the bean that is associated with the context for the thread that is making the reference. The CDI container ensures that the objects are created and injected at the correct time as determined by the scope that is specified for these objects.
You can also define and implement custom scopes. They can be used by those who implement and extend the CDI specification.



## 3.Интерфейс реализации логин+пароль на React. На стороне сервера- Rest API











spring mvc 总https://habr.com/ru/post/500572/

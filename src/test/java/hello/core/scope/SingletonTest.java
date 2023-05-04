package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonTest1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonTest2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonTest1 = " + singletonTest1);
        System.out.println("singletonTest2 = " + singletonTest2);
        Assertions.assertThat(singletonTest1).isSameAs(singletonTest2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean{

        @PostConstruct
        public void init(){
            System.out.println("singletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("singletonBean.destroy");
        }
    }
}

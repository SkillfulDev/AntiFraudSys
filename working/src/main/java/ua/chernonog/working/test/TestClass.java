package ua.chernonog.working.test;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Getter
@Setter
@Component
@Slf4j
public class TestClass {
    int age = 19;
    String notName = "Somebody";

    @PostConstruct
    void info() {
        log.info("testClassInfoAge={},testInfoName={}", new TestClass().getAge(),new TestClass().getNotName());
    }
}

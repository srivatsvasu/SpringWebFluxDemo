package com.example.demo;

import org.junit.Test;
import reactor.core.publisher.Mono;

/**
 * Created by C815317 on 3/1/2018.
 */
public class MonoTest {

    @Test
    public void firstMono() {
        Mono.just("A")
                .log()
                .subscribe();
    }

    @Test
    public void monoWithConsumer() {
        Mono.just("A")
                .log()
                .subscribe(s -> System.out.println(s));
    }

    @Test
    public void monoWithDoOn() {
        Mono.just("A")
                .log()
                .doOnRequest(r -> System.out.println("do on request" + r))
                .doOnSubscribe(s -> System.out.println("do on sub " + s))
                .doOnCancel(() -> System.out.println("do on cancel"))
                .doOnNext(s -> System.out.println("do On Next " + s))
                .doOnSuccess(s -> System.out.println("do on success " + s))
                .subscribe(s -> System.out.println(s));
    }

    @Test
    public void emptyMono() {
        Mono.empty()
                .log()
                .subscribe(System.out::println);
    }

    @Test
    public void emptyCompleteConsumerMono() {
        Mono.empty()
                .log()
                .subscribe(System.out::println, null , () -> System.out.println("Done"));
    }


    @Test
    public void errorRuntimeExceptionMono() {
        Mono.error(new RuntimeException())
                .log()
                .subscribe();
    }
    @Test
    public void errorExceptionMono() {
        Mono.error(new Exception())
                .log()
                .subscribe(System.out::println,e-> System.out.println("Error" +e));
    }

    @Test
    public void doOnErrorMono() {
        Mono.error(new Exception())
                .log()
                .doOnError(e -> System.out.println("Error "+e))
                .subscribe();
    }

    @Test
    public void errorOnErrorResume() {
        Mono.error(new Exception())
                .onErrorResume(e -> {
                    System.out.println("Error Caught "+e);
                    return Mono.just("B");
                })
                .log()
                .subscribe();
    }
    @Test
    public void errorOnErrorReturn() {
        Mono.error(new Exception())
                .onErrorReturn("B")
                .log()
                .subscribe();
    }

}

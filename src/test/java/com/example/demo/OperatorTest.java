package com.example.demo;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Created by C815317 on 3/1/2018.
 */
public class OperatorTest {

    @Test
    public void map() {

        Flux.range(1, 5)
                .map(i -> i * 10)
                .subscribe(System.out::println);

    }

    @Test
    public void flatmap() {

        Flux.range(1, 5)
                .flatMap(i -> Flux.range(i * 10, 3))
                .subscribe(System.out::println);

    }


    @Test
    public void flatmapMany() {

        Mono.just(3)
                .flatMapMany(i -> Flux.range(1, i))
                .subscribe(System.out::println);

    }

    @Test
    public void concat() throws InterruptedException {

        Flux<Integer> oneToFive = Flux.range(1, 5).delayElements(Duration.ofMillis(200));
        Flux<Integer> sixToTen = Flux.range(6, 5).delayElements(Duration.ofMillis(400));

  /*      Flux.concat(oneToFive,sixToTen)
                .subscribe(System.out::println);*/


        oneToFive.concatWith(sixToTen)
                .subscribe(s -> System.out.println(s));
        Thread.sleep(4000);

    }

    @Test
    public void merge() throws InterruptedException {

        Flux<Integer> oneToFive = Flux.range(1, 5).delayElements(Duration.ofMillis(200));
        Flux<Integer> sixToTen = Flux.range(6, 5).delayElements(Duration.ofMillis(400));

        Flux.merge(oneToFive, sixToTen)
                .subscribe(System.out::println);

        /*oneToFive.mergeWith(sixToTen)
                .subscribe(s-> System.out.println(s));
*/
        Thread.sleep(4000);

    }

    @Test
    public void zip() throws InterruptedException {

        Flux<Integer> oneToFive = Flux.range(1, 5);
        Flux<Integer> sixToTen = Flux.range(6, 5);

        Flux.zip(oneToFive, sixToTen)
                .subscribe(System.out::println);

        /*oneToFive.zipWith(sixToTen)
                .subscribe(s-> System.out.println(s));
*/
        Thread.sleep(4000);

    }


}

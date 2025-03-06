package example.micronaut;

import reactor.core.publisher.Mono;

interface FruitService {

    Mono<Fruit> save(Fruit fruit);

}
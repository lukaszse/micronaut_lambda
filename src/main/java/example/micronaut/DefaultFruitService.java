package example.micronaut;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.List;

@Singleton
class DefaultFruitService implements FruitService {

    private final FruitRepository fruitRepository;

    public DefaultFruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Publisher<Fruit> list() {
        return fruitRepository.findAll();
    }

    public Mono<Fruit> save(Fruit fruit) {
        if (fruit.getId() == null) {
            return Mono.from(fruitRepository.save(fruit));
        } else {
            return Mono.from(fruitRepository.update(fruit));
        }
    }

    public Publisher<Fruit> find(@NonNull String id) {
        return fruitRepository.findById(id);
    }

    public Publisher<Fruit> findByNameInList(List<String> name) {
        return fruitRepository.findByNameInList(name);
    }
}
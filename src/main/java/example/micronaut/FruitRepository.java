package example.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import org.reactivestreams.Publisher;

import java.util.List;

@MongoRepository 
public interface FruitRepository extends ReactiveStreamsCrudRepository<Fruit, String> { 

    @NonNull
    Publisher<Fruit> findByNameInList(@NonNull List<String> names); 
}
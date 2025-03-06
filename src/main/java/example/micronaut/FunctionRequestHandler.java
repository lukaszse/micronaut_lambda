package example.micronaut;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import io.micronaut.function.aws.MicronautRequestHandler;
import io.micronaut.serde.jackson.JacksonJsonMapper;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FunctionRequestHandler extends MicronautRequestHandler<SQSEvent, Void> {

    @Inject
    private JacksonJsonMapper jsonMapper;

    @Inject
    private FruitService fruitService;


    public Void execute(SQSEvent sqsEvent) {
        log.info("SqsEvent received");
        sqsEvent.getRecords().stream()
                .map(this::mapToFruit)
                .forEach(fruit -> fruitService.save(fruit).block());
        return null;
    }

    private Fruit mapToFruit(SQSEvent.SQSMessage record) {
        try {
            return jsonMapper.readValue(record.getBody(), Fruit.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


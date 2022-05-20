package br.ufmg.reuso.marcelosg.reprova.factories;

import br.ufmg.reuso.marcelosg.reprova.strategies.ExamGeneratorStrategy;
import br.ufmg.reuso.marcelosg.reprova.strategies.RandomExamGeneratorStrategy;
import br.ufmg.reuso.marcelosg.reprova.strategies.StrategyType;
import br.ufmg.reuso.marcelosg.reprova.strategies.TagsExamGeneratorStrategy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExamGeneratorStrategyFactory {

    private MongoTemplate mongoTemplate;

    public ExamGeneratorStrategyFactory(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public ExamGeneratorStrategy createStrategy(StrategyType strategyType) {

        switch (strategyType) {
            case RANDOM:
                return new RandomExamGeneratorStrategy(this.mongoTemplate);
            case TAGS:
                return new TagsExamGeneratorStrategy(this.mongoTemplate);
            default:
                return null;
        }
    }
}

package br.ufmg.reuso.marcelosg.reprova.strategies;

import br.ufmg.reuso.marcelosg.reprova.model.Question;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;

import java.util.List;

public class RandomExamGeneratorStrategy implements ExamGeneratorStrategy {

    private MongoTemplate mongoTemplate;

    public RandomExamGeneratorStrategy(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Question> generateExamQuestions(int totalQuestions) {

        SampleOperation sampleOperation = Aggregation.sample(totalQuestions);
        Aggregation aggregation = Aggregation.newAggregation(sampleOperation);
        AggregationResults<Question> results = mongoTemplate.aggregate(aggregation, "question", Question.class);

        return results.getMappedResults();
    }
}

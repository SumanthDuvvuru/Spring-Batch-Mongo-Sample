package com.dsr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
@EnableBatchProcessing
public class ReadFromDB {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Bean
	public Job readReport() throws Exception {
		return jobBuilderFactory.get("readReport").flow(step()).end().build();
	}

	@Bean
	public Step step() throws Exception {
		return stepBuilderFactory.get("step").<Report, Report>chunk(10).reader(reader()).writer(writer()).build();
	}

	@Bean
	public MongoItemReader<Report> reader() {
		CustomMongoReader reader = new CustomMongoReader();
		reader.setTemplate(mongoTemplate);
		reader.setTargetType(Report.class);
		reader.setQuery("{}");
		reader.setSort(new HashMap<String, Sort.Direction>() {
			{
				put("_id", Direction.DESC);
			}
		});
		reader.setPageSize(1);
		return reader;
	}

	@Bean
	public StaxEventItemWriter<Report> writer() {
		StaxEventItemWriter<Report> writer = new StaxEventItemWriter<>();
		writer.setResource(new FileSystemResource("xml/mongo.xml"));
		writer.setMarshaller(userUnmarshaller());
		writer.setRootTagName("report");
		return writer;
	}

	public XStreamMarshaller userUnmarshaller() {
		XStreamMarshaller unMarshaller = new XStreamMarshaller();
		Map<String, Class> aliases = new HashMap<String, Class>();
		aliases.put("report", Report.class);
		unMarshaller.setAliases(aliases);
		return unMarshaller;
	}
}
package com.dsr;

import org.springframework.batch.item.data.MongoItemReader;

public class CustomMongoReader extends MongoItemReader<Report> {

	@Override
	protected void doClose() throws Exception {
		page=0;
	}
	

}

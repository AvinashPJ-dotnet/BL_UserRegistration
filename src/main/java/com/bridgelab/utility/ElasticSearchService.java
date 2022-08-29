package com.bridgelab.utility;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelab.notes.model.Note;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Configuration
@Service
public class ElasticSearchService {

	String INDEX = "notedb";
	String TYPE = "notetype";

	@Autowired
	private RestHighLevelClient client;

	@Autowired
	private ObjectMapper mapper;

	public Note createNote(Note note)  {
		@SuppressWarnings("unchecked")
		Map<String, Object> dataMap = mapper.convertValue(note, Map.class);
		@SuppressWarnings("deprecation")
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, String.valueOf(note.getId())).source(dataMap);
		try {
			IndexResponse indexResponse =client.index(indexRequest, RequestOptions.DEFAULT);
			System.out.println(indexResponse.getResult().name());
			return note;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return note;
	}
}

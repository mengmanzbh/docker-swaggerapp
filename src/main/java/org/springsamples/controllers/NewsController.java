package org.springsamples.controllers;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;

@RestController
@Api("News")
class NewsController {

	Map<Long, NewsEntry> entries = new ConcurrentHashMap<Long, NewsEntry>();

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	Collection<NewsEntry> entries() {
		return this.entries.values();
	}

	@RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
	NewsEntry remove(@PathVariable Long id) {
		return this.entries.remove(id);
	}

	@RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
	NewsEntry entry(@PathVariable Long id) {
		return this.entries.get(id);
	}

	@RequestMapping(value = "/news/{id}", method = RequestMethod.POST)
	NewsEntry update(@Valid @RequestBody NewsEntry news) {
		this.entries.put(news.getId(), news);
		return news;
	}

	@RequestMapping(value = "/news", method = RequestMethod.POST)
	NewsEntry add(@Valid @RequestBody NewsEntry news) {
		long id = 10 + new Random().nextInt(99);
		news.setId(id);
		this.entries.put(id, news);
		return news;
	}

	NewsController() {
		for (long i = 0; i < 1; i++)
			this.entries.put(i, new NewsEntry(i, "Title #" + i));
	}

	public static class NewsEntry {
		private long id;
		@NotNull
		private String content;

		public NewsEntry() {
		}

		public NewsEntry(long id, String b) {
			this.id = id;
			this.content = b;
		}

		public long getId() {
			return this.id;
		}

		public String getContent() {
			return this.content;
		}

		public void setId(long id) {
			this.id = id;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
}
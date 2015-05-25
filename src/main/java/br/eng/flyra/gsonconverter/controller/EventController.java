package br.eng.flyra.gsonconverter.controller;

import java.util.Date;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.eng.flyra.gsonconverter.model.Event;

@Controller
@Path("events/")
public class EventController {

	private Result result;

	@Inject
	public EventController(Result result) {
		this.result = result;
	}

	/**
	 * @deprecated CDI's eyes only
	 */
	public EventController() {
		this(null);
	}

	@Post("")
	@Consumes("application/json")
	public void save(Event event) {
		result.use(Results.json()).from(event).serialize();
	}

	@Get("")
	public void get() {
		result.use(Results.json()).from(this.getEvent()).serialize();
	}

	private Event getEvent() {
		Event event = new Event();
		event.setId(1);
		event.setDescription("Testing");
		event.setDate(new Date());

		return event;
	}

}

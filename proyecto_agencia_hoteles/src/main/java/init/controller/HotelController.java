package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import init.model.Hotel;
import init.service.interfaces.HotelService;

@CrossOrigin("*")
@RestController
public class HotelController {
	
	@Autowired
	HotelService service;

	@GetMapping(value = "hotel/{idHotel}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> hotel(@PathVariable("idHotel") int idHotel) {
		return new ResponseEntity<Hotel>(service.hotel(idHotel), HttpStatus.OK);
	}

	@GetMapping(value = "hoteles/{localizacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> hoteles(@PathVariable("localizacion") String localizacion) {
		return new ResponseEntity<List<Hotel>>(service.hoteles(localizacion), HttpStatus.OK);
	}
	
	@GetMapping(value = "localizaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> localizaciones() {
		return new ResponseEntity<List<String>>(service.localizaciones(), HttpStatus.OK);
	}
}
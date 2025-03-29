package backend.uam.GestionService;

import backend.uam.GestionService.Model.Location;
import backend.uam.GestionService.Model.Service;
import backend.uam.GestionService.Repository.ServiceRepository;
import backend.uam.GestionService.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class GestionServiceApplication implements CommandLineRunner {

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private LocationRepository localisationRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {

//		Service orange = new Service("Orange");
//		Service senelec = new Service("Senelec");
//		Service seneau = new Service("SenEau");
//		Service yass = new Service("Yass");
//		Service obank = new Service("OBank");
//
//
//		serviceRepository.saveAll(Arrays.asList(orange, senelec, seneau, yass, obank));
//
//
//		List<Location> localisations = Arrays.asList(
//				new Location("Dakar Plateau", senelec),
//				new Location("Medina", senelec),
//				new Location("Parcelles Assainies", senelec),
//				new Location("Thies", senelec),
//				new Location("Touba", senelec),
//
//				new Location("Mbour", seneau),
//				new Location("Mermoz", seneau),
//				new Location("Grand Yoff", seneau),
//				new Location("Yoff", seneau),
//				new Location("Yeumbeul", seneau),
//
//				new Location("Kaolack", orange),
//				new Location("Tamba", orange),
//				new Location("Colobane", orange),
//				new Location("Guédiawaye", orange),
//				new Location("Rufisque", orange),
//
//				new Location("Keur Massar", obank),
//				new Location("Pikine", obank),
//				new Location("Malika", obank),
//				new Location("Tivaouane Peulh", obank),
//				new Location("Diamniadio", obank),
//
//				new Location("Kolda", yass),
//				new Location("Pikine", yass),
//				new Location("Golf Sud", yass),
//				new Location("Thiaroye", yass),
//				new Location("Saint-Lious", yass)
//		);
//
//
//		localisationRepository.saveAll(localisations);
	}
}

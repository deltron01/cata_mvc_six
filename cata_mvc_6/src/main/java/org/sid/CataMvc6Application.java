package org.sid;

import org.sid.dao.ProduitRepository;
import org.sid.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CataMvc6Application implements CommandLineRunner {

	@Autowired
	private ProduitRepository produitRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CataMvc6Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*produitRepository.save(new Produit("Prod5222", 9850, 5));
		produitRepository.save(new Produit("Prod584111", 10540, 10));
		produitRepository.save(new Produit("Prod98521", 37100, 2));
		produitRepository.save(new Produit("Prod2598", 1200, 9));
		
		produitRepository.save(new Produit("Prod2568", 5822, 5));
		produitRepository.save(new Produit("Prod11400", 1300, 10));
		produitRepository.save(new Produit("Prod36255", 9500, 13));
		produitRepository.save(new Produit("Prod2100", 7800, 6));
		
		produitRepository.save(new Produit("Prod89522", 6900, 5));
		produitRepository.save(new Produit("Prod9005", 25, 7));
		produitRepository.save(new Produit("Prod25430", 26000, 4));
		produitRepository.save(new Produit("Prod3688", 14020, 18));*/
		
		produitRepository.findAll().forEach(p -> {
			System.out.println("Produit from DB : " + p.getDesignation());
		});
	}

}

package gr.nothingness.springskeletons.datajpabasicaccess;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class DataAccessor {

  private final CustomerRepository repository;

  public DataAccessor(CustomerRepository repository) {
    this.repository = repository;

    addCustomers();
    logFindAll();
    logFindById();
    logFindByLastName();
  }

  private void addCustomers() {
    repository.save(new Customer("Jack", "Bauer"));
    repository.save(new Customer("Chloe", "O'Brian"));
    repository.save(new Customer("Kim", "Bauer"));
    repository.save(new Customer("David", "Palmer"));
    repository.save(new Customer("Michelle", "Dessler"));
  }

  private void logFindAll() {
    log.info("Customers added with findAll()");
    log.info("------------------------------");
    repository.findAll().forEach(log::info);
    log.info("");
  }

  private void logFindById() {
    Customer customer = repository.findById(1L);
    log.info("Customers added with findById(1L)");
    log.info("------------------------------");
    log.info(customer.toString());
    log.info("");
  }

  private void logFindByLastName() {
    log.info("Customers added with findByLastName(Bauer)");
    log.info("------------------------------");
    repository.findByLastName("Bauer").forEach(log::info);
    log.info("");
  }

}

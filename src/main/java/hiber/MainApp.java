package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("Sasha", "Lyakun", "Sasha@mail.ru", new Car("Kefir", 15213444)));
//      userService.add(new User("Sasha1", "Lyakun1", "Sasha@mail.ru1", new Car("Kefir", 15213444)));

      userService.getUserByCar("Kefir", 15213444).forEach(System.out::println);

//      userService.listUsers().forEach(System.out::println);

      context.close();
   }
}

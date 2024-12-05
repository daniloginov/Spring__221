package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "LastName1", "user1@gmail.com", new Car("model1", 1)));
      userService.add(new User("User2", "LastName2", "user2@gmail.com", new Car("model2", 2)));
      userService.add(new User("User3", "LastName3", "user3@gmail.com", new Car("model3", 3)));
      userService.add(new User("User4", "LastName4", "user4@gmail.com", new Car("model4", 4)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("FirstName = "+user.getFirstName());
         System.out.println("LastName = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.getCar().toString());
         System.out.println();
      }

      User u = userService.getUserByCar("model2", 2);
      System.out.println(u);

      context.close();
   }
}
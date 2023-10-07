# Room Booking

This is a simple room booking application based on Java, Hibernate and Spring Boot Framework with MVC design pattern. It uses Hibernate to persist the data to MySql.

## Modules

### Model

This module mapped to the underlying table in the database.There are 3 classes under this package : `user`, `role`, `rooms`. The classes are annotated with `@Entity`.

User: class gets the id, email, name and password from user.
Role: id and role of the user
Room: id, room type and availablity 

```
@Entity
@Table(name="role")
public class Role {
 
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 @Column(name="role_id")
 private int id;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }
}
```
### Repository

 This module is responsible for the crud operations and connects with DB through provided interfaces from JPA. Each class need to extends `JpaRepository`. The class is annotated with `@Repository`.

 ```
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

 Role findByRole(String role);
}
```
### Service and Service Impl Layer

This module is responsible to provide a set of methods with its implementation which uses repository to interact with the database.

This classes are annotated with `Service`. and repository classes are injected through `Àutowired`.

```
@Service("roomsService")
public class RoomsServiceImpl implements RoomsService {

@Autowired
 private RoomsRepository roomsRepository;

  @Override
 public java.util.List<Rooms> findAllRooms(){
     return roomsRepository.findAll();
 }
 }
```

### Controller

Business logic happens in controllers. Requests from user comes to `@RequestMapping`. Through injecting the services, the data is retrieved from DB and with `ModelAndView`class, the view is set and the data added as an object. 

```
@Controller
public class UserController {

 @Autowired
 private UserService userService;
 
 @Autowired
 private RoomsService roomsService;
 
 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("user/login");
  return model;
 }
}
```



 


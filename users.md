# Users docs

## Presentation
This documentation talks about the *way this software will manage the users*.

## What there's in this update?
In this update there are a lot of features and it covers many parts of the project:
1. user profile
2. repository (users' databbase)
3. user service system
4. controller that will closely communicate with the front-end system

### User profile (model/User.java)
This service will manage the users' properties will be ***Users.java***, which is composed by the class, the instance attributes (**each user properties**), and the access methods to set and get the each instance attribute value:
- id [getId(), setId(id)]
- username [getUsername(), setUsername(username : String)]
- password [getPassword(), setPassword(password : String)]
- total_score [getTotalScore(), setTotalScore(total_score : Integer)]
- active [setActive(active : boolean)]

if you want to get the boolean attribute, then it will execute the method 'isActive()'.

Above the class name, it was set two Jakarta annotations:
- '@Entity' -> it indicates that's an entity relationship
- '@Table' -> it indicates to change the representation class in table format

Inside of the class, above of each attribute, we have many Jakarta annotations:
- '@Id' -> id
- '@GeneratedValue(stategy)' -> id
- '@Column(unique)' -> username
- '@Column(nullable)' -> password
- '@Column(name,nullable)' -> total_score, active

### Users' Repository (repository/UserRepository.java)
The users will be managed by a Java Interface called ***UserRepository***, and this interface will be set in the ***UserRepository.java***. Inside of this file, we have our interface which inherits from another interface-JpaRepository-via extension.

Inside of the interface, there are 4 methods that will communicate with the Hibernate Database:
```java
	public Optional<User> findByUsername(String username);
	public boolean existsByUsername(String username);
	public List<User> findAllByActive(Boolean active);

	public List<User> findAllByTotalScore(Integer tot_score);
```

- findByUsername(username : String) -> search the user through his username in the database. It returns an User, else it throws an IllegalArgumentException if the returned value is *null*
- existByUsername(username : String) -> search the user through his username in the database. It return *true* if the username exists in the users' database, else it return *false*
- findAllByActive(active : Boolean) -> search all users that have the same ***online status***. It returns a *list of users* if there are many users with the same online status, else it return an *empty list of users* if there's not many players which share the same online status.
- findAllByTotalScore(tot_score : Integer) -> find all players which share the same total score.

Above the interface name there's a Springframework annotation '@Repository' to set the communication between this interface and the database. While inside the interface, there's a Sprinframwork Query annotation '@Query("SELECT ... :tot_score)' to execute correctly the query through the method *findAllByTotalScore()*. Without it, we'll always execute an Exception for not found the attribute 'totalScore'.

### User service system (service/UserService.java)
This system will be managed by the Java Class ***UserService*** in the Java File Source ***UserService.java***. The class is composed by a final attribute ***UserRepository repo*** and the oeprations to manipulate the users' repository through the attribute *repo*. Excluding the Constructor Method 'UserService()' these operations are divided in three macro-areas:

1. operations will communicate directly with the database
2. getter operations-like (they are called from the users' controller system)
3. operations called from the user's controller system


#### Operations will communicate with the database
```java
@Transactional
public User add(String username,String password){
    if (repo.existsByUsername(username)) {
        throw new RuntimeException("User not registered: this username just exists in this server!");      // todo: create a UserErrorException which extends RuntimeException
    }

    User u = new User(username,password,0,true);
    return repo.save(u);
}

@Transactional 
public boolean remove(String username){      // todo: insert a password management system
    if(repo.existsByUsername(username)){
        User u = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));

        repo.delete(u);
    }
}
```
User add(username : String, password : String) -> adds the user in the database. It returns an *User object* if the username doesn't, else it throws a RuntimeException object
boolean remove(username : String) -> removes the user from the database. It returns true if the user was found and deleted. Else, it throws a RuntimeException if the user is not found.

#### getter operations-like (they are called from the users' controller system)
```java
@Transactional(readOnly = true)
public User getUser(String username){
    return repo.findByUsername(username)
    .orElseThrow(() -> new RuntimeException("User not found!"));
}

@Transactional(readOnly = true)
public List<User> getAllUsers(){
    return repo.findAll();
}

@Transactional(readOnly = true)
public User getUserByTotScore(String username,int tot_score){
    return repo.findByUsername(username)
    .filter(u -> u.getUsername().equals(username) && u.getTotalScore() == tot_score)
    .orElseThrow(() -> new RuntimeException("User not found!"));
}

@Transactional(readOnly = true)
public List<User> getAllUsersByTotScore(int tot_score){
    return repo.findAllByTotalScore(tot_score);
}

@Transactional(readOnly = true)
public User getUserByActive(String username,boolean active){
    return repo.findByUsername(username)
    .filter(u -> u.getUsername().equals(username) && u.isActive() == active)
    .orElseThrow(() -> new RuntimeException("User not found!"));
}

@Transactional(readOnly = true)
public List<User> getAllUsersByActive(boolean active){
    return repo.findAllByActive(active);
}
```
User getUser(username : String) -> It returns an *User object* thrugh his username, else executes a *RuntimeException object* if the user was not found.
List<User> getAllUsers() -> It returns a *list of all users*. If there isn't any user in the database, it will return an *empty list*
User getUserByScore(username : String, tot_score : int) -> It returns an *User object* if there's an user with shares the int argument. Else it throws a *RuntimeException object* if there's not any user.
List<User> getAllUsersByTotScore(tot_score : int) -> It returns a *list of all users which share the same argument*. If there isn't any user in the database which don't share the same argument, it will return an *empty list*
User getUserByActive(username : String,active : boolean) -> It returns that User that shares the same username or online status. If there's not any user which shares the same username or online status, then it throws a *RuntimeExecption object* if the user was not found.
List<User> getAllUsersByActive(active : boolean) -> It returns a *list of all users which share the same online status*. If there isn't any user in the database which don't share the same online status, it will return an *empty list*

#### operations called from the user's controller system
```java
@Transactional 
public User registerUser(String username,String password){
    return add(username, password);
}

@Transactional 
public boolean loginUser(String username,String password){
    return repo.findByUsername(username).map(u -> {
        if(u.getPassword().equals(password)){
            u.setActive(true);
            repo.save(u);

            return true;
        }

        return false;
    }).orElseThrow(() -> new RuntimeException("User not found!"));
}

@Transactional 
public boolean logoutUser(String username){
    return repo.findByUsername(username).map(u -> {
        if(u != null){
            u.setActive(false);
            repo.save(u);

            return true;
        }
        return false;
    }).orElseThrow(() -> new RuntimeException("User not found!"));
}

@Transactional 
public User updateUser(String us_name,String new_username,String new_password,Integer new_tot_score){
    User u = getUser(us_name);

    if(new_username != null && !new_username.isBlank()) {
        if(repo.existsByUsername(new_username)) throw new RuntimeException("This username already exists in the server!");
        u.setUsername(new_username);
    }
    if(new_password != null && !new_password.isBlank()) u.setPassword(new_password);
    if(new_tot_score != null) u.setTotalScore(new_tot_score);

    return repo.save(u);
}

@Transactional
public boolean deleteUser(String username){
    return repo.findByUsername(username).map(u ->{
        repo.delete(u);
        return true;
    }).orElse(false);
}
```

User registerUser(username : String, password : String) -> It executes the method add().
boolean loginUser(username : String, password : String) -> It changes the online status from 'false' to 'true' if the user was found, then it return true. Else it returns false if the password was different. Or else it throws a *RuntimeException object* if the user was not found.
User updateUser(us_name : String, new_username : String, new_password : String, new_password : String, new_tot_score : Integer) -> It returns an *User object* if the user was found with his updated attributes. Else it throws a *RuntimeException object* if the User was not found.
boolean deleteUser(username : String) -> It executes remove()

#### Annotations & Stereotypes
In this file there are many annotations/stereotypes:

- @Service -> It allows the class to communicates directly with the database through the UserRepository operations
- @Transactional -> it allows a method to call many methods which manipulate the database
- @Tramsactional(readOnly = true) -> it allows a method to call many methods which returns any value from the database


### Users' control system (controller/UserController.java)
This system is managed by the Java Class *UserController* in the Java Source File ***UserController.java***. The file is composed by the ***UserService instance-attribute*** and the operations that call the UserService operations. These operations are:


```java
// REGISTER
@PostMapping("/register")
public User register(
    @RequestParam String init_username,
    @RequestParam String init_password
){
    return service.registerUser(init_username,init_password);
}

// LOGIN
@PostMapping("/login")
public boolean login(
    @RequestParam String username,
    @RequestParam String password
){
    return service.loginUser(username, password);
}

// LOGOUT
@PostMapping("/logout")
public boolean logout(@RequestParam String username){
    return service.logoutUser(username);
}

// UPDATE
@PostMapping("/update")
public User update(
    @RequestParam String curr_username,
    @RequestParam(required = false) String new_username,
    @RequestParam(required = false) String new_password,
    @RequestParam(required = false) Integer new_tot_score
){
    return service.updateUser(curr_username,new_username,new_password,new_tot_score);
}

// DELETE
@PostMapping("/delete")
public boolean delete(@RequestParam String username){
    return service.deleteUser(username);
}

// GET USER/ALL
@GetMapping("/get")
public User get(@RequestParam String username){
    return service.getUser(username);
}

@GetMapping("/get-all")
public List<User> getAll(){
    return service.getAllUsers();
}

@GetMapping("/get-by-score")
public User getByScore(
    @RequestParam String username,
    @RequestParam int tot_score
){
    return service.getUserByTotScore(username,tot_score);
}

@GetMapping("/get-all-by-score")
public List<User> getAllByScore(@RequestParam int tot_score){
    return service.getAllUsersByTotScore(tot_score);
}

@GetMapping("/get-user-online")
public User getByActive(
    @RequestParam String username,
    @RequestParam boolean status
){
    return service.getUserByActive(username,status);
}

@GetMapping("/get-all-online")
public List<User> getAllByActive(@RequestParam boolean status){
    return service.getAllUsersByActive(status);
}
```
User register(init_username : String, init_password : String) -> It executes service.registerUser()
boolean login(username : String, password : String) -> It executes service.loginUser()
boolean logout(username :  : String) -> It executes service.logoutUser()
User update(curr_username : String, new_username : String, new_password : String, new_tot_score : Integer) -> It executes service.updateUser()
boolean delete() -> It executes service.deleteUser()
User get() -> It executes service.getUser()
List<User> getAll() -> It executes service.getAll()
User getByScore() -> It executes service.getByScore()
List<User> getAllByScore() -> It executes service.getAllByScore()
User getByActive() -> It executes service.getByActive()
List<User> getAllByActive() -> It executes service.getAllByActive()


#### Annotations & Stereotypes
There are many ***annotations/stereotypes***:

- @RestController -> it needs to define a Controller Class of the users
- @RequestMapping("/api/users") -> It used to link to the database
- @PostMapping() -> It used to map many tuples of the database
- @GetMapping() -> It used to get many interested tuples from the database



End.

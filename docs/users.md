# User docs
Added a feature about users' management: ***Creation Account Date***

## Users.java
Defined in this Java File Source the following elements:

```java
@Column(nullable = false,updatable = false)
private LocalDate created_at;

public LocalDate getCreatedAt(){
    return created_at;
}
```

private created_at -> attribute which shows the creation date for each user account
public LocalDate getCreatedAt() -> getter operator which returns a LocalDate object (*creation account date expressed in **string format***)

### Annotations/Stereotypes
@Column(nullable = false,updatable = false) -> It defines the attribute *created_at* a not nullable value, but a not updatable value

## UserRepository.java
Defined in this Java File Source the following elements:

```java
@Query ("SELECT u FROM User u WHERE u.created_at = :created_at")
public List<User> findAllByCreated_at(LocalDate created_at);
```

public List<User> findAllByCreated_at(LocalDate created_at) -> It returns a list of users created their account at the specified argument

### Annotations/stereotypes
@Query ("SELECT u FROM User u WHERE u.created_at = :created_at") -> It defines a query to avoid the Spring Framework implementation call ambiguity

## UserService.java
Defined in this Java File Source the following elements:

```java
@Transactional(readOnly = true)
public User getUserByCreationDate(String username,LocalDate created){
    return repo.findByUsername(username)
    .filter(u -> u.getUsername().equals(username) && u.getCreatedAt().equals(created))
    .orElseThrow(() -> new RuntimeException("User not found!"));
}

@Transactional(readOnly = true)
public List<User> getAllUsersByCreationDate(LocalDate created){
    return repo.findAllByCreated_at(created);
}
```
public User getUserByCreationDate(String username,LocalDate created) -> It returns a found User; it throws a ***RuntimeException object*** if the User was not found else
public List<User> getAllUsersByCreationDate(LocalDate created) -> It returns a List of Users; if the list is empty then it returns an empty List of Users

### Annotations/Stereotypes
@Transactional(readOnly = true) -> It allows to execute a ***read-only operation*** to the below method 

## UserController.java
Defined in this Java File Source the following elements:

```java
@GetMapping("/get-by-date-created")
public User getUserByAccountCreated(String username,LocalDate created){
    return service.getUserByCreationDate(username,created);
}

@GetMapping("/get-all-by-date-created")
public List<User> getAllByAccountCreated(@RequestParam LocalDate created){
    return service.getAllUsersByCreationDate(created);
}
```
public List<User> getAllByAccountCreated(@RequestParam LocalDate created) -> It returns a List of Users through the UserService operator getAllUsersByCreationDate();
public User getUserByAccountCreated(@RequestParam LocalDate created) -> It returns an User through the UserService operator getUserByCreationDate()

### Annotations/Stereotypes
@GetMapping("/...") -> It allows to get the type value set to below of this annotation
